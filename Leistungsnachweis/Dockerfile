# Use an official OpenJDK 22 image as the base
FROM openjdk:22-jdk-slim as builder

# Set the working directory
WORKDIR /Leistungsnachweis

# Copy the Gradle build files and source code into the image
COPY ./Leistungsnachweis/build.gradle ./build.gradle
COPY ./Leistungsnachweis/settings.gradle ./settings.gradle
COPY ./Leistungsnachweis/gradlew ./gradlew
COPY ./Leistungsnachweis/gradlew.bat ./gradlew.bat
COPY ./Leistungsnachweis/gradle ./gradle
COPY ./Leistungsnachweis/src ./src

# Build the application
RUN ./gradlew build --no-daemon

# Final image
FROM openjdk:22-jdk-slim

# Copy the JAR file from the builder image
COPY --from=builder /Leistungsnachweis/build/libs/*.jar app.jar

# Start the application
CMD ["java", "-jar", "app.jar"]
