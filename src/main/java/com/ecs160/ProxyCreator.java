package com.ecs160;

import java.lang.reflect.Method;
// javassist proxy
import javassist.util.proxy.MethodHandler;
import javassist.util.proxy.ProxyFactory;
import javassist.util.proxy.ProxyObject;
import java.util.HashMap;


import com.ecs160.db.RedisLogger;

public class ProxyCreator {
    private HashMap<String, Object> proxyCache = new HashMap<>();

    class LogHandler implements MethodHandler {
        private Object target;
        private RedisLogger logger;

        LogHandler(Object target, RedisLogger logger) {
            this.target = target;
            this.logger = logger;
        }

        @Override
        public Object invoke(Object self, Method thisMethod, Method proceed, Object[] args) throws Throwable {
            // Check if the 
            // method is annotated with @Log
            if (thisMethod.isAnnotationPresent(com.ecs160.annotations.Log.class)) {
                logger.log("Invoking method: " + thisMethod.getName());
            }
            return thisMethod.invoke(target, args);
        }
    }

    public Object createProxy(Object obj) throws Exception {
        Class<?> clazz = obj.getClass();
        String className = clazz.getName();

        // avoid creating the proxy each time
        if (proxyCache.containsKey(className)) {
            return proxyCache.get(className);
        }

        ProxyFactory factory = new ProxyFactory();
        factory.setSuperclass(clazz);
        Class<?> proxyClass = factory.createClass();
        Object proxyInstance = proxyClass.getDeclaredConstructor().newInstance();


        // Create the RedisLogger
        RedisLogger logger = new RedisLogger("localhost", 6379);
        ((ProxyObject) proxyInstance).setHandler(new LogHandler(obj, logger));

        proxyCache.put(className, proxyInstance);
        return proxyInstance;
    }
}
