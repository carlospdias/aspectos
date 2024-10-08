package br.jus.tse.csadm.spi;

import br.jus.tse.csadm.sessao.Sessao;
import br.jus.tse.csadm.sessao.SessaoDeCinema;

/**
 * Hello world!
 */
public class Cliente {
    public static void main(String[] args) {
        Sessao sessao = new SessaoDeCinema();
        long startingTime = System.currentTimeMillis();
        String jsonText = sessao.recuperarSessoes();
        
        System.out.println(jsonText);
        long endingTime = System.currentTimeMillis();
        System.out.println("                 *************************** Tempo de execução:" + (endingTime-startingTime));
    }
}
