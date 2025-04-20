FROM openjdk:21
COPY build/libs/*.jar app.jar
COPY ./.env /.env
ENTRYPOINT ["java", "-jar", "/app.jar"]