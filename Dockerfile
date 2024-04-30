FROM bellsoft/liberica-openjdk-alpine:21

CMD ["./gradlew", "clean", "build"]

VOLUME /tmp

COPY build/libs/*.jar app.jar

EXPOSE 3000

ENTRYPOINT ["java","-jar","/app.jar"]