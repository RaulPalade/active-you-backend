FROM openjdk:19

# Copia il file JAR del microservizio nella directory del container
COPY build/libs/UserService-0.0.1-SNAPSHOT-plain.jar /app.jar

# Esposizione della porta 8080
EXPOSE 8080

# Comando per avviare il microservizio
CMD ["java", "-jar", "/app.jar"]
