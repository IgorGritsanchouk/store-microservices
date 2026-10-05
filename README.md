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
http://localhost:8081/api/products
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
PS C:\DEV\store-microservices> task start    # infra and apps together
PS C:\DEV\store-microservices> task stop
task: [build] mvnw.cmd -pl catalog-service spring-boot:build-image -DskipTests
########    docker related   #########
PS C:\DEV\store-microservices> docker stack ls
docker stack rm <stack-name>
PS C:\DEV\store-microservices> task format
PS C:\DEV\store-microservices> ./mvnw clean package
docker ps -a
./mvnw.cmd -pl catalog-service spring-boot:build-image -DskipTests

PS C:\DEV\store-microservices> cd deployment/docker-compose
PS C:\DEV\store-microservices\deployment\docker-compose>
docker compose -f infra.yaml up -d
docker compose -f infra.yaml down -d 

1.In docker generate user name and token
app.dpscker.com/accounts/igr025/settings/
Account Information -> Personal access tokens -> Generate new token
DOCKERHUB_USERNAME     igr023
DOCKERHUB_TOKEN        dckr_pat_CnyQjWQj......
2. github settingd
https://github.com/IgorGritsanchouk/store-microservices
Settings -> Secrets and variables -> Actions - new repository secret
https://github.com/docker/login-action
3.  add new credentials to github/workflows/catalog-service.yml  
    name: Login to Docker Hub
    name: Build and Publish Docker Image
so that CI pipeline could log into docker repository and push  newly build image !!!!!
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



