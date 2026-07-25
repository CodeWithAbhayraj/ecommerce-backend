# Use Java 21
FROM eclipse-temurin:21-jdk

# Working directory
WORKDIR /app

# Copy project files
COPY . .

# Give execute permission to Gradle wrapper
RUN chmod +x gradlew

# Build the project
RUN ./gradlew build -x test

# Expose Spring Boot port
EXPOSE 8080

# Run the Spring Boot application
CMD ["java", "-jar", "build/libs/EcommerceProject2-0.0.1-SNAPSHOT.jar"]