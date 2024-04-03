# https://www.jetbrains.com/help/idea/dockerize-java-app.html#dockerfile
# https://www.baeldung.com/java-dockerize-app
FROM openjdk:17-jdk-alpine
COPY target/gcloud_ms_api-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]