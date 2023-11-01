package com.mx.banorte.client;

import java.util.concurrent.CompletionStage;

import org.infinispan.client.hotrod.RemoteCache;

import io.quarkus.infinispan.client.Remote;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;

@Path("/greeting")
public class InfinispanGreetingResource {
   
    @Inject
    @Remote("mycache") 
    RemoteCache<String, Greeting> cache;
    
    @POST
    @Path("/{id}")
    public CompletionStage<String> postGreeting(String id, Greeting greeting) {

        System.out.println(id);
        System.out.println(greeting);
        return cache.putAsync(id, greeting) 
              .thenApply(g -> "Greeting done!");
    }

    @GET
    @Path("/{id}")
    public CompletionStage<Greeting> getGreeting(String id) {
        return cache.getAsync(id); 
    }


    
}
