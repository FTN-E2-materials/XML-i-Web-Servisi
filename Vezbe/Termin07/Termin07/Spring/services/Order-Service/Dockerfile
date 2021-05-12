FROM maven:3.6.3-ibmjava-8-alpine AS appBuild

LABEL maintainer="danijelradakovic@uns.ac.rs"

WORKDIR /usr/src/order_service
COPY . .
RUN mvn package -DskipTests

FROM openjdk:8-jdk-alpine AS appRuntime
WORKDIR /app
COPY --from=appBuild /usr/src/order_service/target/order_service.jar ./
EXPOSE 8080
CMD java -jar order_service.jar

