FROM maven:3.6.0-jdk-8-slim AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package -DskipTests=true

FROM openjdk:8-jre-slim-stretch
COPY --from=build /home/app/target/test-0.0.1-SNAPSHOT.war /usr/local/lib/test-0.0.1-SNAPSHOT.war
EXPOSE 8080
ENTRYPOINT ["java","-jar","/usr/local/lib/test-0.0.1-SNAPSHOT.war"]