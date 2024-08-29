FROM ubuntu:latest AS build

RUN sudo apt-get update
RUN sudo apt-get install openjdk-17-jdk -y
COPY . .

RUN sudo apt-get install maven -y
RUN mvn clean install

FROM opendk:21-jdk-slim

EXPOSE 8080

COPY --from=build /target/lojaLivros-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java","-jar","app.jar"]