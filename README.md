This is an ecom-microservice project. Below are the microservices and why they are used:
- order-microservice: used to add item to cart and place order.
- product-microservice: used to create products.
- user-microservice: used to create users who will place orders for themselves.

Additionally this project has a config-server which is used to access the externalized configurations of the microservices.
The configuration files of the microservices are inside application-config folder.

Database used is mongoDB.

Used docker to start mongoDB container locally, can also use a cluster created on cloud.mongodb.com.
Below are some docker commands to use mongoDB through docker:
- docker run -d -p 27020:27017 --name mongodb mongo
- docker exec -it mongodb mongosh
- docker stop
- docker rm mongodb
- docker ps

What the above commands respectively:
- start mongoDB container with localhost:27020 and container:27017. Spring Boot app connects to localhost:27017 while Docker forwards request to MongoDB
  running inside container on port 27017.
- used to open a mongoshell so that you can check if data is going in DB or not.
  show dbs
  use db_name
  db.table_name.find().pretty()
- stop the docker container.
- remove the docker container.
- list all the docker containers running.

Other than this previously I used PostgreSQL & pgadmin4. You can use docker compose up.
other-files has some useful files like sql scripts etc.

Using keytool.exe to encrypt configurations.
- open cmd write keytool.exe -genkeypair -alias config-server-key -keyalg RSA -dname "CN=Config Server, OU=Spring Cloud, O=Company" -keypass root123 -keystore config-server.jks -storepass root123
- the above command will create a config-server.jks file. paste it in resources folder.
- now in app.yml add the below
- encrypt:
    key-store:
      location: config-server.jks
      password: root123
      alias: config-server-key
- in postman POST localhost:8888/encrypt, content-type: text/plain, in body select raw and write what you want to encrypt and send.
- you'll get an encrypted text. keep it in config as {cipher}encrypted_text

# RABBIT MQ - Update configurations without refreshing
docker run -it --rm --name rabbitmq -p 5672:5672 -p 15672:15672 rabbitmq:4-management
- There are 2 different ports here as RabbitMQ exposes two different services.
  - Used by applications/services. Your Spring Boot microservices connect here: amqp://localhost:5672
  - Spring Boot uses this internally. This is the actual RabbitMQ broker port for:
    - producers
    - consumers
    - queues
    - exchanges
  - http://localhost:15672 Used in browser
    - This gives dashboard for:
      - queues
      - exchanges
      - messages
      - connections
      - monitoring
