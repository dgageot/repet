# Application Web Moderne en Java

## The CodeStory Way
by David Gageot & Jean-Laurent de Morlhon

## Abstract

Venez participer à un Hand's On Lab de 2-3 heures. L'objectif est simple : apprendre à développer une application web moderne en Java. Rapidement, de manière pragmatique et en toute confiance.

Avec l'assistance et les démos live de David Gageot et Jean-Laurent de Morlhon.

Au menu : du Java 8, un poil d'Angular, un soupçon de CoffeeScript, du MongoDB, du pair-programming, des tests IHM, des hotkeys de malade, des plugins de fou et un cycle de développement ultra rapide. Oui vous ne rêvez pas on parle bien de Java.

Lundi, au boulot, vous ne verrez plus votre projet java de la même manière.

# Install party

Pour faire cet atelier, tu as besoin  :
  * d'un portable avec de la batterie pour durer 3h de coding
  * d'un binôme
  * de logiciels :
    * Java 8
    * maven 3.1
    * d'un IDE
    * de quelques "assets" de données et graphiques

Tu trouveras tout ça sur une clé USB que nous te distribuons au début de la session.

# Recruteur.io

Votre ami Jean-Claude de SupDeCo Aurillac a une idée de business de malade, Mark Zuckerberg en tremble encore :
il s'agit de faire un site pour trouver des grouillots modernes, des programmeurs, pour se faire une tonne de $$$ en les
plaçant dans des structures qui font des projets web.

