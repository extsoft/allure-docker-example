FROM java:8-jdk
MAINTAINER Dmytro Serdiuk <dmytro.serdiuk@gmai.com>

LABEL Description="This image is used to start the foobar executable"
LABEL Vendor="extsoft"
LABEL Version="1.0.0"


#Inslate Allure CLI
RUN wget https://github.com/allure-framework/allure-core/releases/download/allure-core-1.4.23/allure-commandline.zip && \
    unzip allure-commandline.zip && bin/allure && rm allure-commandline.zip

##Add tests artifacts
COPY build/libs/allure-docker-example-1.0.0-SNAPSHOT.jar /
COPY build/libs/deps/* /deps/


#Run tests, generate and open report
CMD java -javaagent:/deps/aspectjweaver-1.8.0.jar -jar allure-docker-example-1.0.0-SNAPSHOT.jar && \
    allure generate -o allure-report allure-result && \
    allure report open -p 80