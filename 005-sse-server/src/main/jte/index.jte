
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
      <div class="card">
        <div class="card-header"> Avisos</div>
        <div class="card-body">
          <h5 class="card-title">Mensagem Recebida </h5>
          <p class="card-text" id="msg-text">${name}</p>
          <p>${server.contextPath}</p>
        </div>
      </div>
    </main>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
    <script>
      var xhr = function(url) {
        return new Promise(function(resolve, reject) {
          try {
            var xmhr = new XMLHttpRequest();
            //Listen for API Response
            xmhr.onreadystatechange = function() {
              if (xmhr.readyState == XMLHttpRequest.DONE && xmhr.status == 200) {
                return resolve(xmhr.responseText);
              }
            };
            xmhr.open("GET", url, true);
            xmhr.send();
          } catch (ex) {
            reject("Exception: Oh CORS's you've made a mistake!");
          }
        });
      };


      var sse = new EventSource('http://localhost:8522/api-sse');
      sse.onmessage = function (evt) {
        var el = document.getElementById('msg-text');
        el.appendChild(document.createTextNode(evt.data));
        el.appendChild(document.createElement('br'))
      };

    </script>
  </body>
</html>
