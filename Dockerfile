FROM maven:3.6.3-openjdk-15-slim as builder

WORKDIR /app
COPY pom.xml .
# Use this optimization to cache the local dependencies. Works as long as the POM doesn't change
RUN mvn dependency:go-offline

COPY src/ /app/src/
# RUN mvn package
RUN ["mvn", "install", "-Dmaven.test.skip=true"]

# Use AdoptOpenJDK for base image.
FROM adoptopenjdk/openjdk15:jre-15.0.1_9-alpine

# Copy the jar to the production image from the builder stage.
COPY --from=builder /app/target/*.jar /app.jar

EXPOSE 3000
# Run the web service on container startup.
CMD ["java", "-jar", "/app.jar"]