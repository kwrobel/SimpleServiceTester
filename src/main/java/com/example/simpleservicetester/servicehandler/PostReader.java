/* 
 * Copyright (C) 2019 Kay Wrobel <kay.wrobel@gmx.net>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.example.simpleservicetester.servicehandler;

import com.example.simpleservicetester.entity.Post;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

/**
 * Main service class to handle Posts from web service
 * @author Kay Wrobel
 */
public final class PostReader {
        
    private static final int MAX_POSTS = 10;
    private static final String URL_POSTS = "https://jsonplaceholder.typicode.com/posts";
    
    private List<Post> posts;

    /**
     * @return the posts
     */
    public List<Post> getPosts() {
        return posts;
    }

    /**
     * Execute web service to retrieve all posts
     */
    public void refreshPosts() {
        // We will use Jersey 2 (JAX-RS implementation) to talk to the web service
        Client client = ClientBuilder.newClient();
        WebTarget postsTarget = client.target(URL_POSTS);
        Invocation.Builder request = postsTarget.request(MediaType.APPLICATION_JSON);
        
        // Retrieve the JSON result and map it to a List of Post objects via
        // Generics. Ref: https://www.javainterviewpoint.com/jersey-restful-web-services-generictype/
        // JSON translation happens behind the scenes using the Jackson JSON Provider
        this.posts = request.get(new GenericType<List<Post>>(){});
    }

    /**
     * Randomly print posts. The number of posts is limited by MAX_POSTS
     */
    public void displayRandom() {
        this.displayRandom(MAX_POSTS);
    }
    
    /**
     * Randomly print posts.
     * @param maxPosts The maximum number of posts to display
     */
    public void displayRandom(int maxPosts) {
        // Do something with the result
        if (this.getPosts() != null && !this.posts.isEmpty()) {
            System.out.println("I found " + this.getPosts().size() + " posts online. Here are " + maxPosts + " random posts:");
            
            // Loop over MAX_POSTS random posts and show the title and user id who posted it
            Random random = new Random();
            IntStream randomElements = random.ints(0, this.getPosts().size() - 1).distinct().limit(maxPosts);
            
            randomElements.forEach(i -> {
                        Post post = this.getPosts().get(i);
                        System.out.println("[" + post.getId() + "] " + post.getTitle() + " -- posted by user Id " + post.getUserId());                
                    }
            );
        }
    }

    /**
     * Initialize the PostReader.
     * Posts are being read from the web service during this initialization, but
     * they can be refreshed at a later point.
     */
    public PostReader() {
        this.refreshPosts();
    }
    
}
