FROM openjdk:11-jdk AS builder
COPY ./ ./
RUN ./gradlew clean
RUN chmod +x ./gradlew
RUN ./gradlew bootJAR
ENV TZ=Asia/Seoul
FROM openjdk:11-jdk
COPY --from=builder build/libs/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app.jar"]