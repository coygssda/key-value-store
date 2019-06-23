# key-value-store

Key Value Store is a Spring Boot Application based on Key-value management system.It allows the client to add a key-value pair,update an already existing key value pair with a new value and to get a value for the corresponding key.The key-value pair is persisted in the database(couchbase) with the key as the ID of the document.Any issue to update the document in the couchbase,an event is published in the Kafka messaging system to maintain consistency.Through the Kafka topic we can see the updated value and the time at which the document was going to be updated.This project also handles Data Consistency in a way,i.e when we are unable to connect to our database , any request in the meantime to update the value , then an event is published to Kafka so a consumer application can act on it.

Clone the Repository
--------------------
git clone https://github.com/coygssda/key-value-store.git

Build and Run
-------------
maven build(skip Tests) and run the spring boot Application (with main class "com.kvstore.server.project.ProjectApplication")

Client Requests
---------------
Get value by Id   =      curl -X GET   http://localhost:8080/kvStore/getValueFromId/id01
Put Id and value  =     curl -X PUT \
  http://localhost:8080/kvStore/updateStorage \
  -H 'Content-Type: application/json' \
  -d '{
"id":"id4",
"value":"113"
}'

Flow Design
-----------
RestControl - This is the class which is the first point of entry to our server when a request is made by the client to get value and update or create a document in the database . It implements the MVC .
KvStoreApplicationService- This is the class which acts a service(domain) layer and redirects the calls to either the repository service or the gateway service.
ResourceDTORepositoryService-This is the actual class which is responsible for saving and inserting and querying the document from the couchbase.
ProductToExternalService- This class is responsible for publishing message in the form of Json to a topic "kvStoreEvent" and it also publishes the time at which the document was about to be updated.

Integration with Couchbase and Kafka
-----------------------------------
Couchbase-
Create a bucket "kvstore"(same as in application.properties) and primary index on the bucket in Couchbase .
ADD USER in couchbase through the UI.
Kafka-
cd bin/
./zookeeper-server-start.sh ..config/zookeeper.properties
./kafka-server-start.sh ..config/server.properties
./kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic kvStoreEvent
./kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic kvStoreEvent --from-beginning


