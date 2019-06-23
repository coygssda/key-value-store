# key-value-store

Key Value Store is a Spring Boot Application based on Key-value management system.It allows the client to add a key-value pair,update an already existing key value pair with a new value and to get a value for the corresponding key.The key-value pair is persisted in the database(couchbase) with the key as the ID of the document.Any issue to update the document in the couchbase,an event is published in the Kafka messaging system to maintain consistency.Through the Kafka topic we can see the updated value and the time at which the document was going to be updated.

Build
------------------

cd 
