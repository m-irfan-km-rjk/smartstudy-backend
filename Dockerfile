# ---- Build stage ----
FROM gradle:8.5-jdk21 AS build

WORKDIR /app

# Copy only necessary files first (for caching)
COPY build.gradle settings.gradle gradlew ./
COPY gradle ./gradle

RUN chmod +x gradlew

# Download dependencies (cached layer)
RUN ./gradlew dependencies --no-daemon

# Copy source code
COPY src ./src

# Build the application
RUN ./gradlew build -x test --no-daemon


# ---- Run stage ----
FROM openjdk:21-jdk-slim

WORKDIR /app

# Copy jar from build stage
COPY --from=build /app/build/libs/*.jar app.jar

# Expose port (Render will override with PORT env)
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]