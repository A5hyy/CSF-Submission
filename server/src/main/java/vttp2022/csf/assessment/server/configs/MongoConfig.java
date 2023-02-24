package vttp2022.csf.assessment.server.configs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

@Configuration
public class MongoConfig {

    // @Value("${mongo.url}")
    @Value("mongodb://mongo:xXEBDB1zhdIONo0bdK4L@containers-us-west-24.railway.app:7270/")
    private String mongoUrl;

    @Bean
    public MongoClient mongoClient(){
        return MongoClients.create(mongoUrl);
    }

    @Bean
    public MongoTemplate mongoTemplate(){
        return new MongoTemplate(mongoClient(), "restaurants");
    }
    
}

// @Value("mongodb+srv://ash:Acapthecool%401@cluster0.yvalyga.mongodb.net/test")
//     private String mongoUrl;

//     mongodb://mongo:xXEBDB1zhdIONo0bdK4L@containers-us-west-24.railway.app:7270/