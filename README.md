# Webflux Demo

Welcome to **Hello World Enterprise Edition™**, an unnecessarily complex implementation that lifts the traditional "hello world" example into a microservice setting. The architecture is deliberately built in a way to resemble a typical enterprise web service. Initially based on Spring WebMVC, the project is intended as a demonstration how (legacy) Spring applications can be migrated to the more modern WebFlux stack.

## Repository Organization

This git repository comprises three branches:
- **main:** Fully synchronous application in its initial state, based on Spring WebMVC and running on Tomcat.
- **webflux-migration:** The same application migrated to the Spring Webflux stack powered by Netty. Core logic is fully asynchronous using Reactor as the reactive streams implementation of choice.
- **webflux-kotlin-migration:** Technology stack identical to **webflux-migration** with the exception that Kotlin is used instead of Java, leveraging the power of coroutines to express asynchronous logic in a more natural way.

The commit history is structured in a way that the migration process from Spring WebMVC to Webflux (and from Java to Kotlin, respectively) becomes clear. Each commit represents a self-contained, manageable increment towards a reactive application, whereby every step provides tested, working software.

By convention, production code can be found under `src/main` and test code under `src/test`.

## Getting Started

This project uses Maven as build tool. Thus, it can be built using
```bash
mvn clean install
```
and run via
```bash
mvn exec:java -Dexec.mainClass="com.example.webfluxdemo.GreetingApplication"
```

Since this sample service is designed to be part of a microservice environment, it depends on multiple external HTTP APIs, namely `/api/users` and `/api/translations`. Implementing these services would have exceeded the scope of this project, which is why they are statically provided by a mock server for testing purposes. The non-blocking [MockServer](https://mock-server.com/) based on Netty is used so that outgoing HTTP calls do not become the bottleneck when load testing the main application.

Start the mock server with the following command:
```bash
mvn mockserver:run
```
Note that the mocked responses are configured to be delayed by 300 to 500 ms in order to mimic a productive setting (which would involve database queries for example).

As soon as the main application as well as the mock server are up and running, requests can be sent towards the greeting API under `http://localhost:8080/hello/42` (currently, only users with ID 42 and 123 are served by the mocks).

### Using IntelliJ

This project also comes with run configurations for IntelliJ. In this case, simply start both "Run Mockserver" and "GreetingApplication" via the IDE to get the application running.

## Load Testing

You would like to see for yourself how the application performs in comparison after the migration to Spring Webflux? Just start up your favorite load testing tool and direct some GET requests to the running service. I personally enjoyed using [Cassowary](https://github.com/rogerwelin/cassowary) because it makes generating a specific load to a static URL super easy. From the command line, run
```bash
cassowary run -u http://localhost:8080/hello/42 -c 200 -n 2000
```
to send a total of 2000 requests, with a maximum of 200 sent concurrently. The output should be similar to the following:
```
Starting Load Test with 2000 requests using 200 concurrent users

 100% |████████████████████████████████████████| [8s:0s]            8.3653469s


 TCP Connect.....................: Avg/mean=4.83ms      Median=1.00ms   p(95)=29.00ms
 Server Processing...............: Avg/mean=822.18ms    Median=820.00ms p(95)=837.00ms
 Content Transfer................: Avg/mean=1.69ms      Median=0.00ms   p(95)=8.00ms

Summary:
 Total Req.......................: 2000
 Failed Req......................: 0
 DNS Lookup......................: 15.00ms
 Req/s...........................: 239.08
```