# Use an official OpenJDK 22 image as the base
FROM openjdk:22-jdk-slim as builder

# Set the working directory
WORKDIR /Leistungsnachweis

# Build the application
COPY ./Leistungsnachweis/gradlew ./Leistungsnachweis/gradlew

RUN chmod +x ./Leistungsnachweis/gradlew


# Final image
FROM openjdk:22-jdk-slim

# Copy the JAR file from the builder image
COPY --from=builder /Leistungsnachweis/build/libs/*.jar app.jar

# Start the application
CMD ["java", "-jar", "app.jar"]
