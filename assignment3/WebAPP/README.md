# WebAPP

Technologies
* [Spring Boot](https://spring.io/projects/spring-boot) 
* [Angular](https://angular.io/)
* [Maven](https://maven.apache.org/)
* [JPA](https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-data-jpa)

Spring Boot for backend (DerbyDB + JPA). \
Angular for frontend.


Running: 
- DB (`docker-compose up`) in the **parent** directory (Create DB in Derby that match spring.datasource.url in WebAPP/spring/src/main/resources/application.properties).
- Backend (run application in the inteliJ after importing Maven dependancies) or (create jar by typing `mvn clean package`) in the **spring** directory.
- Frotnend (install dependancies `npm install` and run `ng serve`) in the **frontend** diectory. 