package com.github.phillipkruger.membership.graphql.scopes;

import java.time.LocalDateTime;

import javax.enterprise.context.ApplicationScoped;
import lombok.extern.java.Log;
import org.eclipse.microprofile.graphql.GraphQLApi;
import org.eclipse.microprofile.graphql.Query;

/**
 * GraphQL Api to check CDI Scopes
 * @author Phillip Kruger (phillip.kruger@phillip-kruger.com)
 */
@Log
@GraphQLApi
@ApplicationScoped
public class ApplicationScopedApi {
    
    private final LocalDateTime ldt = LocalDateTime.now();
    
    @Query("applicationScopedTime") 
    public LocalDateTime getTime() {
        return ldt;
    }
}