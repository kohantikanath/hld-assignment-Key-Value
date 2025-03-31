# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-jdk-alpine

# Set the working directory in the container
WORKDIR /app

# Copy the project's JAR file into the container at /app
COPY target/hld-assignment-0.0.1-SNAPSHOT.jar /app/hldassignment.jar

# Make port 7171 available to the world outside this container
EXPOSE 7171

# Run the JAR file
ENTRYPOINT ["java", "-jar", "hldassignment.jar"]