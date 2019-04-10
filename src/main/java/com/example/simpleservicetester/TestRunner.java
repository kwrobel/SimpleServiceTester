package com.example.simpleservicetester;

import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import com.example.simpleservicetester.entity.Post;

/**
 * Run test for connecting to an online web service and retrieve
 * its JSON-formatted data. The data comes from the following source:
 * https://jsonplaceholder.typicode.com
 * 
 * @author Kay Wrobel
 */
public class TestRunner {
    
    private static final int MAX_POSTS = 10;
    private static final String URL_POSTS = "https://jsonplaceholder.typicode.com/posts";

    /**
     * Entry point that runs the test scenario
     * 
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        // We will use Jersey 2 (JAX-RS implementation) to talk to the web service
        Client client = ClientBuilder.newClient();
        WebTarget postsTarget = client.target(URL_POSTS);
        Invocation.Builder request = postsTarget.request(MediaType.APPLICATION_JSON);
        
        // Retrieve the JSON result and map it to a List of Post objects via
        // Generics. Ref: https://www.javainterviewpoint.com/jersey-restful-web-services-generictype/
        // JSON translation happens behind the scenes using the Jackson JSON Provider
        List<Post> posts = request.get(new GenericType<List<Post>>(){});
        
        // Do something with the result
        if (posts != null && !posts.isEmpty()) {
            System.out.println("I found " + posts.size() + " posts online. Here are " + MAX_POSTS + " random posts:");
            
            // Loop over MAX_POSTS random posts and show the title and user id who posted it
            Random random = new Random();
            IntStream ints = random.ints(0, posts.size() - 1).distinct();
            IntStream randomElements = ints.limit(MAX_POSTS);
            
            randomElements.forEach(
                    i -> {
                        Post post = posts.get(i);
                        System.out.println("[" + post.getId() + "] " + post.getTitle() + " -- posted by user Id " + post.getUserId());                
                    }
            );
        }
        
    }
    
}
