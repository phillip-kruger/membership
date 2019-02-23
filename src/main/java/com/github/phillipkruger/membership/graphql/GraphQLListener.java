package com.github.phillipkruger.membership.graphql;

import graphql.schema.GraphQLSchema;
import graphql.servlet.SimpleGraphQLHttpServlet;
import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletRegistration;
import javax.servlet.annotation.WebListener;
import lombok.extern.java.Log;

@WebListener
@Log
public class GraphQLListener implements ServletContextListener {
    @Inject
    private GraphQLSchema schema;
    
    @Override
    public void contextInitialized(ServletContextEvent sce) {

        SimpleGraphQLHttpServlet graphQLServlet = SimpleGraphQLHttpServlet.newBuilder(schema).build();

        ServletContext context = sce.getServletContext();
        ServletRegistration.Dynamic servlet = context.addServlet(SERVLET_NAME, graphQLServlet);
        servlet.addMapping(SERVLET_URL);

    }

    private static final String SERVLET_NAME = "GraphQLServlet";
    private static final String[] SERVLET_URL = new String[]{"/graphql/*"}; //TODO: Make configurable
    
    
}

