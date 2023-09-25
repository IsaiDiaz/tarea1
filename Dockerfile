FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
ARG DEPENDENCY=target/dependency
COPY ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY ${DEPENDENCY}/META-INF /app/META-INF
COPY ${DEPENDENCY}/BOOT-INF/classes /app
ENV DATABASE_URL "jdbc:postgresql://172.17.0.1:5432/tarea1bdd"
ENV PORT=8080
EXPOSE $PORT
ENTRYPOINT ["java", "-cp", "app:app/lib/*", "com.example.tarea1.Tarea1Application"]