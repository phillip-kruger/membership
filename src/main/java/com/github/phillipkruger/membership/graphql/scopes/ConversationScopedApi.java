package com.github.phillipkruger.membership.graphql.scopes;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.enterprise.context.ConversationScoped;
import lombok.extern.java.Log;
import org.eclipse.microprofile.graphql.GraphQLApi;
import org.eclipse.microprofile.graphql.Query;

/**
 * GraphQL Api to check CDI Scopes
 * @author Phillip Kruger (phillip.kruger@phillip-kruger.com)
 */
@Log
@GraphQLApi
@ConversationScoped
public class ConversationScopedApi implements Serializable {
    
    private final LocalDateTime ldt = LocalDateTime.now();
    
    @Query("conversationScopedTime") 
    public LocalDateTime getTime() {
        return ldt;
    }
}