
FROM adoptopenjdk/openjdk11 AS build 
WORKDIR /app 
COPY . . 
RUN apt-get update && apt-get install -y maven
RUN mvn clean package

#ENTRYPOINT [ "tail", "-f", "/dev/null" ]
#ENTRYPOINT ["mvn", "exec:java", "-Dexec.mainClass=SenderService"]
CMD ["java", "-jar", "/app/target/SenderService-1.0.jar"]

