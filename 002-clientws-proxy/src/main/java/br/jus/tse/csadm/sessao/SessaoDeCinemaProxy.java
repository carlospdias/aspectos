package br.jus.tse.csadm.sessao;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;

public class SessaoDeCinemaProxy implements Sessao {
    public static final int MAX_CACHE_IN_HOURS = 3;
    private static final String FILE_NAME_CACHE=System.getProperty("user.home") + "/cache-movies.json";

    private boolean isvalidCache(){
        Path path = Paths.get(FILE_NAME_CACHE);
        if (!path.toFile().isFile()){
            return false;
        }
        BasicFileAttributes attr;
        try {
            attr = Files.readAttributes(path, BasicFileAttributes.class);
            attr.lastModifiedTime();

            LocalDateTime ultimaModificacao = LocalDateTime.ofInstant(attr.lastModifiedTime().toInstant(), ZoneId.systemDefault());
            long hoursPassed = ChronoUnit.HOURS.between(ultimaModificacao, LocalDateTime.now());
            return  MAX_CACHE_IN_HOURS > hoursPassed;
        }catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
    private String getCache(){
        Path path = Paths.get(FILE_NAME_CACHE);
        try {
           return  Files.readString(path, Charset.defaultCharset());
           
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private void updateCache(String json){
        Path path = Paths.get(FILE_NAME_CACHE);
        try {
            Files.writeString(path, json);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public String recuperarSessoes() {
        if(!isvalidCache()){
            Sessao filmSessions = new SessaoDeCinema();
            String result =  filmSessions.recuperarSessoes();
            updateCache(result);
          }
          return getCache();
    }

}
