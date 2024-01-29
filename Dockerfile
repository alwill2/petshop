FROM openjdk:17-jdk-alpine
ADD build/libs/*.jar petShop.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/petShop.jar"]