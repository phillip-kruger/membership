package com.github.phillipkruger.membership.graphql;

import com.github.phillipkruger.membership.service.LinkService;
import com.github.phillipkruger.membership.Link;
//import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import graphql.annotations.annotationTypes.GraphQLField;
import graphql.annotations.annotationTypes.GraphQLName;
import java.util.List;
import javax.inject.Inject;

@GraphQLName("query")
public class Query {//implements GraphQLQueryResolver {
    
    //@Inject
    private static  LinkService linkService = new LinkService();

    @GraphQLField
    public static List<Link> allLinks() {
        return linkService.getAllLinks();
    }
    
    @GraphQLField
    public static String ping(){
        return "pong";
    }
    
}