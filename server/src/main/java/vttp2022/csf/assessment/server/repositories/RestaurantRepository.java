package vttp2022.csf.assessment.server.repositories;

import java.util.List;
import java.util.Optional;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import vttp2022.csf.assessment.server.models.Comment;
import vttp2022.csf.assessment.server.models.Restaurant;

@Repository
public class RestaurantRepository {

	public static final String RESTAURANTS = "restaurants";

	// TODO Task 2
	// Use this method to retrive a list of cuisines from the restaurant collection
	// You can add any parameters (if any) and the return type 
	// DO NOT CHNAGE THE METHOD'S NAME
	// Write the Mongo native query above for this method
	// 
	@Autowired
	private MongoTemplate mongoTemplate;

	public List<Restaurant> getCuisines(){
		Query query = (new Query());
		return mongoTemplate.find(query, Document.class, "restaurants")
		.stream().map(v -> {return Restaurant.createRestaurant(v);}).toList();
	}

	// public List<Restaurant> getCuisines(int limit, int skip){
	// 	Query query = (new Query()).limit(limit).skip(skip);
	// 	return mongoTemplate.find(query, Document.class, "restaurants")
	// 	.stream()
	// 	.map(v -> {return Restaurant.createRestaurant(v);})
	// 	.toList();
	// }


	// db.restaurants.distinct( "cuisine");

	// public List<String> getCuisine(){
	// 	return mongoTemplate.findDistinct(new Query(), "cuisines", RESTAURANTS, String.class);
	// }




	// TODO Task 3
	// Use this method to retrive a all restaurants for a particular cuisine
	// You can add any parameters (if any) and the return type 
	// DO NOT CHNAGE THE METHOD'S NAME
	// Write the Mongo native query above for this method
	 
	public List<Restaurant> getRestaurantsByCuisine(String cuisine) {
		// Implmementation in here
		Criteria criteria = Criteria.where("cuisine").is(cuisine);
		Query query = Query.query(criteria);
		return mongoTemplate.find(query, Document.class, "restaurants")
		.stream().map(v -> {return Restaurant.createRestaurant(v);})
		.toList();
	}

	// // TODO Task 4
	// // Use this method to find a specific restaurant
	// // You can add any parameters (if any) 
	// // DO NOT CHNAGE THE METHOD'S NAME OR THE RETURN TYPE
	// // Write the Mongo native query above for this method
	// //  
	// public Optional<Restaurant> getRestaurant(???) {
	// 	// Implmementation in here
		
	// }

	// TODO Task 5
	// Use this method to insert a comment into the restaurant database
	// DO NOT CHNAGE THE METHOD'S NAME OR THE RETURN TYPE
	// Write the Mongo native query above for this method
	//  
	// public void addComment(Comment comment) {
	// 	// Implmementation in here
		
	// }
	
	// You may add other methods to this class

}
