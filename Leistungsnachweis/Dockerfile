# Stage 1: Build
FROM openjdk:22-jdk-slim AS builder

# Set the working directory
WORKDIR /Leistungsnachweis

# Copy all files from the current directory to the container's working directory
COPY . .

# Make the gradlew file executable
RUN chmod +x gradlew

# Build the application
RUN ./gradlew build

# Stage 2: Run
FROM openjdk:22-jdk-slim

# Set the working directory
WORKDIR /Leistungsnachweis

# Copy the built JAR file from the builder stage
COPY --from=builder /Leistungsnachweis/src/main/java/service/UseTimeCalculator.jar /Leistungsnachweis/UseTimeCalculator.jar

# Run the application
CMD ["java", "-jar", "UseTimeCalculator.jar"]
