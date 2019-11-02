# Start with a base image containing Java runtime
FROM openjdk:8-jdk-alpine

# Add Maintainer Info
LABEL maintainer="techmonks@gmail.com"

# Add a volume pointing to /user-service
VOLUME /user-service

# Make port 8080 available to the world outside this container
EXPOSE 5050

# The application's jar file
ARG JAR_FILE=build/libs/userservice-0.0.1-SNAPSHOT.jar

# Add the application's jar to the container
ADD ${JAR_FILE} userservice.jar

# Run the jar file 
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","-Dspring.profiles.active=default", "/userservice.jar"]
