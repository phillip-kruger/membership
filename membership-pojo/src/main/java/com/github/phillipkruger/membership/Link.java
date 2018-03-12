package com.github.phillipkruger.membership;

import graphql.annotations.annotationTypes.GraphQLField;
import graphql.annotations.annotationTypes.GraphQLName;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data @AllArgsConstructor
@GraphQLName("link")
public class Link {
    @GraphQLField
    private String url;
    @GraphQLField
    private String description;
}