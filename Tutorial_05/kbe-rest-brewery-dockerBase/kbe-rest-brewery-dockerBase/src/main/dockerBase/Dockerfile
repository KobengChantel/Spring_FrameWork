FROM eclipse-temurin:17-jdk-jammy AS builder
WORKDIR application
ADD target/kbe-rest-brewery-dockerBase/kbe-rest-brewery-dockerBase-0.0.1-SNAPSHOT.jar ./
RUN java -Djarmode=layertools -jar kbe-rest-brewery-0.0.1-SNAPSHOT.jar extract



FROM eclipse-temurin:17-jdk-jammy

WORKDIR application
COPY --from=builder application/dependencies/ ./
COPY --from=builder application/spring-boot-loader/ ./
COPY --from=builder application/snapshot-dependencies/ ./
COPY --from=builder application/application/ ./
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "org.springframework.boot.loader.JarLauncher"]


ENV JAVA_OPTS " -Xms512m -Xmx512m -Djava.security.egd=file:/dev/./urandom"



COPY ../../../target/kbe-rest-brewery-0.0.1-SNAPSHOT.jar ./

ENTRYPOINT ["java", "-jar", "kbe-rest-brewery-0.0.1-SNAPSHOT.jar"]




