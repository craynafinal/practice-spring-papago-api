FROM selenium/standalone-chrome
VOLUME /tmp
COPY target/papago-api-0.0.1-SNAPSHOT.jar papago-api-0.0.1-SNAPSHOT.jar
CMD ["java","-Djava.security.egd=file:/dev/./urandom", "-jar", "/papago-api-0.0.1-SNAPSHOT.jar"]
