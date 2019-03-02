package com.github.phillipkruger.membership.graphql.scopes;

import java.time.LocalDateTime;

import lombok.extern.java.Log;
import org.eclipse.microprofile.graphql.GraphQLApi;
import org.eclipse.microprofile.graphql.Query;

/**
 * GraphQL Api to check CDI Scopes
 * @author Phillip Kruger (phillip.kruger@phillip-kruger.com)
 */
@Log
@GraphQLApi
public class NoScopedApi {
    
    private final LocalDateTime ldt = LocalDateTime.now();
    
    @Query("noScopedTime") 
    public LocalDateTime getTime() {
        return ldt;
    }
}