FROM openjdk:17-jdk-alpine
RUN mkdir /app
WORKDIR /app
COPY target/currency-converter-0.0.1-SNAPSHOT.jar /app/app.jar
EXPOSE 8080
CMD ["java", "-jar", "/app/app.jar"]
