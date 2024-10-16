package br.jus.tse.csadm;

import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("suprimento")
public class SupplierRequest {
    private String nome;
    private String url;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
