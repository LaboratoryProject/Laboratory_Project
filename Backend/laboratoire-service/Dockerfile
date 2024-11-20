FROM openjdk:17-jdk-alpine

WORKDIR /app

# Copy the JAR file from target into the container
COPY target/laboratoire-service-0.0.1-SNAPSHOT.jar laboratoire-service.jar

# Expose port 8081
EXPOSE 8081

# Command to run the application
ENTRYPOINT ["java", "-jar", "laboratoire-service.jar"]
