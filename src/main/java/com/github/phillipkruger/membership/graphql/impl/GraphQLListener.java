package com.github.phillipkruger.membership.graphql.impl;

import graphql.schema.GraphQLSchema;
import graphql.servlet.SimpleGraphQLHttpServlet;
import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletRegistration;
import javax.servlet.annotation.WebListener;
import lombok.extern.java.Log;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@WebListener
@Log
public class GraphQLListener implements ServletContextListener {
    @Inject
    private GraphQLSchema schema;
    @Inject @ConfigProperty(name = "microprofile.graphql.contextpath", defaultValue = "graphql")
    private String path;
    @Inject @ConfigProperty(name = "microprofile.graphql.servletname", defaultValue = "GraphQLServlet")
    private String servletname;
    
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();
        
        // The Endpoint
        SimpleGraphQLHttpServlet graphQLServlet = SimpleGraphQLHttpServlet.newBuilder(schema).build();
        ServletRegistration.Dynamic endpointservlet = context.addServlet(servletname, graphQLServlet);
        endpointservlet.addMapping("/" + path + "/*");
        
        // The Schema
        GraphQLSchemaServlet graphQLSchemaServlet = new GraphQLSchemaServlet(schema);
        ServletRegistration.Dynamic schemaservlet = context.addServlet(servletname + "Schema", graphQLSchemaServlet);
        schemaservlet.addMapping("/" + path + "/schema.graphql");
    }
}

