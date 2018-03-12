package com.github.phillipkruger.membership;

import io.leangen.graphql.annotations.GraphQLQuery;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data @AllArgsConstructor
public class Link {
    @GraphQLQuery(name = "url", description = "The url of the link")
    private String url;
    @GraphQLQuery(name = "description", description = "The description of the link")
    private String description;
}