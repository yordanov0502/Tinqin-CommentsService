FROM amazoncorretto:21-alpine
LABEL authors="yordanov0502"
WORKDIR /app
EXPOSE 8081
COPY rest/target/rest-0.0.1-SNAPSHOT.jar /app/comments.jar

ENTRYPOINT ["java", "-jar", "/app/comments.jar"]