# Getting Started

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/3.3.4/maven-plugin)
* [Create an OCI image](https://docs.spring.io/spring-boot/3.3.4/maven-plugin/build-image.html)
* [Spring Web](https://docs.spring.io/spring-boot/docs/3.3.4/reference/htmlsingle/index.html#web)
* [JTE](https://jte.gg/)

### Guides
The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)

## JTE

This project has been configured to use [JTE precompiled templates](https://jte.gg/pre-compiling/).

However, to ease development, those are not enabled out of the box.
For production deployments, you should remove

```properties
gg.jte.development-mode=true
```

from the `application.properties` file and set

```properties
gg.jte.use-precompiled-templates=true
```

instead.
For more details, please take a look at [the official documentation](https://jte.gg/spring-boot-starter-3/).

### Maven Parent overrides

Due to Maven's design, elements are inherited from the parent POM to the project POM.
While most of the inheritance is fine, it also inherits unwanted elements like `<license>` and `<developers>` from the parent.
To prevent this, the project POM contains empty overrides for these elements.
If you manually switch to a different parent and actually want the inheritance, you need to remove those overrides.


-----------------
https://github.com/danvega/jte-forms/blob/main/src/main/jte/index.jte
https://medium.com/@leonidivakhnenko/internationalization-with-jte-and-spring-boot-3-979a939f6ccb


## Testes
----------------------- testes ------------
curl -i -H "Accept: application/json" -H "Content-Type: application/json" -X POST --data '{"titulo": "O fim do jogo","descricao": "oveiic ad idf ","preco": 800}' "http://localhost:8085/dispachEvent"


curl -i -H "Accept: application/json" -H "Content-Type: application/json" -X POST --data '{"titulo": "Kaiak Sonar Masculino 100 ml", "link": "https://www.natura.com.br/c/perfumaria-masculina", "cupom": "uai", "precoNomal":"179,90", "desconto":"36", "precoComDesconto":"109,90",
"image":"https://production.na01.natura.com/on/demandware.static/-/Sites-natura-br-storefront-catalog/default/dw6826adb9/Produtos/NATBRA-155607_2.jpg"}' "http://localhost:8085/dispachEvent"


//@EnableAspectJAutoProxy -- Não precisa usar no Springboot

----------------------
## Testes
https://www.baeldung.com/spring-aop-advice-tutorial
https://reflectoring.io/aop-spring/