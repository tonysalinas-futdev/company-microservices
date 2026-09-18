FROM gradle:9-jdk21

WORKDIR /app

COPY build.gradle  ./

COPY gradle gradle

RUN --mount=type=cache,target=/home/gradle/.gradle gradle dependencies --no-daemon

COPY . .

RUN --mount=type=cache,target=/home/gradle/.gradle gradle clean build --no-daemon

CMD ["gradle", "bootRun", "--no-daemon"]