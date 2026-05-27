# Minimal Dockerfile used only as a deployment mechanism, since most free
# hosts (e.g. Render) do not run JVM apps without one. No orchestration,
# compose, or custom images involved.

# --- Build stage ---
FROM maven:3.9-eclipse-temurin-21 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# --- Run stage ---
FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=build /app/target/item-directory-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
