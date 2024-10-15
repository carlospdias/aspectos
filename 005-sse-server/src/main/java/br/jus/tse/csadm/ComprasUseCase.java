package br.jus.tse.csadm;

import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import java.util.List;

@Service
public class ComprasUseCase {

    private List<ProductResponse>listarProdutosDisponiveisParaProntaEntrega(String urlDoFornecedor){
        CatalogoDeProdutos catalogoDeProdutos = new CatalogoEudora();
        return catalogoDeProdutos.recuperarCatalogo(urlDoFornecedor);
    }
    private void writeToFile(List<ProductResponse>produtos){
        String file = System.getProperty("user.home") + "\\h2db\\produtos.html";
        System.out.println(file);
        System.out.println("********************************************");
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(file, false), 1024)) {
           for(ProductResponse produto : produtos){
               bw.write(produto.responseAsHtmCard());
           }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public List<ProductResponse> gravarNoBancoDeDados(String urlDoFornecedor){
        List<ProductResponse>produtos = listarProdutosDisponiveisParaProntaEntrega(urlDoFornecedor);
        writeToFile(produtos);
        return produtos;
    }
}
