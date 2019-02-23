package com.github.phillipkruger.membership.graphql.impl;

import graphql.schema.GraphQLSchema;
import io.leangen.graphql.GraphQLSchemaGenerator;
import io.leangen.graphql.metadata.strategy.query.AnnotatedResolverBuilder;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import javax.enterprise.event.Observes;
import javax.enterprise.inject.spi.AfterDeploymentValidation;
import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.BeanManager;
import javax.enterprise.inject.spi.Extension;
import javax.enterprise.inject.spi.ProcessBean;
import lombok.extern.java.Log;
import org.eclipse.microprofile.graphql.Mutation;
import org.eclipse.microprofile.graphql.Query;

import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.inject.spi.CDI;

/**
 * This is a CDI extension that detects GraphQL components and generate a CDI
 * GraphQLConfig bean used by SchemaProducer.
 *
 * @author Phillip Kruger (phillip.kruger@redhat.com)
 */
@Log
public class GraphQLExtension implements Extension {

    private final List<Bean> graphQLComponents = new LinkedList<>();
    
    private GraphQLSchema schema;
    
    private void registerGraphQLComponents(@Observes AfterDeploymentValidation abd, BeanManager beanManager) {
        GraphQLSchemaGenerator schemaGen = new GraphQLSchemaGenerator()
                .withResolverBuilders(new AnnotatedResolverBuilder());
        
        for(Bean component:graphQLComponents){
            Type beanClass = component.getBeanClass();
            CreationalContext<?> creationalContext = beanManager.createCreationalContext(component);
            schemaGen.withOperationsFromSingleton(
                    beanManager.getReference(component, beanClass, creationalContext), 
                    component.getBeanClass());
            log.log(Level.INFO, "GraphQL Component [{0}] registered", component.getBeanClass());
        }
        schema = schemaGen.generate();
        
        SchemaProducer schemaProducer = CDI.current().select(SchemaProducer.class).get();
        schemaProducer.setGraphQLSchema(schema);
        
        log.info("All GraphQL Components loaded.");
        
    }
    
    // Detect and store GraphQLcomponents
    private <X> void detectGraphQLComponent(@Observes ProcessBean<X> event) {
        if(!graphQLComponents.contains(event.getBean())){
        
            Method[] methods = event.getBean().getBeanClass().getMethods();
            for(Method method:methods){
                if(method.getAnnotationsByType(Query.class).length>0 ||
                        method.getAnnotationsByType(Mutation.class).length>0){
                    graphQLComponents.add(event.getBean());
                    return;
                }
            }
        }
    }
    
}