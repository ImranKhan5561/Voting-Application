FROM openjdk:21-jdk-slim
WORKDIR /app
COPY target/your-app.jar /app/app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/app.jar"]