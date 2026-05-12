# Stage 1: Build the application
FROM eclipse-temurin:21-jdk as build
WORKDIR /app

# Copy the project files
COPY . .

# Ensure the wrapper is executable and build the project
RUN chmod +x mvnw
RUN ./mvnw clean package -DskipTests

# Stage 2: Run the application
FROM eclipse-temurin:21-jre
WORKDIR /app

# Copy the built jar file from the build stage
# Note: If your jar builds to a different directory or name, adjust this path
COPY --from=build /app/target/*.jar app.jar

# Expose the port your app runs on (Render defaults to 10000, but 8080 is standard for Spring)
EXPOSE 8080

# Start the application
ENTRYPOINT ["java", "-jar", "app.jar"]