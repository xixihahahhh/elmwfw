FROM openjdk:11
VOLUME /tmp
ADD target/elmProjectHS-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8761 8762 8763
ENTRYPOINT ["java","-jar","/app.jar","--spring.profiles.active=${PROFILE}"] 