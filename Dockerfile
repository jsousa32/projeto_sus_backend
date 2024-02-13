FROM maven:3.6.3-openjdk-17
VOLUME /tmp
EXPOSE 8080
COPY target/sus-0.0.1-SNAPSHOT.jar sus.jar
ENTRYPOINT ["java","-jar","/sus.jar"]