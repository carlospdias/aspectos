package br.jus.tse.csadm;

import java.util.List;

public interface CatalogoDeProdutos {
    List<ProductResponse> recuperarCatalogo(String url);
}
