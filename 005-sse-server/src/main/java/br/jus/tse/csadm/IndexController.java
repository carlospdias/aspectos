package br.jus.tse.csadm;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Controller
public class IndexController {
    private final List<SseEmitter> emitters = new CopyOnWriteArrayList<>();

    @GetMapping
    public ModelAndView index(){
        ModelAndView mv = new ModelAndView("index");
        return mv;
    }
    @CrossOrigin
    @GetMapping(value="subscribe")
    public SseEmitter subscribeEvent() {
        SseEmitter sseEmitter = new SseEmitter(Long.MAX_VALUE);
        try {
            sseEmitter.send(SseEmitter.event().name("INIT"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        sseEmitter.onCompletion(() -> emitters.remove(sseEmitter));

        emitters.add(sseEmitter);
        return sseEmitter;

    }
    private String prepareResponse(List<ProductResponse>respostas){
        StringBuilder stb = new StringBuilder();
        respostas.forEach((resp)->{
            stb.append(resp.responseAsHtmCard());
        });
        return stb.toString();
    }
    @PostMapping(value="dispachEvent",consumes = "application/json")
    public ResponseEntity<?> dispatchEvent(@RequestBody SupplierRequest supplierRequest) {
        ComprasUseCase comprasUseCase = new ComprasUseCase();
        String response = prepareResponse(comprasUseCase.gravarNoBancoDeDados(supplierRequest.getUrl()));

        for (SseEmitter emitter : emitters) {
            try {
                emitter.send(SseEmitter.event().name("ultima_cotacao").data(response));
            } catch (IOException e) {
                emitters.remove(emitter);
                throw new RuntimeException(e);
            }
        }

        return ResponseEntity.ok().header("fila","alimentada").body("mensagem recebida");
    }

}
