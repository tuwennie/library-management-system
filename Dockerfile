FROM eclipse-temurin:17-jdk-jammy
LABEL maintainer="tuba@library.com"
COPY target/library-management-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]