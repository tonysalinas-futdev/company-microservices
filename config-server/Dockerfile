
FROM gradle:9.5.1-jdk21 AS builder

WORKDIR /app

COPY settings.gradle ./

COPY config-repo ./config-repo
COPY gradle ./gradle
COPY gradlew ./
COPY config-server/build.gradle ./config-server/

RUN --mount=type=cache,target=/home/gradle/.gradle ./gradlew :config-server:dependencies --no-daemon || true

COPY config-server/src ./config-server/src

RUN --mount=type=cache,target=/home/gradle/.gradle ./gradlew :config-server:bootJar -x test --no-daemon

FROM eclipse-temurin:21-jre-noble

WORKDIR /app

COPY --from=builder /app/config-server/build/libs/*.jar app.jar
COPY --from=builder /app/config-repo ./config-repo

ENTRYPOINT ["java", "-jar", "app.jar"]
