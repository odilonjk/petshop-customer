# Shop Platform - Customer

[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)

---

### This project uses

* JDK 11 + OpenJ9
* Spring 2
* Gradle 6

---

#### Docker

    $ gradle clean bootJar
    $ docker build -f Dockerfile -t shop-platform-customer .
    $ docker run -d --rm --name shop-platform-customer -p 8080:8080 --link=mongodb:mongodb shop-platform-customer:latest

---

#### Database

In order to run this project, you need to run a MongoDB on `mongodb:27017` or you can change it on `application.properties` file. 

---

#### API

You can find examples of how to use the rest API on a file called `api.http`.
This file can also be executed on VS Code using the Rest Client plugin.