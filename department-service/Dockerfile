
FROM gradle:9.5.1-jdk21 AS builder

WORKDIR /app
COPY settings.gradle ./
COPY .env ./
COPY gradle ./gradle
COPY gradlew ./
COPY depatment-service/build.gradle ./department-service/
COPY avro-schemas-repo/build.gradle ./avro-schemas-repo/

RUN --mount=type=cache,target=/home/gradle/.gradle ./gradlew :department-service:dependencies --no-daemon || true

COPY avro-schemas-repo/src ./avro-schemas-repo/src
COPY department-service/src ./department-service/src

RUN --mount=type=cache,target=/home/gradle/.gradle ./gradlew :department-service:bootJar -x test --no-daemon

FROM eclipse-temurin:21-jre-noble

WORKDIR /app

COPY --from=builder /app/department-service/build/libs/*.jar app.jar


ENTRYPOINT ["java", "-jar", "app.jar"]
