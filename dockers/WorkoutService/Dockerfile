FROM openjdk:19

# Copia il file JAR del microservizio nella directory del container
COPY build/libs/WorkoutService-0.0.1-SNAPSHOT-plain.jar /app.jar

# Esposizione della porta 8081
EXPOSE 8081

# Comando per avviare il microservizio
CMD ["java", "-jar", "/app.jar"]