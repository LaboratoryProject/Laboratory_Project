FROM openjdk:17-jdk-alpine

WORKDIR /app

# Copy the JAR file from target into the container
COPY target/analyse-service-0.0.1-SNAPSHOT.jar analyse-service.jar

# Expose port 8082
EXPOSE 8082
# Command to run the application
ENTRYPOINT ["java", "-jar", "analyse-service.jar"]
