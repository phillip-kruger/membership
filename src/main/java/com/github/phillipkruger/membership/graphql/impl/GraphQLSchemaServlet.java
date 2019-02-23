package com.github.phillipkruger.membership.graphql.impl;

import graphql.schema.GraphQLSchema;
import graphql.schema.idl.SchemaPrinter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;

/**
 * To make the schema available
 * @author Phillip Kruger (phillip.kruger@redhat.com)
 */
@AllArgsConstructor
public class GraphQLSchemaServlet extends HttpServlet {

    private final GraphQLSchema schema;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        SchemaPrinter schemaPrinter = new SchemaPrinter(SchemaPrinter.Options.defaultOptions());
        String json = schemaPrinter.print(schema);
        response.setContentType("plain/text");
        PrintWriter out = response.getWriter();
        out.print(json);
        out.flush();
    }
    
    @Override
    public String getServletInfo() {
        return "The graphQL schema";
    }
}
