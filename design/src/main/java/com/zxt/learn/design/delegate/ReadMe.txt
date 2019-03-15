不知道怎么画UML 阅读看一下 spring MVC dispatcherServlet doService 的处理逻辑
其中 所有的请求 都会映射到 dispatcherServlet 中 doService 方法
doService 会调用 doDispacth 方法 开始分发请求
doDispacth  方法 会遍历handlerMapping list 根据Url 获取 对应的handler  其中 hanlerMapping 是策略模式  通过对应的hanlder 反射调用  对应的方法 这样 能够调用 @Controller 中的业务逻辑