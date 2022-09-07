# selenide-junit-allure
Example project for [Selenide](https://selenide.org/) framework , [jUnit5](https://junit.org/junit5/) and [Allure docker service](https://github.com/fescobar/allure-docker-service)

# Prerequisities
- Selenium 4 Grid installed for execution from Jenkins
- Allure docker service installed 
- Jenkins installed

# How to run locally
Run maven goal test to generate allure results. 
Then run maven allure:serve to generate and open Allure report

# How to run on Jenkins
Create Multibranch pipeline job in Jenkins pointing to your repository and Jenkinsfile

**Required Jenkins plugins**:
- https://plugins.jenkins.io/http_request/
- https://plugins.jenkins.io/pipeline-utility-steps/

# Jenkinsfile
In Jenkinsfile modify properties:
allure_server_url - URL where the Allure docker service container is deployed
project_id - Project ID to your project in Allure container

Maven build parameters:
- -Dselenide.headless - true for headless browser mode 
- -DuseSeleniumGrid - true for Selenium Grid usage
- -DseleniumGridHub - url of Selenium Grid
- -DtestUrl - url to be tested
- -DnumberOfSession=5 - Number of parallel sessions used in CustomStrategy class

# jUnit properties
junit-platform.properties descibed:
- junit.jupiter.execution.parallel.enabled - enable parallel test execution
- junit.jupiter.execution.parallel.config.strategy - value custom for Custom strategy
- junit.jupiter.execution.parallel.mode.default - value same_thread for methods with class to be run one after another
- junit.jupiter.execution.parallel.mode.classes.default - value concurrent for parallel execution of Classes
- junit.jupiter.execution.parallel.config.custom.class - class name with Custom parallel strategy execution