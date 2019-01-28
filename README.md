# selenium_papago_api
## Condition
- Install docker
    - https://docs.docker.com/install/linux/docker-ce/ubuntu/
- Install heroku
- Login to heroku, heroku container, and docker
    - heroku login
    - heroku container:login
    - docker login
- Build jar file
    - mvn package -DskipTests
- Build Dockerfile
    - docker build .
- Deploy to heroku
    - heroku container:push web --app \<appname\>
    - heroku container:release web --app \<appname\>
## References
- https://medium.com/@urbanswati/deploying-spring-boot-restapi-using-docker-maven-heroku-and-accessing-it-using-your-custom-aa04798c0112
