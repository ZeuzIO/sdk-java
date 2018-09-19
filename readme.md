# Zeuz.io Library for Java
Java SDK client for handling services of [zeuz.io](https://zeuz.io).

* [Homepage](https://zeuz.io)
* [API Docs](https://zeuz.io/api)
* [Github Repository](https://github.com/zeuzio)


## Features
This library supports the following functionalities:
* Provides easy-to-use HTTP clients for our authentication protocol.
* Conversation of json to Java Models
* Managing services through the API
    * list services
    * allocate services
    * deallocate services
    * reserve services
    * unreserve services
    * start services
    * restart services
    * stop services
    * reinstall services
    * gather infos about a service
    * gather the status of a service

## Quickstart
To call any of the supported zeuz.io endpoints add a corresponding
client library artifact as a dependency to your project.

If you are using Maven add this to your pom.xml file

```xml
<project>
    <repositories>
        <repository>
            <id>zzapirepository</id>
            <url>https://mymavenrepo.com/repo/IxJHbV78YloJCngOkFiX/</url>
        </repository>
    </repositories>
</project>
...
<dependency>
    <groupId>com.gportal.zeuz</groupId>
    <artifactId>zzapi_sdk</artifactId>
    <version>1.0-SNAPSHOT</version>
</dependency>
```

``Important:``
We currently do not provide Grade / SBT repositories, feel free to contact
us if are working with any of these technologies.

## Authentication / first calls
1. Before you begin, you need an zeuz.io account. Please contact the zeuz team for informations about how to create an zeuz account and retrieve your zeuz credentials.
2. The recommended way to use the zeuz SDK for Java in your project is to consume it from Maven. Import the zzapi_sdk and specifiy the SDK Maven modules that your project needs in the dependencies.
3. ex.
```java
String appToken = "<app token>";
String secretToken = "<secret token>";

try {
    Api api = new Api(appToken, secretToken);
    String gameProfileId = "123";
    String serverGroupId = "123";

    ApiResponse apiResponse =
        api.requestServiceList(gameProfileId, serverGroupId);

    if(apiResponse.isOk()) {
        // <insert awesome stuff here>
    }
}
catch(ApiException apiException) {
    apiException.printStacktrace();
}

```

## Java Versions
Java 8 or above is required for using the clients in this repository.

## Support Platforms
* Android
* Windows on x86_64
* Linux on x86_64
* Windows on x86_64
* Mac OSX on x86_64

## Testing
This library provides a small ``ApiTest`` class, which demonstrates
how to call specific endpoints.

## Building From Source
Once you check out the code from GitHub, you can build it using Maven. To disable the GPG-signing in the build, use:
```
mvn clean install -Dgpg.skip=true
```