# Use an official Gradle image to build the Spring Boot app
FROM gradle:8.10.2-jdk21 AS builder

# Set the working directory inside the container
WORKDIR /Leistungsnachweis

# Copy the rest of the application code
COPY . .

RUN gradle clean shadowJar --no-daemon

# Use an official OpenJDK runtime as a base image for running the application
FROM openjdk:21-jdk-slim

# Set the working directory inside the container
WORKDIR /Leistungsnachweis
# Copy the built JAR file from the first stage. It contains GSON
COPY --from=builder /Leistungsnachweis/build/libs/*.jar Leistungsnachweis.jar

# Run the main class from the JAR
ENTRYPOINT ["java", "-jar", "Leistungsnachweis.jar"]