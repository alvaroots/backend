# Etapa de construcción con Gradle y JDK 21
FROM gradle:8-jdk21 AS build
COPY . /app
WORKDIR /app
RUN chmod +x gradlew
RUN ./gradlew clean build

# Etapa de ejecución con JRE 21
FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=build /app/build/libs/*.jar app.jar
ENV PORT=8080
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]
