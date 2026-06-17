FROM ubuntu:latest
LABEL authors="Tony"

ENTRYPOINT ["top", "-b"]