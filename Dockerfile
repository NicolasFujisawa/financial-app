FROM maven:3.8.6-eclipse-temurin-11-alpine as builder

WORKDIR /app

COPY . .

RUN mvn clean install -DskipTests

FROM openjdk:11.0-jdk-slim

ARG PROFILE
ARG ADDITIONAL_OPTS

ENV PROFILE=${PROFILE}
ENV ADDITIONAL_OPTS=${ADDITIONAL_OPTS}

WORKDIR /financial/api

COPY --from=builder /app/target/financial-app-*.jar financial-app.jar

SHELL ["/bin/sh", "-c"]

EXPOSE 5005 8080

CMD java ${ADDITIONAL_OPTS} -jar financial-app*.jar --spring.profiles.active=${PROFILE}
