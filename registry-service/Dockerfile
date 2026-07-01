FROM eclipse-temurin:21-jdk

WORKDIR /sprin
COPY . .
RUN ./gradlew bootRun

ENTRYPOINT ["top", "-b"]