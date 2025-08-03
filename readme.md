to install dependency 
mvn clean install

to run 
mvn spring-boot:run 

spring init --dependencies=web --build=maven --java-version=21 my-app
cd my-app
mvn spring-boot:run


curl https://start.spring.io/starter.zip \
  -d dependencies=web \
  -d type=maven-project \
  -d javaVersion=21 \
  -d baseDir=my-app \
  -o my-app.zip

unzip my-app.zip
cd my-app
mvn spring-boot:run
