# store-microservices
Microservices architecture utilizing spring boot, Rabbit Mq, Kubernates deployment
https://www.drawio.com/         # draw design

PS C:\DEV\store-microservices> mvn -version
Apache Maven 3.8.5 (3599d3414f046de2324203b78ddcf9b5e4388aa0)
Maven home: C:\Apps\apache-maven-3.8.5

!!!!!!  Installing mvn wrapper:  !!!!!!
mvn wrapper:wrapper
PS C:\DEV\store-microservices> mvn wrapper:wrapper
!!!!  Building using wrapper  !!!!
./mvnw -ntp verify       ###just to verify build
PS C:\DEV\store-microservices> ./mvnw clean package
PS C:\DEV\store-microservices> ./mvnw clean package -DskipTests
BUILD SUCCESS
-- format before build --  mvn spotless:apply  or   ./mvnw spotless:apply
PS C:\DEV\store-microservices>./mvnw spotless:apply
-- skip running tests
mvn package -DskipTests
-- skipping compiling test 
mvn package -Dmaven.test.skip=true
=====   Start all the required containters by running app in test mode :
Start application by running  :  TestCatalogServiceApplication  
http://localhost:8081/actuator/info     // commit information # useful
http://localhost:8081/actuator
http://localhost:8081/actuator/health
http://localhost:8081/actuator/metrics
// documentation
http://localhost:8081/swagger-ui/index.html

########    docker related   #########
PS C:\DEV\store-microservices> cd deployment/docker-compose
PS C:\DEV\store-microservices\deployment\docker-compose>
docker compose -f infra.yaml up -d
docker compose -f infra.yaml down -d 



