# allure-docker-example
The purpose of this repo is to build an example of how to use Allure + Junit + Docker.

## Run
Just run inside project root `./run.sh`.

Open Allure report on your host machine:
- Linux host: [http://localhost:8000/#/](http://localhost:8000/#/)
- Boot2docker host (Windows, Mac): usually [http://192.168.99.100:8000/#/](http://192.168.99.100:8000/#/) . 
If it doesn't work, please find the IP address of your docker machine by `docker-machine ip my-machine-name` 
and replace `192.168.99.100` to evaluated IP.


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