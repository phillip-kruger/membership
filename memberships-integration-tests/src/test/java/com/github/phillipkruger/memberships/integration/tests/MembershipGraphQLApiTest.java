package com.github.phillipkruger.memberships.integration.tests;

import graphql.ExecutionInput;
import graphql.ExecutionResult;
import graphql.GraphQL;
import graphql.GraphQLError;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.util.List;
import lombok.extern.java.Log;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@Ignore
@Log
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MembershipGraphQLApiTest {
 
    @Test
    public void testGetMembership(){
        
        
        SchemaParser schemaParser = new SchemaParser();
        SchemaGenerator schemaGenerator = new SchemaGenerator();

        TypeDefinitionRegistry typeRegistry = schemaParser.parse(getReader());
        
//        RuntimeWiring wiring = buildRuntimeWiring();
        GraphQLSchema schema = schemaGenerator.makeExecutableSchema(typeRegistry, null);

        GraphQL graphQL = GraphQL.newGraphQL(schema).build();

        ExecutionInput executionInput = ExecutionInput.newExecutionInput().query(
                "query Memberships{\n" +
"  memberships{\n" +
"    membershipId\n" +
"  }\n" +
"}")
                .build();

        ExecutionResult executionResult = graphQL.execute(executionInput);

        Object data = executionResult.getData();
        List<GraphQLError> errors = executionResult.getErrors();
    
        
        log.severe(">>>>>>>> DATA: " + data);
    
        log.severe(">>>>>>>> ERRORS: " + errors);
    }
 
    private Reader getReader(){
        try {
            URL schemaUrl = new URL(SCHEMA_ENDPOINT);
            return new BufferedReader(new InputStreamReader(schemaUrl.openStream()));
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        
    }
    
//    private RuntimeWiring buildDynamicRuntimeWiring() {
//        WiringFactory dynamicWiringFactory = new WiringFactory() {
//            @Override
//            public boolean providesTypeResolver(TypeDefinitionRegistry registry, InterfaceTypeDefinition definition) {
//                return getDirective(definition,"specialMarker") != null;
//            }
//
//            @Override
//            public boolean providesTypeResolver(TypeDefinitionRegistry registry, UnionTypeDefinition definition) {
//                return getDirective(definition,"specialMarker") != null;
//            }
//
//            @Override
//            public TypeResolver getTypeResolver(TypeDefinitionRegistry registry, InterfaceTypeDefinition definition) {
//                Directive directive  = getDirective(definition,"specialMarker");
//                return createTypeResolver(definition,directive);
//            }
//
//            @Override
//            public TypeResolver getTypeResolver(TypeDefinitionRegistry registry, UnionTypeDefinition definition) {
//                Directive directive  = getDirective(definition,"specialMarker");
//                return createTypeResolver(definition,directive);
//            }
//
//            @Override
//            public boolean providesDataFetcher(TypeDefinitionRegistry registry, FieldDefinition definition) {
//                return getDirective(definition,"dataFetcher") != null;
//            }
//
//            @Override
//            public DataFetcher getDataFetcher(TypeDefinitionRegistry registry, FieldDefinition definition) {
//                Directive directive = getDirective(definition, "dataFetcher");
//                return createDataFetcher(definition,directive);
//            }
//        };
//        return RuntimeWiring.newRuntimeWiring()
//            .wiringFactory(dynamicWiringFactory).build();
//    }
    
    
    private static final String SCHEMA_ENDPOINT = "http://localhost:8080/membership-service/graphql/schema.json";
    
}
