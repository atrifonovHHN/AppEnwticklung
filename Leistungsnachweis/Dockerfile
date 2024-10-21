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
