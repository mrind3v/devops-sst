FROM eclipse-temurin:21-jdk AS build

WORKDIR /build
COPY Main.java .
RUN javac --add-modules jdk.httpserver Main.java

FROM eclipse-temurin:21-jre

WORKDIR /app
COPY --from=build /build/Main.class .

EXPOSE 8080
CMD ["java", "--add-modules", "jdk.httpserver", "Main"]
