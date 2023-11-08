FROM eclipse-temurin:17-jdk-jammy as build-image

WORKDIR /to-build-app
COPY . .
RUN chmod +x ./mvnw
RUN ./mvnw clean install
RUN ./mvnw dependency:go-offline package -DskipTests

FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=build-image /to-build-app/target/*.jar /app/app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/app.jar"]

