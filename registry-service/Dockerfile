FROM gradle:9.5.1-jdk21 AS builder

WORKDIR /app

COPY settings.gradle ./
COPY gradle ./gradle
COPY gradlew ./
COPY registry-service/build.gradle ./registry-service/

RUN --mount=type=cache,target=/home/gradle/.gradle ./gradlew :registry-service:dependencies --no-daemon || true

COPY registry-service/src ./registry-service/src

RUN --mount=type=cache,target=/home/gradle/.gradle ./gradlew :registry-service:bootJar -x test --no-daemon

FROM eclipse-temurin:21-jre-noble

WORKDIR /app

COPY --from=builder /app/registry-service/build/libs/*.jar app.jar


ENTRYPOINT ["java", "-jar", "app.jar"]
