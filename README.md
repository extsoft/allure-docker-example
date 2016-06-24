# allure-docker-example
The purpose of this repo is to build an example of how to use Allure + Junit + Docker.

## Automation checks
Code: 
- master: [![Build Status](https://travis-ci.org/extsoft/allure-docker-example.svg?branch=master)](https://travis-ci.org/extsoft/allure-docker-example) 
- develop: [![Build Status](https://travis-ci.org/extsoft/allure-docker-example.svg?branch=develop)](https://travis-ci.org/extsoft/allure-docker-example)


## Run
### Manually
1. `gradle`
2. `java -javaagent:build/libs/deps/aspectjweaver-1.8.0.jar -jar build/libs/allure-docker-example-1.2.0.jar`
3. `allure generate -o allure-report allure-result`
4. `allure report open -p 8000`


### Docker
1. ```docker run -t -i -p 8000:80 extsoft/allure-docker-example ```
2. Open Allure report on your host machine:
  - Linux host: [http://localhost:8000/#/](http://localhost:8000/#/)
  - Boot2docker host (Windows, Mac): usually [http://192.168.99.100:8000/#/](http://192.168.99.100:8000/#/) . 
If it doesn't work, please find the IP address of your docker machine by `docker-machine ip my-machine-name` 
and replace `192.168.99.100` to evaluated IP.

### Build image locally
``` docker build -t extsoft/allure-docker-example . ```

## License
```
Copyright 2016 Dmytro Serdiuk <dmytro.serdiuk@gmail.com>

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
