
@param String name

<!doctype html>
<html lang="pt-br">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <title>Hello, world!</title>
  </head>
  <body>
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
      <div class="container-fluid">
        <a class="navbar-brand" href="#">Server Side Events</a>
      </div>
    </nav>
    <main class="container py-4 px-3 mx-auto">
      <div class="card" id="card">
        <div class="card-header"> Avisos</div>
        <div class="card-body">
          <h5 class="card-title">Mensagem Recebida </h5>
          <p class="card-text" id="msg-text">${name}</p>
          <div class="card-body" id="card-body"></div>
        </div>
      </div>
    </main>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
    <script>
      document.onreadystatechange = function () {
        if (document.readyState == "complete") {
          const endPointUrl = "http://localhost:8085/subscribe"
          const eventSource = new EventSource(endPointUrl);
          eventSource.addEventListener("ultima_cotacao", (event)=>{
          let json =  JSON.parse(event.data);

          document.getElementById("msg-text").textContent = json['titulo'];

          let el_body = document.getElementById("card-body");
          console.log(el_body);
          let img = document.createElement('img');
          img.src = json["image"];
          img.width="300";
          img.heigh="300";
          el_body.appendChild(img);
          el_body.appendChild(document.createElement('br'))
          let link = document.createElement("a");
          link.href=json["link"];
          link.target="_blank";

          link.textContent="Link de Compra"
          el_body.appendChild(link);
          el_body.appendChild(document.createElement('br'));
          el_body.appendChild(document.createTextNode("Preço Normal: " + json["precoNomal"]));
          el_body.appendChild(document.createElement('br'));
          el_body.appendChild(document.createTextNode("Desconto: " + json["desconto"]));
          el_body.appendChild(document.createElement('br'));
          el_body.appendChild(document.createTextNode("Preço de Venda: " + json["precoComDesconto"]));
          el_body.appendChild(document.createElement('br'));
          });
        }
      }
    </script>
  </body>
</html>
