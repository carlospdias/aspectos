package br.jus.tse.csadm;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CatalogoEudora implements CatalogoDeProdutos {

    @Override
    public List<ProductResponse> recuperarCatalogo(String url) {
        List<ProductResponse>produtos = new ArrayList<>();
        try {

            Document doc = Jsoup.connect(url).get();
            Elements elbase = doc.select(".showcase-gondola > .showcase-item.js-event-product-click");

            for (int i =0; i < elbase.size();i++){
                if (!elbase.get(i).hasClass("showcase-item-unavailable")) {

                    String nomedoProduto = elbase.get(i).select("div.wrap-showcase > div > p.showcase-item-name > a").attr("title");
                    String discountData = elbase.get(i).select("a.showcase-item-image span.item-discount").attr("data-discount");
                    String precoFinal = elbase.get(i).select(".wrap-showcase > div > div.showcase-item-buy > div>a").attr("data-price");
                    String precoSemDesconto = precoFinal;
                    int indexDiscount = discountData.indexOf("discount");
                    String desconto = "0";
                    if (indexDiscount > -1) {
                        desconto = discountData.substring(indexDiscount).split(":")[1].replace("}", "");
                        precoSemDesconto = elbase.get(i).select(".wrap-showcase > div > div.item-price > div > div.item-price-max").text().replaceAll("R\\$", "").trim().replaceAll(",", ".");
                    }
                    String image = elbase.get(i).select("a.showcase-item-image > img").attr("data-src");
                    ProductResponse productResponse = new ProductResponse(nomedoProduto, Double.parseDouble(desconto), Double.parseDouble(precoSemDesconto), Double.parseDouble(precoFinal), image);
                    produtos.add(productResponse);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return produtos;
    }

}
