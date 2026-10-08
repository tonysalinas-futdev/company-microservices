
FROM gradle:9.5.1-jdk21 AS builder

WORKDIR /app
COPY settings.gradle ./
COPY .env ./
COPY gradle ./gradle
COPY gradlew ./
COPY employee-service/build.gradle ./employee-service/
COPY avro-schemas-repo/build.gradle ./avro-schemas-repo/

RUN --mount=type=cache,target=/home/gradle/.gradle ./gradlew :employee-service:dependencies --no-daemon || true

COPY avro-schemas-repo/src ./avro-schemas-repo/src
COPY employee-service/src ./employee-service/src

RUN --mount=type=cache,target=/home/gradle/.gradle ./gradlew :employee-service:bootJar -x test --no-daemon

FROM eclipse-temurin:21-jre-noble

WORKDIR /app

COPY --from=builder /app/employee-service/build/libs/*.jar app.jar


ENTRYPOINT ["java", "-jar", "app.jar"]
