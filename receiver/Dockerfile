
FROM adoptopenjdk/openjdk11 AS build 
WORKDIR /app 
COPY . . 
RUN apt-get update && apt-get install -y maven
RUN mvn clean package


CMD ["java", "-jar", "/app/target/ReceiverService-1.0.jar"]

