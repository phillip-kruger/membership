package com.github.phillipkruger.membership.graphql;

import com.github.phillipkruger.membership.service.MembershipService;
import graphql.schema.GraphQLSchema;
import graphql.servlet.SimpleGraphQLServlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletRegistration;
import javax.servlet.annotation.WebListener;
import lombok.extern.java.Log;

import io.leangen.graphql.GraphQLSchemaGenerator;
import io.leangen.graphql.metadata.strategy.query.AnnotatedResolverBuilder;
import javax.inject.Inject;

@Log
@WebListener
public class MembershipGraphQLApi implements ServletContextListener {
    
    @Inject
    private MembershipService membershipService;
    
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        
        GraphQLSchema schema = new GraphQLSchemaGenerator()
                .withResolverBuilders(new AnnotatedResolverBuilder().withDefaultFilters())
                .withOperationsFromSingleton(membershipService,MembershipService.class)
                .generate();
        
        SimpleGraphQLServlet.Builder builder = SimpleGraphQLServlet.builder(schema)
                .withGraphQLErrorHandler(new MembershipErrorHandler())
                //.withInstrumentation(new FieldValidationInstrumentation(new SimpleFieldValidation()))
                ;
        
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