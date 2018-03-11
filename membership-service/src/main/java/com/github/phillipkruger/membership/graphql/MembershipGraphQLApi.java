package com.github.phillipkruger.membership.graphql;

import com.coxautodev.graphql.tools.SchemaParser;
import graphql.schema.GraphQLSchema;
import graphql.servlet.SimpleGraphQLServlet;
import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletRegistration;
import javax.servlet.annotation.WebListener;
import lombok.extern.java.Log;

//@WebServlet(urlPatterns = "/graphql")
@Log
@WebListener
public class MembershipGraphQLApi implements ServletContextListener {
    
    @Inject
    private Query query;
    
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        log.severe(">>>>>>>>>>>>> Hello <<<<<<<<<<<<");
        
        
        GraphQLSchema schema = SchemaParser.newParser()
                .file(GRAPHQL_SCHEMA_PATH)
                .resolvers(query)
                .build()
                .makeExecutableSchema();
        
        SimpleGraphQLServlet.Builder builder = SimpleGraphQLServlet.builder(schema);
        SimpleGraphQLServlet graphQLServlet = builder.build();
        
        ServletContext context = sce.getServletContext();
 
        ServletRegistration.Dynamic servlet = context.addServlet(SERVLET_NAME, graphQLServlet);
        servlet.addMapping(SERVLET_URL);
 
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        log.severe(">>>>>>>>>>>>> Bye bye <<<<<<<<<<<<");
    }
    
    private static final String SERVLET_NAME = "MembershipGraphQLServlet";
    private static final String[] SERVLET_URL = new String[]{"/graphql"};
    private static final String GRAPHQL_SCHEMA_PATH = "/schema.graphqls"; 
}