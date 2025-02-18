# Read Me First
The following was discovered as part of building this project:

* The original package name 'com.socialbotlabs.serverbot.socialbotlabs-bot' is invalid and this project uses 'com.socialbotlabs.serverbot.socialbotlabs_bot' instead.

# Getting Started

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/3.4.2/maven-plugin)
* [Create an OCI image](https://docs.spring.io/spring-boot/3.4.2/maven-plugin/build-image.html)
* [GraalVM Native Image Support](https://docs.spring.io/spring-boot/3.4.2/reference/packaging/native-image/introducing-graalvm-native-images.html)
* [Spring Boot DevTools](https://docs.spring.io/spring-boot/3.4.2/reference/using/devtools.html)
* [Spring Web](https://docs.spring.io/spring-boot/3.4.2/reference/web/servlet.html)
* [Spring Reactive Web](https://docs.spring.io/spring-boot/3.4.2/reference/web/reactive.html)
* [Thymeleaf](https://docs.spring.io/spring-boot/3.4.2/reference/web/servlet.html#web.servlet.spring-mvc.template-engines)
* [Spring Security](https://docs.spring.io/spring-boot/3.4.2/reference/web/spring-security.html)
* [OAuth2 Client](https://docs.spring.io/spring-boot/3.4.2/reference/web/spring-security.html#web.security.oauth2.client)
* [OAuth2 Authorization Server](https://docs.spring.io/spring-boot/3.4.2/reference/web/spring-security.html#web.security.oauth2.authorization-server)
* [OAuth2 Resource Server](https://docs.spring.io/spring-boot/3.4.2/reference/web/spring-security.html#web.security.oauth2.server)
* [Spring Data JPA](https://docs.spring.io/spring-boot/3.4.2/reference/data/sql.html#data.sql.jpa-and-spring-data)
* [Validation](https://docs.spring.io/spring-boot/3.4.2/reference/io/validation.html)
* [Spring Boot Actuator](https://docs.spring.io/spring-boot/3.4.2/reference/actuator/index.html)
* [Spring Cache Abstraction](https://docs.spring.io/spring-boot/3.4.2/reference/io/caching.html)
* [OpenFeign](https://docs.spring.io/spring-cloud-openfeign/reference/)

### Guides
The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)
* [Building a Reactive RESTful Web Service](https://spring.io/guides/gs/reactive-rest-service/)
* [Handling Form Submission](https://spring.io/guides/gs/handling-form-submission/)
* [Securing a Web Application](https://spring.io/guides/gs/securing-web/)
* [Spring Boot and OAuth2](https://spring.io/guides/tutorials/spring-boot-oauth2/)
* [Authenticating a User with LDAP](https://spring.io/guides/gs/authenticating-ldap/)
* [Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)
* [Accessing data with MySQL](https://spring.io/guides/gs/accessing-data-mysql/)
* [Validation](https://spring.io/guides/gs/validating-form-input/)
* [Building a RESTful Web Service with Spring Boot Actuator](https://spring.io/guides/gs/actuator-service/)
* [Caching Data with Spring](https://spring.io/guides/gs/caching/)

### Additional Links
These additional references should also help you:

* [Configure AOT settings in Build Plugin](https://docs.spring.io/spring-boot/3.4.2/how-to/aot.html)
* [Declarative REST calls with Spring Cloud OpenFeign sample](https://github.com/spring-cloud-samples/feign-eureka)

## GraalVM Native Support

This project has been configured to let you generate either a lightweight container or a native executable.
It is also possible to run your tests in a native image.

### Lightweight Container with Cloud Native Buildpacks
If you're already familiar with Spring Boot container images support, this is the easiest way to get started.
Docker should be installed and configured on your machine prior to creating the image.

To create the image, run the following goal:

```
$ ./mvnw spring-boot:build-image -Pnative
```

Then, you can run the app like any other container:

```
$ docker run --rm -p 8080:8080 socialbotlabs-bot:0.0.1-SNAPSHOT
```

### Executable with Native Build Tools
Use this option if you want to explore more options such as running your tests in a native image.
The GraalVM `native-image` compiler should be installed and configured on your machine.

NOTE: GraalVM 22.3+ is required.

To create the executable, run the following goal:

```
$ ./mvnw native:compile -Pnative
```

Then, you can run the app as follows:
```
$ target/socialbotlabs-bot
```

You can also run your existing tests suite in a native image.
This is an efficient way to validate the compatibility of your application.

To run your existing tests in a native image, run the following goal:

```
$ ./mvnw test -PnativeTest
```


### Maven Parent overrides

Due to Maven's design, elements are inherited from the parent POM to the project POM.
While most of the inheritance is fine, it also inherits unwanted elements like `<license>` and `<developers>` from the parent.
To prevent this, the project POM contains empty overrides for these elements.
If you manually switch to a different parent and actually want the inheritance, you need to remove those overrides.

