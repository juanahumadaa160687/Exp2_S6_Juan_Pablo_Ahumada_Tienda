FROM openjdk:25-ea-oracle

WORKDIR /app

COPY target/tienda-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]