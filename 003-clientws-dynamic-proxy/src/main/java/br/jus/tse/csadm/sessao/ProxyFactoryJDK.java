package br.jus.tse.csadm.sessao;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import com.github.underscore.U;

class XmlTransformInvocation implements InvocationHandler{
    private Object target;
    
    public XmlTransformInvocation(Object target) {
        super();
        this.target = target;
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        String jsonString = (String) method.invoke(target,args);
        return U.jsonToXml(jsonString);
    }
    
}
public class ProxyFactoryJDK {
    
    private ProxyFactoryJDK() {}
    
    @SuppressWarnings("unchecked")
    public static<T>T createFactory(T target, Class<T>interfaceType){
        
        return (T) Proxy.newProxyInstance(interfaceType.getClassLoader(), new Class<?>[] {interfaceType}, new XmlTransformInvocation(target));
        
    }
}
