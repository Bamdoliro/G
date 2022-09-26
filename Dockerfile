FROM openjdk:11-jdk
FROM openjdk:11-jdk AS builder
COPY ./ ./
RUN ./gradlew clean
RUN chmod +x ./gradlew
RUN ./gradlew bootJAR

FROM openjdk:11-jdk
COPY --from=builder build/libs/*.jar app.jar
ENTRYPOINT ["java","-jar","-Dspring.profiles.active=prod","/app.jar"]