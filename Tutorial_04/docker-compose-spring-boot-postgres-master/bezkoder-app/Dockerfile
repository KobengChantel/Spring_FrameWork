# FROM maven:3.8.2-jdk-8 # for Java 8
#install the image of the Maven – JDK version
FROM maven:3.8.5-openjdk-17
#path of the working directory
WORKDIR /bezkoder-app
#copy all the files inside the project directory to the container
COPY . .
#execute a command-line inside the container: mvn clean install to install the dependencies in pom.xml.
RUN mvn clean install
#un script mvn spring-boot:run after the image is built.
CMD mvn spring-boot:run