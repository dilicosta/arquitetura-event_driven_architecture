FROM maven:3.9.6-eclipse-temurin-21-alpine

# Set the working directory to /app
WORKDIR /app

# Copy the source code to the container
COPY . .

# Build the application with Maven
RUN mvn package -Dmaven.test.skip

# Set environment variables if needed
#ENV MYSQL_URL=jdbc:mysql://mysql-balance:3306/balance
#ENV KAFKA_URL=kafka:9092

# Expose default Spring Boot port
#EXPOSE 8080

# Run the jar file
CMD ["java", "-jar", "target/kafka-0.0.1-SNAPSHOT.jar", "--spring.profiles.active=prod"]


