# Run

```
mvn compile exec:java
```

# Build to deploy

```
mvn clean package dependency:copy-dependencies
```

# Add the maven dependency:

```xml
<dependency>
  <groupId>net.code-story</groupId>
  <artifactId>http</artifactId>
  <version>1.39</version>
</dependency>
```

# Start a web server:

```java
public static void main(String[] args) {
  new WebServer().start();
}
```

# YAML Front Matter

```yaml
---
layout: default
title: Chasseur de dev.io
styles: [/webjars/bootstrap/3.1.1/css/bootstrap.css]
developers:
 - name: David Gageot
   city: Paris
   tags: [Java, Cloud]

 - name: Jean-Laurent de Morlhon
   city: Houilles
   tags: [CoffeeScript, Java]
---
```

# Handlebars

```
[[#each developers]]
+ [[name]] [[price]]
[[/each]]

```

```
[[#each developers.tags]][[.]] [[/each]]

```

# Markdown

Un lien vers Google Maps

```
<@ Paris>
```

# Add bootstrap with a webjar

```xml
<dependency>
  <groupId>org.webjars</groupId>
  <artifactId>bootstrap</artifactId>
  <version>3.1.1</version>
</dependency>
```

# Convert yaml to json

```bash
npm install -g yamljs
yaml2json file.yml > file.json
```

# AngularJS

```html
<div ng-controller="BasketController as basket" ng-cloak>
</div>

<script src="/webjars/angularjs/1.2.16/angular.min.js"></script>
<script src="app.coffee"></script>
```

```css
[ng-cloak], .ng-cloack {
  display: none !important;
}
```

```yaml
---
ng-app: devoxx
---
```

```coffee
angular.module 'devoxx', []

.controller 'BasketController', class
    constructor: ->
      console.log 'it works!'
```

# Read yaml or json data from java code

```java
Site.get().getData().get("developers");

TypeConvert.convertValue(Site.get().getData().get("developers"), Developer[].class);
```

# AngularJs directive

```coffee
.directive 'score', ->
    restrict: 'E'
    scope:
      value: '='
      category: '@'
    templateUrl: '/directives/score.html'
```

```html
<span class="box"
      ng-class="'box-' + i + ' ' + category"
      ng-show="i <= value"
      ng-repeat="i in [1,2,3,4,5]"></span>
```

# Protractor test

``` coffee
```