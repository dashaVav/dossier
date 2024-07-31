FROM openjdk:21-slim
LABEL authors="dashavav"
COPY target/dossier*.jar /dossier.jar
ENTRYPOINT ["java", "-jar", "/dossier.jar"]