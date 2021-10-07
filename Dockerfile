FROM openjdk:11-jre
COPY /target/*.jar /usr/share/app.jar
EXPOSE 8081
CMD ["java","-jar","/usr/share/app.jar"]
