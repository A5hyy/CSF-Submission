package vttp2022.csf.assessment.server.controllers;

import java.io.IOException;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import jakarta.json.Json;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonValue;
import vttp2022.csf.assessment.server.models.Restaurant;
import vttp2022.csf.assessment.server.services.AmazonService;
import vttp2022.csf.assessment.server.services.RestaurantService;

@Controller
@RequestMapping(path = "/api")
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    @GetMapping(path = "/cuisines")
    // public ResponseEntity<String> getCuisines(
    //     @RequestParam(defaultValue = "20") int limit,
    //     @RequestParam(defaultValue = "0") int offset){
    public ResponseEntity<String> getCuisine(){

    //     List<String> cuisine = restaurantService.getCuisine();
    //     JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
    //     System.out.println(cuisine);
    //     for (String c: cuisine)
	// 		arrayBuilder.add(c);

    //         // System.out.println(arrayBuilder.build().toString());

    //         return ResponseEntity.ok(arrayBuilder.build().toString());}
    

            // List<Restaurant> restaurants = restaurantService.getCuisines();
            List<Restaurant> cuisines = restaurantService.getCuisines();
            JsonArrayBuilder jsonArrayBuilder = Json.createArrayBuilder();
            cuisines.stream().forEach(v -> {jsonArrayBuilder.add(v.toJson());});
    

            return ResponseEntity.ok(jsonArrayBuilder.build().toString());
        }
    
        @GetMapping(path = "/{cuisine}/restaurants")
        public ResponseEntity<String> getRestaurantsByCuisine(@PathVariable String cuisine){
            List<Restaurant> restaurants = restaurantService.getRestaurantsByCuisine(cuisine);
            JsonArrayBuilder jsonArrayBuilder = Json.createArrayBuilder();
            restaurants.stream().forEach(v -> {jsonArrayBuilder.add(v.toJson());});
            return ResponseEntity.ok(jsonArrayBuilder.build().toString());
        }

        @Autowired
    private AmazonService amazonS3Service;

        @PostMapping(path = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE )
    //return a string, *myFile from index.html, *name from index.html
    public String postUpload(
        @RequestPart MultipartFile myFile, 
        @RequestPart String name,
        Model model){

            String key = " ";

            //try-catch or throw IOException
            try {
                key = amazonS3Service.upload(myFile);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            
            // pass in myFile to "file"
            model.addAttribute("file", myFile);
            // pass in name to "name"
            model.addAttribute("name", name);
            // pass in key to "key"
            // convert /myUploads%2F83ad0890 to "key" which will be used as /${key} in html tymeleaf
            model.addAttribute("key", "myUploads/%s".formatted(key));

         // return html page name upload /templates/upload.html
        return "upload"; 
    }
        

    
}
