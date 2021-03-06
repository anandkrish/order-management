# order-management
![Headers](Order_management_system_drawio.png) 

### Prerequisites
 #### Setup
 - Download any IDE that work with Gradle and Springboot
 - Install MongoDb
    - Follow Mongo DB setup from here [setup](https://www.mongodb.com/docs/manual/tutorial/install-mongodb-on-windows/) 
    - Create database named `orderManagement` and collection named `orderDetails`
    - Create another collection named `userLogin` for authentication

 - Install Kafka (Steps are different for Windows): version kafka_2.13-3.2.0
 ##### Install zookeeper and kakfa

 - WSL2 - Virtual linx environment in Windows (Ubuntu is default)\
    ```wsl --install --ubuntu```

 - Download Kafka\
    ```wget https://archive.apache.org/dist/kafka/3.2.0/kafka_2.13-3.2.0.tgz```

 - Extract Kafka and move to desired folder\
    ```tar xzf kafka_2.13-3.2.0.tgz```
    ```mv kafka_2.13-3.2.0 ~```
    ```cd kafka_2.13-3.2.0/```

    ```PATH="$PATH:~/kafka_2.13-3.2.0/bin"```

- Start zookeeper

    ```~/kafka_2.13-3.2.0/bin/zookeeper-server-start.sh ~/kafka_2.13-3.2.0/config/zookeeper.properties```

- Start kafka

    ```~/kafka_2.13-3.2.0/bin/kafka-server-start.sh ~/kafka_2.13-3.2.0/config/server.properties /home/exzion/kafka_2.13-3.2.0/bin```

 
- Create topic 

  ```~/kafka_2.13-3.2.0/bin/kafka-topics.sh  --create --bootstrap-server localhost:9092 --replication-factor 1 --partitions 1 --topic order-details```
  
- List topics

  ```~/kafka_2.13-3.2.0/bin/kafka-topics.sh --bootstrap-server localhost:9092 --list```
	
	
- Edit server.properties of kafka in vi and add map local host URL 
	
      advertised.listeners=PLAINTEXT://127.0.0.1:9092\
      listener.security.protocol.map=PLAINTEXT:PLAINTEXT\
      listeners=PLAINTEXT://0.0.0.0:9092
      
 #### Start server
 ``` .\gradlew bootRun```
      
      
 ### Run test cases
 ```.\gradlew clean test --info```
      
      
 #### Postman collection updated in repo\
  - order-management.postman_collection.json
 
 - Use below end point to generate token\
 ```/order_management/v1/login```
 
 #### Authentication
 - For all other secured end points add following headers
  
![Headers](auth_postman.JPG) 


``` 
curl --location --request POST 'http://localhost:9050/order_management/v1/orders' \
--header 'Content-Type: application/json' \
--header 'Cookie: user=exzion' \
--header 'x-auth-token: cc830fca-115b-44a4-94f9-1e937a8b76a4' \
--data-raw ' { "expectedDeliveryDate": "",
 "shipmentAddress": "Chennai, INdia",
 "customerName": "Anand",
 "customerId": "99999",
 "phoneNumber": "9952563200",
 "productList": [{
    "productId": "P8971",    
    "productName": "HOme products",   
    "price": 500,    
    "productType" : "Decor"
 },{
    "productId": "P8171",    
    "productName": "Item1",   
    "price": 100,    
    "productType" : "Fashion"
 }]
 
 }
 ```
  
