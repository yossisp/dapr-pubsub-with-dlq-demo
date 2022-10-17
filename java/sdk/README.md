# Dapr pub/sub example using dead letter queue and Redis Streams/Kafka

This repo is essentially a small subset of the official Dapr quickstarts [repo](https://github.com/dapr/quickstarts).
I also wrote a [blog post](https://www.spektor.dev/how-to-set-up-dapr-with-dead-letter-queue-in-java/) which provides a tutorial around the code.
This quickstart includes one publisher:

- Java client message generator `checkout`

And one subscriber:

- Java subscriber `order-processor`

## Pre-requisites

* [Dapr and Dapr Cli](https://docs.dapr.io/getting-started/install-dapr-cli/).
* Java JDK 11 (or greater):
    * [Microsoft JDK 11](https://docs.microsoft.com/en-us/java/openjdk/download#openjdk-11)
    * [Oracle JDK 11](https://www.oracle.com/technetwork/java/javase/downloads/index.html#JDK11)
    * [OpenJDK 11](https://jdk.java.net/11/)
* [Apache Maven](https://maven.apache.org/install.html) version 3.x.

### Run Java message publisher app with Dapr (Redis Streams)

```bash
cd java/sdk/checkout
mvn clean install
dapr run --app-id checkout --components-path ../../../components-redis -- java -jar target/CheckoutService-0.0.1-SNAPSHOT.jar
```

### Run Java message subscriber app with Dapr (Redis Streams)

```bash
cd java/sdk/order-processor
mvn clean install
dapr run --app-port 8080 --app-id order-processor --components-path ../../../components-redis -- java -jar target/OrderProcessingService-0.0.1-SNAPSHOT.jar 2>&1 | tee log
```
### Run Java message publisher app with Dapr (Kafka)
#### Start Kafka
```bash
docker-compose up
```

```bash
cd java/sdk/checkout
mvn clean install
dapr run --app-id checkout --components-path ../../../components-kafka -- java -jar target/CheckoutService-0.0.1-SNAPSHOT.jar
```

### Run Java message subscriber app with Dapr (Redis Streams)
#### Start Kafka
```bash
docker-compose up
```

```bash
cd java/sdk/order-processor
mvn clean install
dapr run --app-port 8080 --app-id order-processor --components-path ../../../components-kafka -- java -jar target/OrderProcessingService-0.0.1-SNAPSHOT.jar 2>&1 | tee log
```
