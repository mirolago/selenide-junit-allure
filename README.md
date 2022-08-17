# selenide-junit-allure
Example project for [Selenide](https://selenide.org/) framework , [jUnit5](https://junit.org/junit5/) and [Allure docker service](https://github.com/fescobar/allure-docker-service)

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
allure_server_url - URL where the Allure container is deployed
project_id - Project ID to your project in Allure container

Maven build parameters:
-Dselenide.headless=true 
-DuseSeleniumGrid=true 
-DseleniumGridHub=https://selenium4.astondev.sk 
-DtestUrl=https://www.darcakrvi.sk/
-DnumberOfSession=5 - Number of parallel sessions used in CustomStrategy class

# jUnit properties
junit-platform.properties descibed:
