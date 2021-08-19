# Keyword Calculator
The proposal of this project is calculate how reliable is a keyword using the public API of Amazon Completion.

# How to run
1. Clone repository:
```sh
$ git clone https://github.com/Soares-Victor/room-occupancy-manager-api.git
```

2. Change Directory:
```sh
$ cd keyword-calculator/
```

3. Build:
```sh
$ ./gradlew build
```

4. Execute Directly:
```sh
$ ./gradlew bootRun
``` 

5. Test your first request:
```sh
$ curl --location --request GET 'http://localhost:8080/keyword-calculator/estimates?keyword=iphone+charger'
``` 

6. Or access the swagger, following the address: [Swagger UI](http://localhost:8080/keyword-calculator/swagger-ui.html)

# Architecture about this project
* Gradle
* Java 8
* Spring Webflux (Reactive Mode)
* Lombok
* Swagger UI

# Coming Soon
* All Stack deployed to Amazon
* Complete CI/CD
* Docker and Kubernetes