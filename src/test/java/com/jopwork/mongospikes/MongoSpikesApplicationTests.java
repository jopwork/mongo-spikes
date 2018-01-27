package com.jopwork.mongospikes;

import com.mongodb.*;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;
import org.bson.Document;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MongoSpikesApplicationTests {
    @Autowired
    private CustomerRepository repository;


    @Test
	public void testUsingSpring() {
		repository.deleteAll();

		// save a couple of customers
		repository.save(new Customer("Alice", "Smith"));
		repository.save(new Customer("Bob", "Smith"));

		// fetch all customers
		System.out.println("Customers found with findAll():");
		System.out.println("-------------------------------");
		for (Customer customer : repository.findAll()) {
			System.out.println(customer);
		}
		System.out.println();

		// fetch an individual customer
		System.out.println("Customer found with findByFirstName('Alice'):");
		System.out.println("--------------------------------");
		System.out.println(repository.findByFirstName("Alice"));

		System.out.println("Customers found with findByLastName('Smith'):");
		System.out.println("--------------------------------");
		for (Customer customer : repository.findByLastName("Smith")) {
			System.out.println(customer);
		}

	}

	@Test
    public void testUsingApi() {
        // create database
        MongoClient client = new MongoClient("localhost", 27017);
        MongoDatabase testDB = client.getDatabase("test");
        System.out.println("Dropping person collection in test database");
        MongoCollection<Document> collection = testDB.getCollection("person");
        collection.drop();

        // insert document
        Document person = new Document("name", "Fred").append("age", 30);
        collection.insertOne(person);

        // find document
        System.out.println("Now finding a person using findOne");
        FindIterable<Document> result = collection.find();
        for (Document each : result) {
            System.out.println("Found a document");
            System.out.println("Name: " + each.get("name"));
            System.out.println("Age: " + each.get("age"));
        }

        // get database names
        MongoIterable<String> databases = client.listDatabaseNames();
        System.out.println("Begin list of database names.");
        for (String each : databases) {
            System.out.println(each);
        }
        System.out.println("End of list.");
    }

}
