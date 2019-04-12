package com.zxt.learn.mvcframework.servlet;

import com.zxt.learn.mvcframework.annotation.*;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by zxt on 2019/4/1.
 */
public class ZxtDispatcherServlet extends HttpServlet {

    private Properties contextConfig = new Properties();

    private List<String> classNames = new ArrayList<String>();

    private Map<String,Object> ioc = new HashMap<String, Object>();

    private List<Handler> handlerMapping = new ArrayList<Handler>();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        dispatcher(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    private void dispatcher(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException{
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter out = resp.getWriter();
        Handler handler = getHandler(req);
        if(handler==null){
            out.write("hahaha  404 la");
            return;
        }
        Class<?>[] types = handler.getClasses();
        Object[] args = new Object[types.length];
        Map<String,String[]> parmMap = req.getParameterMap();
        for(Map.Entry<String,String[]> entry:parmMap.entrySet()){
            String key = entry.getKey();
            String value = entry.getValue().length==0?"":Arrays.toString(entry.getValue()).replaceAll("\\[|\\]","");
            if(!handler.pramemterMap.containsKey(key)) continue;
            int index = handler.pramemterMap.get(key);
            args[index] = conrvet(types[index],value);
        }

        if(handler.pramemterMap.containsKey(HttpServletRequest.class.getName())){
            args[handler.pramemterMap.get(HttpServletRequest.class.getName())] =req;
        }

        if(handler.pramemterMap.containsKey(HttpServletResponse.class.getName())){
            args[handler.pramemterMap.get(HttpServletRequest.class.getName())] =resp;
        }
        System.out.println(Arrays.toString(args));
        try {
            Object obj = handler.getMethod().invoke(handler.getObj(),args);
            if(obj==null||obj instanceof Void){
                return;
            }else {
                out.write(obj.toString());
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }


    private Handler getHandler(HttpServletRequest request){
        if(handlerMapping.isEmpty())return null;
        String url = request.getRequestURI().toString();
        String context = request.getContextPath();
        String baseUrl = url.replace(context,"").replaceAll("/+","/");
        for(Handler handler:handlerMapping){
            Matcher matcher = handler.getPattern().matcher(baseUrl);
            if(matcher.matches()){
                return handler;
            }else {
                continue;
            }
        }
        return null;
    }

    private Object conrvet(Class<?> type,String value){
        System.out.println("type:"+type.getSimpleName());
        if(type==Integer.class){
            return Integer.valueOf(value);
        }
        if(type==Double.class){
            return Double.valueOf(value);
        }

        return value;
    }

    @Override
    public void destroy() {
        super.destroy();
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        loadConfig(config.getInitParameter("contextConfigLocation"));

        scanPackages(contextConfig.getProperty("scanPackage"));

        doInstance();

        doDi();

        doInitHandlerMapping();

        System.out.println("dispatcherServlet is init");
    }

    private void doInitHandlerMapping() {
        System.out.println("doInitHandlerMapping");
        if(ioc.isEmpty()){return;}

        for(Map.Entry<String,Object> entry:ioc.entrySet()){
            Class<?> clazz = entry.getValue().getClass();
            if(clazz.isAnnotationPresent(ZxtController.class)){
                String baseUrl = "";
                if(clazz.isAnnotationPresent(ZxtRequestMapping.class)){
                    ZxtRequestMapping zxtRequestMapping = clazz.getAnnotation(ZxtRequestMapping.class);
                    baseUrl = zxtRequestMapping.value();
                }

                Method[] methods = clazz.getMethods();
                for (Method method:methods){
                    if(method.isAnnotationPresent(ZxtRequestMapping.class)){
                        ZxtRequestMapping zxtRequestMapping = method.getAnnotation(ZxtRequestMapping.class);
                        String url = "/"+ baseUrl +"/"+ zxtRequestMapping.value();
                        System.out.println(url);
                        String regsx = url.replaceAll("/+","/");
                        System.out.println(regsx);
                        Pattern pattern = Pattern.compile(regsx);
                        Handler handler = new Handler(method,pattern,entry.getValue());
                        handlerMapping.add(handler);
                    }
                }
            }
        }
    }


    private void doDi() {
        System.out.println("DI");
        if(ioc.isEmpty()){return;}

        for (Map.Entry<String,Object> entry:ioc.entrySet()){
            Class<?> clazz = entry.getValue().getClass();
            Field[] fields = clazz.getDeclaredFields();
            for (Field field:fields){
                if(field.isAnnotationPresent(ZxtAutowired.class)){
                    ZxtAutowired autowired = field.getAnnotation(ZxtAutowired.class);
                    String beanName = autowired.value().trim();
                    if("".equals(beanName)){
                        //获得接口的类型，作为key待会拿这个key到ioc容器中去取值
                        beanName = field.getType().getName();
                    }
                    field.setAccessible(true);

                    try {
                        field.set(entry.getValue(),ioc.get(beanName));
                        Object obj = field.get(entry.getValue());
                        System.out.println(obj.getClass());
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }

                }else {continue;}
            }
        }
    }

    private void doInstance() {
        if (classNames.isEmpty()) return;
        for(String className :classNames){
            try {
                Class<?> clazz = Class.forName(className);
                if(clazz.isAnnotationPresent(ZxtController.class)){
                    Object obj = clazz.newInstance();
                    String beanName  = getBeanName(clazz);
                    ioc.put(beanName,obj);
                }else if(clazz.isAnnotationPresent(ZxtService.class)){
                    ZxtService service =clazz.getAnnotation(ZxtService.class);
                    String beanName = service.value();
                    if(beanName==null||"".equals(beanName)){
                        beanName =  getBeanName(clazz);
                    }
                    Object obj = clazz.newInstance();
                    ioc.put(beanName,obj);

                    for (Class<?> cla:clazz.getInterfaces()){
                        if(ioc.containsKey(cla.getName())){
                            throw new Exception("The “" + cla.getName() + "” is exists!!");
                        }
                        ioc.put(cla.getName(),obj);
                    }
                }else {
                    continue;
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private String getBeanName(Class clazz){
        String beanName =clazz.getSimpleName();
        char c1 = (char) (beanName.charAt(0)-('A'-'a'));
        beanName = c1 + beanName.substring(1);
        return beanName;
    }

    private void scanPackages(String scanPackage) {
        System.out.println("/"+scanPackage.replaceAll("\\.","/"));
        URL url = this.getClass().getClassLoader().getResource("/"+scanPackage.replaceAll("\\.","/"));
        System.out.println(url.getFile());
        File files = new File(url.getFile());
        for(File file:files.listFiles()){
            if(file.isDirectory()){
                scanPackages(scanPackage+"."+file.getName());
            }else {
                if(!file.getName().endsWith(".class")){continue;}
                String className = scanPackage+"."+file.getName().replace(".class","");
                classNames.add(className);
            }
        }

    }

    private void loadConfig(String configKey){
        System.out.println(configKey);
        InputStream in = this.getClass().getClassLoader().getResourceAsStream(configKey);
        try {
            contextConfig.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public class Handler{
        private Method method;
        private Pattern pattern;  //正则
        private Object obj;
        private Class[] classes;

        public Method getMethod() {
            return method;
        }

        public Pattern getPattern() {
            return pattern;
        }

        public Object getObj() {
            return obj;
        }

        public Class[] getClasses() {
            return classes;
        }

        public Handler(Method method, Pattern pattern, Object obj) {
            this.method = method;
            this.pattern = pattern;
            this.obj = obj;
            classes = method.getParameterTypes();
            initMap(method);
        }

        private Map<String,Integer> pramemterMap = new HashMap<String, Integer>();

        private void initMap(Method method){
            Annotation[][] annotations=method.getParameterAnnotations();
            for (int i = 0;i<annotations.length;i++){
                for (Annotation annotation:annotations[i]){
                    if(annotation instanceof  ZxtRequestParam){
                        String parmemterName = ((ZxtRequestParam)annotation).value();
                        System.out.println("parm:"+parmemterName);
                        pramemterMap.put(parmemterName,i);
                    }
                }
            }

            Class<?>[] pramemters = method.getParameterTypes();
            for(int i=0;i<pramemters.length;i++){
                Class<?>  c = pramemters[i];
                if(c==HttpServletRequest.class||c==HttpServletResponse.class){
                    pramemterMap.put(c.getSimpleName(),i);
                }
            }
        }
    }
}
