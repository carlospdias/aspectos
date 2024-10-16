package br.jus.tse.csadm;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComprasUseCase {

    private List<ProductResponse>listarProdutosDisponiveisParaProntaEntrega(String urlDoFornecedor){
        CatalogoDeProdutos catalogoDeProdutos = new CatalogoEudora();
        return catalogoDeProdutos.recuperarCatalogo(urlDoFornecedor);
    }
    public List<ProductResponse> gravarNoBancoDeDados(String urlDoFornecedor){
        return listarProdutosDisponiveisParaProntaEntrega(urlDoFornecedor);

    }
}
