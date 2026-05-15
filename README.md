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
