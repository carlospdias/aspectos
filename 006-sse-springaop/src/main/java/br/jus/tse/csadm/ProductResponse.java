package br.jus.tse.csadm;

record ProductResponse(String nome, double desconto, double precoNormal, double precoFinal, String imagem){
    private static final String responseTemplate = """
            <div class="card m-1 border-secondary w-33" >
               <div class="card-header">
                 %s
               </div>
               <img src="%s"  class="card-img-top"  alt="...">
               
                  <ul class="list-group list-group-flush">
                     <li class="list-group-item">Preço Normal: %.2f</li>
                     <li class="list-group-item">Desconto: %.2f</li>
                     <li class="list-group-item">Preço de Venda: %.2f</li>
                  </ul>
            </div>
            """;

    String responseAsHtmCard(){
        return String.format(responseTemplate,nome, imagem, precoNormal,  desconto, precoFinal);
    }
}