Le truc c'est que visiblement les programmeurs ont des talents différents et que pour faire une bonne équipe, il faut
mixer ces talents.
Entre recherche de mots clés (ex: cherche stagiaire, 7 ans d'expérience en j2ee, vba et golo est un plus), ce que tous
les sites font, il faut plutôt être sur qu'ils soient bons dans un certain nombre de caractéristiques utiles aux
projets d'aujourd'hui.

En 2014, il nous faut 4 talents : `Front`, `Back`, `Database`, `Test`. Pour faire chouette et parce que c'est plus
vendeur, on veut aussi un coefficient `Hipster`.

Donc le site doit optimiser le prix (le nerf de la guerre d'après Jean-Claude, et il sait de quoi il parle: il a une BM)
mais aussi ces 5 caractéristiques.

Ca pourrait ressembler à ça :

![Screenshot](./screenshot.png)

Martine de la compta, a déjà acheté le nom de domaine, y'a plus qu'à.
On t'a installé Frontpage et IIS, let's go ! T'as 2h.

# Let's write some code

## Démarrer le serveur

1. Crées toi un répertoire tout neuf, dans lequel tu ajoute un `pom.xml` qui pourrait ressembler à ça :

```xml
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/maven-v4_0_0.xsd">
  <modelVersion>4.0.0</modelVersion>

  <groupId>net.code-story</groupId>
  <artifactId>recruteurio</artifactId>
  <version>1.0-SNAPSHOT</version>

  <properties>
    <maven.compiler.source>1.8</maven.compiler.source>
    <maven.compiler.target>1.8</maven.compiler.target>
    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
  </properties>

  <dependencies>
    <dependency>
      <groupId>net.code-story</groupId>
      <artifactId>http</artifactId>
      <version>2.20</version>
    </dependency>
  </dependencies>
</project>
```

1. Ensuite tu te crées comme un grand les répertoires de sources et de tests qui vont bien.
(Tu peux aussi utiliser ta souris, mais ça fait moins hype)

```bash
mkdir -p src/{main,test}/java
```

1. On est là pour faire du web. Alors allons-y pour un Hello World classique (c'est bien le classique aussi parfois).
  Tu peux créer un fichier `index.html` à la racine d'une répertoire
  `app` a coté de ton `pom.xml`

```bash
mkdir app
touch index.html
```

Ensuite tu édites `index.html` et tu colles dedans ça par exemple :

```html
---
layout: default
title: Hello Devoxx
---

<h1>Hello Devoxx</h1>

<p>Je sers une page web avec un projet java en moins de 2 minutes... yes, I can!</p>
```

La section entre les tirets, c'est du Yaml Front Matter.

Dans fluent-http, tout ce qui est dans le répertoire `app` est servi par défaut à la racine de ton serveur.
Si tu y mets du Html il sera servi tel quel, comme pour le Js, les images etc..
Si tu y mets du Less, il sera compilé en Css, du Coffee en Js, du Markdown en Html, etc...

1. Bon on code du java ou pas ? :

  Dans `src/main/java` tu ajoutes une classe `Server`. Celle là par exemple :

```java
import net.codestory.http.*;

public class Server {
  public static void main(String[] args) {
    new WebServer().start();
  }
}
```

TODO: working dir

1. Exécute la classe `Server` et pointe ton navigateur sur http://localhost:8080
Normalement, là, tu as moins envie d'utiliser weblo et tomcat, lundi au boulot.

## Des moustaches coté serveur avec Handlebars

 1. Tu peux utiliser le templating coté serveur
 
```java
public class Server {
  public static void main(String[] args) {
    new WebServer(routes -> routes.get("/", () -> Model.of("conference", "Devoxx"))).start();
  }
}
```

```Html
---
layout: default
title: hello mix-it
---

<h1>Hello [[conference]] !</h1>
```

Le traitement sera fait coté serveur.

Le langage de template ici est du [Handlebars](http://handlebarsjs.com/). Tu peux donc utiliser toutes les commandes
handlebars comme par exemple:

### Boucle

```java
public class Server {
  public static void main(String[] args) {
    new WebServer(routes -> routes.get("/", () -> Model.of("developers", Arrays.asList("David", "Jean-Laurent")))).start();
  }
}
```

Tu peux les afficher avec une boucle comme ceci:

```
[[#each developers]]
  [[.]]
[[/each]]
```

### Java Beans

```java
public class Developer {
  String name;
  int price;
  
  public Developer(String name, int price) {
    this.name = name;
    this.price = price;
  }
}

public class Server {
  public static void main(String[] args) {
    new WebServer(routes -> routes.get("/", () -> Model.of("developers", Arrays.asList(new Developer("David", 1000), new Developer("Jean-Laurent", 1000))))).start();
  }
}
```

Tu peux les afficher avec une boucle comme ceci:

```
[[#each developers]]
  [[name]] [[price]]
[[/each]]
```

Il y a d'autres éléments, mais guère plus, que tu peux voir dans http://handlebarsjs.com/.

### Test

+ Extract Configuration to make it usable for tests

```java
public class Server {
  public static void main(String[] args) {
    new WebServer(new ServerConfiguration()).start();
  }

  public static class ServerConfiguration implements Configuration {
    @Override
    public void configure(Routes routes) {
      routes.get("/", () -> Model.of("developers", Arrays.asList(new Developer("David", 1000), new Developer("Jean-Laurent", 1000))));
    }
  }
}
```

+ e2e test

```xml
<dependency>
  <groupId>net.code-story</groupId>
  <artifactId>simplelenium</artifactId>
  <version>1.25</version>
  <scope>test</scope>
</dependency>
```

```java
import net.codestory.http.WebServer;
import net.codestory.simplelenium.SeleniumTest;
import org.junit.Test;

import static net.codestory.Server.ServerConfiguration;

public class BasketSeleniumTest extends SeleniumTest {
  WebServer webServer = new WebServer(new ServerConfiguration()).startOnRandomPort();

  @Override
  public String getDefaultBaseUrl() {
    return "http://localhost:" + webServer.port();
  }

  @Test
  public void list_developers() {
    goTo("/");

    find(".developer").should().haveSize(2);
    find(".developer").should().contain("David", "Jean-Laurent");
  }
}
```

Don't need to install Chrome, Selenium, PhantomJS or what. It just works.

Pour éviter les conflits en cas de parallelisations des tests, fluent-http possède une méthode `startOnRandomPort` qui permet d'être sûr d'éviter les conflits de bind.

## Service REST Simple

Fluent-http expose vos beans en json par défaut.

Par exemple pour retourner un basket qui pourrait être défini comme cela :


```java
public class Basket {
  long front;
  long back;
  long database;
  long test;
  long hipster;
  long sum;
}
```

Vous pouvez facilement ajouter une ressource à votre serveur http comme cela :

```java
public class BasketResource {
  @Get("/basket")
  public Basket basket() {
    return new Basket();
  }
}
```

Vous le branchez dans vos routes :

```Java
public class ServerConfiguration implements Configuration {
    @Override
    public void configure(Routes routes) {
      routes.add(BasketResource.class);
    }
}
```

Et en appelant http://localhost:8080/basket vous obtenez :

TODO

```json
{
  "front":0,
  "back":0,
  ...
  "sum":0,
}
```

Now that we created our first resource, let's extract another resource for the index page.

```java
import net.codestory.http.annotations.Get;
import net.codestory.http.templating.Model;

public class IndexResource {
  @Get("/")
  public Model index() {
     return Model.of("developers", Arrays.asList(new Developer("David", 1000), new Developer("Jean-Laurent", 1000)));
  }
}
```

And plug it this way:

```java
public class ServerConfiguration implements Configuration {
    @Override
    public void configure(Routes routes) {
      routes
        .add(IndexResource.class)
        .add(BasketResource.class);
    }
}
```

## Tester en intégration ses resources avec RestAssured

Les tests d'intégration au niveau resource sont interessants car ils vérifient si notre application est proprement wrappée dans une resource REST.
On va se concentrer sur les entrées/sorties http.

On utilise pour cela RestAssured, qui propose une API fluent pour décrire ces tests qui sont parfois assez complexes à écrire.

```xml
    <dependency>
      <groupId>com.jayway.restassured</groupId>
      <artifactId>rest-assured</artifactId>
      <version>2.3.4</version>
      <scope>test</scope>
    </dependency>
```

Un test RestAssured à besoin du serveur pour tourner, mais plutôt que de les lancer avec les tests d'intégration, avec faisafe, nous préférons les utiliser comme des tests unitaires.
Il est parfaitement possible d'executer cela dans un test unitaire a condition que le serveur soit capable de démarrer trés rapidement.
Il se trouve que fluent-http est trés bon dans ce domaine.

```java
public class BasketRestTest {
  WebServer webServer = new WebServer(new ServerConfiguration()).startOnRandomPort();

  @Test
  public void query_basket() {
    given().port(webServer.port())
      .when().get("/basket")
      .then().contentType("application/json").statusCode(200);
  }
}

## Angular

Pour ajouter angularjs tu peux utiliser les webjars. Ajoutes à ton pom :

```xml
    <dependency>
      <groupId>org.webjars</groupId>
      <artifactId>angularjs</artifactId>
      <version>1.3.0</version>
    </dependency>
```

Tu peux accéder en utilisant le chemin `/webjars/angularjs/1.3.0/angular.min.js` dans une balise `<script>`.
Tu peux agir de la même manière avec toutes tes dépendances front.

Sinon il y a [bower](http://bower.io/). C'est plus hype mais tu dois déplacer les fichiers à la main.

### Use coffeescript

Tu peux écrire tes controlleurs angular en coffee, avec une syntaxe de classe, cela te permet d'isoler facilement les éléments.
par exemple :

```coffee
angular.module 'devoxx', []

.controller 'BasketController', class
    constructor: (@$http) ->
      @info = "Hello World"
      @basket = {}

    search: ->
      @$http.get("/basket").success (data) =>
        @basket = data

```

Il te suffit ensuite de coller un entête ng-app dans le yaml front matter comme cela :

TODO: ajouter src angular

```yaml
---
title: Hello Devoxx
ng-app: devoxx
---
<div ng-controller="BasketController as controller">
  {{controller.info}}
  <a href="" ng-click="controller.search()">search</a>
</div>
```

# Tests unitaires, intégrations, javascript, d'interfaces !

## Tester unitairement ses resources avec JUnit

Rien d'extraordinaire dans cette section.

Nous utilisons des utilitaires de tests bien connus, comme assertj (un fork de fest-assert) et mockito

Tu peux les ajouter facilement à ton pom en ajoutant :

```xml
    <dependency>
      <groupId>org.assertj</groupId>
      <artifactId>assertj-core</artifactId>
      <version>1.7.0</version>
      <scope>test</scope>
    </dependency>

    <dependency>
      <groupId>org.mockito</groupId>
      <artifactId>mockito-all</artifactId>
      <version>1.10.8</version>
      <scope>test</scope>
    </dependency>
```

```java
public class Developers {
  public Developer find(String email) {
    return Stream.of(findAll()).filter(dev -> email.equals(dev.email)).findFirst().orElse(null);
  }

  Developer[] findAll() {
    try {
      return new ObjectMapper().readValue(Resources.getResource("developers.json"), Developer[].class);
    } catch (IOException e) {
      throw new RuntimeException("Unable to load developers list", e);
    }
  }
}
```


```java
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DevelopersTest {
  @Test
  public void load_developers() {
    Developer[] developers = new Developers().findAll();

    assertThat(developers).hasSize(2);
  }
}
```

## Tester unitairement ses controlleurs Angular avec Karma

On peut tester unitairement son controleur Angular.

On utilise Karma, en conjonction avec Jasmine pour cela.
Il faut que les fichiers de angular soient disponibles dans le path.
Si tu utilises les webjars, c'est le bon moment pour lancer un `bower install` dans ta console.

Utilise le fichier de configuration de karma que tu trouveras sur la clé USB
Si tu n'as pas Chrome sur ta machine tu peux ouvrir le fichier de configuration et remplacer `chrome` par `safari`, `firefox`... ou `ie` !

Le test se lance en tapant `karma start karma.conf.coffee`
(si karma n'est pas dans ton path, tu peux le trouver dans node_modules/karma/bin/karma`)

```coffee
should = chai.should()

describe 'Basket tests', ->
  beforeEach ->
    module 'devoxx'
    inject ($controller) ->
      @controller = $controller 'BasketController'

  it 'should start with an empty basket', ->
    @controller.emails.should.eql []
    @controller.basket.should.eql {}
```

maven frontend plugin


```json
{
    "name": "devoxx-codestory-lab",
    "version": "1.0.0",
    "description": "Node dependencies for devoxx-codestory-lab",
    "main": "index.js",
    "author": "Jean-Laurent de Morlhon && David Gageot",
    "license": "MIT",
    "devDependencies": {
        "chai": "^1.9.1",
        "coffee-script": "^1.7.1",
        "karma": "^0.12.16",
        "karma-coffee-preprocessor": "^0.2.1",
        "karma-jasmine": "^0.2.2",
        "karma-jsmockito-jshamcrest": "0.0.6",
        "karma-phantomjs-launcher": "^0.1.4",
        "jsmockito": "^1.0.5",
        "mocha": "^1.20.1",
        "protractor": "^0.20.1"
    },
    "scripts": {
        "test": "node_modules/mocha/bin/mocha --reporter dot google-scripts/*-test.js"
    },
    "repository": {
        "type": "git",
        "url": "git://github.com/CodeStory/devoxx-quickstart.git"
    }
}
```

```xml
<profiles>
<profile>
  <id>karma</id>
  <activation>
    <property>
      <name>!skipTests</name>
    </property>
  </activation>
  <build>
    <plugins>
      <plugin>
        <groupId>org.codehaus.mojo</groupId>
        <artifactId>exec-maven-plugin</artifactId>
        <version>1.3.2</version>
        <executions>
          <execution>
            <id>extract webjars</id>
            <goals>
              <goal>java</goal>
            </goals>
            <phase>generate-test-resources</phase>
          </execution>
        </executions>
        <configuration>
          <mainClass>misc.ExtractWebjars</mainClass>
        </configuration>
      </plugin>
      <plugin>
        <groupId>com.github.eirslett</groupId>
        <artifactId>frontend-maven-plugin</artifactId>
        <version>0.0.16</version>
        <configuration>
          <workingDirectory>${project.basedir}</workingDirectory>
        </configuration>
        <executions>
          <execution>
            <id>install node and npm</id>
            <goals>
              <goal>install-node-and-npm</goal>
            </goals>
            <phase>generate-test-resources</phase>
            <configuration>
              <nodeVersion>v0.10.29</nodeVersion>
              <npmVersion>1.4.16</npmVersion>
            </configuration>
          </execution>
          <execution>
            <id>npm install</id>
            <goals>
              <goal>npm</goal>
            </goals>
            <phase>generate-test-resources</phase>
            <configuration>
              <arguments>install</arguments>
            </configuration>
          </execution>
          <execution>
            <id>karma tests</id>
            <goals>
              <goal>karma</goal>
            </goals>
            <phase>test</phase>
            <configuration>
              <karmaConfPath>${project.basedir}/src/test/karma.conf.ci.js</karmaConfPath>
            </configuration>
          </execution>
        </executions>
      </plugin>
    </plugins>
  </build>
</profile>
</profiles>
```

### Service http

```coffee
should = chai.should()

describe 'Basket tests', ->
  beforeEach ->
    module 'devoxx'
    inject ($controller, $httpBackend) ->
      @controller = $controller 'BasketController'
      @http = $httpBackend

  it 'should refresh basket after adding a developer', ->
    @http.expectGET('/basket?emails=foo@bar.com').respond '{"test":0,"back":0,"database":0,"front":0,"hipster":0,"sum":0}'
    @controller.add 'foo@bar.com'
    @http.flush()

    @controller.emails.should.eql ['foo@bar.com']
    @controller.basket.should.eql
      test: 0
      back: 0
      database: 0
      front: 0
      hipster: 0
      sum: 0
```

## Ajouter bootstrap

Pour ajouter bootstrap tu peux utiliser les webjars. Ajoutes à ton pom :

```xml
<dependency>
  <groupId>org.webjars</groupId>
  <artifactId>bootstrap</artifactId>
  <version>3.3.0</version>
</dependency>
```

Si tu utilises le YAML Front Matter tu peux carrément le rajouter dans le header :

```YAML
---
title: recruteur.io
styles: ['/webjars/bootstrap/3.1.1/css/bootstrap.css']
---
```

-- David & Jean-Laurent
