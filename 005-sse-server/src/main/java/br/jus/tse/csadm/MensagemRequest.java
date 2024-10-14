package br.jus.tse.csadm;

import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("mensagem")
public class MensagemRequest {
    private String titulo;
    private String link;
    private String cupom;
    private String precoNomal;
    private String desconto;
    private String precoComDesconto;
    private String image;

    public MensagemRequest() {
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getCupom() {
        return cupom;
    }

    public void setCupom(String cupom) {
        this.cupom = cupom;
    }

    public String getPrecoNomal() {
        return precoNomal;
    }

    public void setPrecoNomal(String precoNomal) {
        this.precoNomal = precoNomal;
    }

    public String getDesconto() {
        return desconto;
    }

    public void setDesconto(String desconto) {
        this.desconto = desconto;
    }

    public String getPrecoComDesconto() {
        return precoComDesconto;
    }

    public void setPrecoComDesconto(String precoComDesconto) {
        this.precoComDesconto = precoComDesconto;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
