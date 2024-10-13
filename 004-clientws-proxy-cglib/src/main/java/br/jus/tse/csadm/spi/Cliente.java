package br.jus.tse.csadm.spi;

import br.jus.tse.csadm.sessao.ProxyFactoryCGLIB;
import br.jus.tse.csadm.sessao.SessaoDeCinema;

/**
 * Hello world!
 */
public class Cliente {
    public static void main(String[] args) {
        /*Sessao sessao = new SessaoDeCinemaProxy();
        long startingTime = System.currentTimeMillis();
        String jsonText = sessao.recuperarSessoes();
        
        System.out.println(jsonText);
        long endingTime = System.currentTimeMillis();
        System.out.println("                 *************************** Tempo de execução:" + (endingTime-startingTime));*/
        
        // in vm arguments add
        //--add-opens java.base/java.lang=ALL-UNNAMED
        
        SessaoDeCinema sessaoProxy = ProxyFactoryCGLIB.createProxy(new SessaoDeCinema(), SessaoDeCinema.class);
        
        System.out.println(sessaoProxy.recuperarSessoes());
    }
}
