# Overview

Provided solution is an example of test automation with a use of Selenium and JUnit 5. 

In order to simplify code, [Project Lombok](https://projectlombok.org) was used.

## Usage

You can run solution from you favorite IDE. If you have **Maven** installed, you can run tests with command:

```
mvn test -DtestFile="./test.json" -DremoteHub="http://localhost:4444/wd/hub"
```
**Both parameters are optional. When not specified, test data will be always read from test.json file. When specified, remoteHub parameter is equal to setting useSelenium to true, and setting value for this same variable in test JSON file.**

As this solution use neither calls to API, nor collect data from DB, please fill-in **test.json** (default if not specified as system property) with an example test data.

Test file provides the possibility to switch between local Chrome, or Selenium Grid as a target.

```
  "webDriver": {
    "useSeleniumGrid": true,
    "type": "chrome",
    "remoteHub": "http://localhost:4444/wd/hub",
    "driverPath": "./chromedriver",
    "timeoutInSeconds": 10
  }
```

When **useSeleniumGrid** is set to true, **driverPath** will be not taken into consideration. When set to false, **remoteHub** have no use. 

Please ensure that **driverPath** is set to correct location before running tests on local environment.

## Running with an use of Selenium Grid

In order to speedup the process of configuring needed dependencies, you can find Docker compose file in a docker folder. Running Selenium Grid is as simple as running the command below:

```
docker-compose up -d
```

**docker-compose.yml** have all the configuration needed to run test automation on Chrome node. As default port mappings are set to 4444. 
