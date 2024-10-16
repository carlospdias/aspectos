package br.jus.tse.csadm;



import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import java.util.List;
import java.util.UUID;

@Aspect
@Component
public class FileSystemLoggingEventAspect {

    @Pointcut("execution(public * br.jus.tse.csadm.ComprasUseCase..*(..))")
    public void logPointcutWithArgs() {}

    private void writeToFile(String fileName, List<ProductResponse>produtos){
        String file = System.getProperty("user.home") + "\\h2db\\"+ UUID.randomUUID() + fileName + ".txt";

        try(BufferedWriter bw = new BufferedWriter(new FileWriter(file, false), 1024)) {
            for(ProductResponse produto : produtos){
                bw.write(produto.responseAsHtmCard());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @AfterReturning(value= "execution(*  br.jus.tse.csadm.ComprasUseCase..*(..))", returning= "result")
    public void logMethodCallsWithArgsAdvice(JoinPoint joinPoint, List<ProductResponse> result) {
        String fileName = ((String)joinPoint.getArgs()[0]).replace("https://","").replaceAll("[./\\?=]","-");
        writeToFile(fileName, result);
    }

}
