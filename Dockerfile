From openjdk:8
EXPOSE 8091
ADD /target/GestionFeedback.jar GestionFeedback.jar
ENTRYPOINT ["java", "-jar", "/GestionFeedback.jar"]