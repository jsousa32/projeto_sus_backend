FROM maven:3.6.3-openjdk-17
VOLUME /tmp
EXPOSE 8080
COPY target/projeto_sus_backend-0.0.1-SNAPSHOT.jar projeto_sus_backend.jar
ENTRYPOINT ["java","-jar","/projeto_sus_backend.jar"]