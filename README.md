# selenium_papago_api
1. Install docker (https://docs.docker.com/install/linux/docker-ce/ubuntu/)<br/>
2. Install heroku<br/>
3. Build jar file(mvn package -DskipTests)<br/>
4. Build Dockerfile (docker build .)<br/>
5. Deploy to heroku (heroku container:push web, heroku container:release web)
<br/><br/>
Reference: https://medium.com/@urbanswati/deploying-spring-boot-restapi-using-docker-maven-heroku-and-accessing-it-using-your-custom-aa04798c0112
