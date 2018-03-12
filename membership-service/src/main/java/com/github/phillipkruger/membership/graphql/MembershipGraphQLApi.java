package com.github.phillipkruger.membership.graphql;

//import graphql.annotations.processor.GraphQLAnnotations;
import com.github.phillipkruger.membership.service.LinkService;
import com.github.phillipkruger.membership.service.MembershipService;
import graphql.schema.GraphQLObjectType;
import graphql.schema.GraphQLSchema;
import graphql.servlet.SimpleGraphQLServlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletRegistration;
import javax.servlet.annotation.WebListener;
import lombok.extern.java.Log;

import io.leangen.graphql.GraphQLSchemaGenerator;
import javax.inject.Inject;

@Log
@WebListener
public class MembershipGraphQLApi implements ServletContextListener {
    
    @Inject
    private LinkService linkService;
    
    @Inject
    private MembershipService membershipService;
    
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        
        GraphQLSchema schema = new GraphQLSchemaGenerator()
            .withOperationsFromSingleton(linkService,LinkService.class)
            .withOperationsFromSingleton(membershipService, MembershipService.class)
            .generate(); 

        SimpleGraphQLServlet.Builder builder = SimpleGraphQLServlet.builder(schema);
        SimpleGraphQLServlet graphQLServlet = builder.build();
        
        ServletContext context = sce.getServletContext();
 
        ServletRegistration.Dynamic servlet = context.addServlet(SERVLET_NAME, graphQLServlet);
        servlet.addMapping(SERVLET_URL);
 
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {}
    
    private static final String SERVLET_NAME = "MembershipGraphQLServlet";
    private static final String[] SERVLET_URL = new String[]{"/graphql"};
    
}