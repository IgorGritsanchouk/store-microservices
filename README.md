# store-microservices
https://www.youtube.com/watch?v=wJNFtkPqn2g&list=PLuNxlOYbv61g_ytin-wgkecfWDKVCEDmB&index=5
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
http://localhost:8081/api/products?page=1
http://localhost:8081/api/product{P101}

---------   Permissions in ca flow -issue to fix ----
PS C:\DEV\store-microservices> git ls-files --stage catalog-service/mvnw
100644 bd8896bf2217b46faa0291585e01ac1a3441a958 0       catalog-service/mvnw
instead of  100644   IT NEEDS TO BE:  100755
PS C:\DEV\store-microservices> git update-index --chmod=+x catalog-service/mvnw
PS C:\DEV\store-microservices> git ls-files --stage catalog-service/mvnw
100755 bd8896bf2217b46faa0291585e01ac1a3441a958 0       catalog-service/mvnw
100755  --  OK   now
----- it will start containers
PS C:\DEV\store-microservices> task start_infra
then run >  CatalogServiceApplication
to import the data via > Flyway
worked OK
--------------   use taskfile.dev   to simplify commands -------
PS C:\DEV\store-microservices> task --version
3.52.0
PS C:\DEV\store-microservices> task
PS C:\DEV\store-microservices> task test
PS C:\DEV\store-microservices> task start_infra  
PS C:\DEV\store-microservices> task stop_infra
########    docker related   #########
PS C:\DEV\store-microservices> cd deployment/docker-compose
PS C:\DEV\store-microservices\deployment\docker-compose>
docker compose -f infra.yaml up -d
docker compose -f infra.yaml down -d 

======= Testing concepts strategies ========
Controller -> Service -> Repository -> DB
1. INTEGRATION TESTING
Load all components and test all components use:
@SpringBootTest
2. Test only Controller   functionality
Slice Test Annotation  -->  Slice Testing
@WebMVCTest
3. Test only Repository  
@DataJpaTest



