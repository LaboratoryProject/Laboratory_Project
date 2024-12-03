FROM openjdk:17-jdk-alpine

WORKDIR /app

# Copy the JAR file from target into the container
COPY target/patient-service-0.0.1-SNAPSHOT.jar patient-service.jar

# Expose port 8089
EXPOSE 8089

# Command to run the application
ENTRYPOINT ["java", "-jar", "patient-service.jar"]
