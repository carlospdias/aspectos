package br.jus.tse.csadm.sessao;

import java.lang.reflect.Method;

import com.github.underscore.U;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

class XmlTransformInvocation implements MethodInterceptor{
    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        String res  = (String)  proxy.invokeSuper(obj, args);
        return U.jsonToXml(res.toUpperCase());
    }
}

public class ProxyFactoryCGLIB {
    
    private ProxyFactoryCGLIB() {}
    
    @SuppressWarnings("unchecked")
    public static<T>T createProxy(T target, Class<T>superClassType){
        
        Enhancer enhancer = new Enhancer();
        
        enhancer.setSuperclass(superClassType);
        enhancer.setCallback(new XmlTransformInvocation());
       
        return (T)enhancer.create();
        
    }
}
