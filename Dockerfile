FROM openjdk:8
EXPOSE 8080
ADD target/demo-0.0.1-SNAPSHOT.jart app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]