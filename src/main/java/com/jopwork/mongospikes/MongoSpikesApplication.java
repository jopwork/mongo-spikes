package com.jopwork.mongospikes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MongoSpikesApplication {

    public static void main(String[] args) {
		SpringApplication.run(MongoSpikesApplication.class, args);
	}
}
