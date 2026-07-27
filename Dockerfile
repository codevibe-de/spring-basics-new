# syntax=docker/dockerfile:1

# --- Build stage: produce the WAR with the Maven wrapper ---
FROM eclipse-temurin:17-jdk AS build
WORKDIR /app

# Cache dependencies first
COPY .mvn/ .mvn/
COPY mvnw pom.xml ./
RUN ./mvnw -B dependency:go-offline

# Build the WAR
COPY src/ src/
RUN ./mvnw -B clean package -DskipTests


# --- Runtime stage: Tomcat 10.1 (Jakarta EE 10) on Java 17 ---
FROM tomcat:10.1-jre17

# Remove the default apps that ship with Tomcat
RUN rm -rf /usr/local/tomcat/webapps/*

# Deploy the WAR as the ROOT context (served at http://host:8080/)
COPY --from=build /app/target/pizza-app-045.war /usr/local/tomcat/webapps/ROOT.war

# Expose port and run Tomcat
EXPOSE 8080
CMD ["catalina.sh", "run"]
