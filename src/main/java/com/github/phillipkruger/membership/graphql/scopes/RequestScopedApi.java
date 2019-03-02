package com.github.phillipkruger.membership.graphql.scopes;

import java.time.LocalDateTime;

import javax.enterprise.context.RequestScoped;
import lombok.extern.java.Log;
import org.eclipse.microprofile.graphql.GraphQLApi;
import org.eclipse.microprofile.graphql.Query;

/**
 * GraphQL Api to check CDI Scopes
 * @author Phillip Kruger (phillip.kruger@phillip-kruger.com)
 */
@Log
@GraphQLApi
@RequestScoped
public class RequestScopedApi {
    
    private final LocalDateTime ldt = LocalDateTime.now();
    
    @Query("requestScopedTime") 
    public LocalDateTime getTime() {
        return ldt;
    }
}