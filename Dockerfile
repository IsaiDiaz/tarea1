FROM openjdk:17-jdk
COPY target/*.jar backend.jar
ENV DATABASE_URL "jdbc:postgresql://172.17.0.1:5432/tarea1bdd"
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "backend.jar"]