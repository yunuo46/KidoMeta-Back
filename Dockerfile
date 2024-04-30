# FROM bellsoft/liberica-openjdk-alpine:21
# FROM gradle:8.4-jdk-alpine

CMD ["./gradlew", "clean", "build"]

COPY build/libs/*.jar app.jar

EXPOSE 3000

ENTRYPOINT ["java","-jar","/app.jar"]