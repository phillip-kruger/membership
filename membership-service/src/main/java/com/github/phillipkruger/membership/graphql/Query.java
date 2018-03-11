package com.github.phillipkruger.membership.graphql;

import com.github.phillipkruger.membership.service.LinkService;
import com.github.phillipkruger.membership.Link;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import java.util.List;
import javax.inject.Inject;

public class Query implements GraphQLQueryResolver {
    
    @Inject
    private LinkService linkService;

    public List<Link> allLinks() {
        return linkService.getAllLinks();
    }
    
    public String ping(){
        return "pong";
    }
}