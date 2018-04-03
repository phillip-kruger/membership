package com.github.phillipkruger.membership.graphql;

import graphql.ExceptionWhileDataFetching;
import graphql.GraphQLError;
import graphql.servlet.GenericGraphQLError;
import graphql.servlet.GraphQLErrorHandler;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

public class MembershipErrorHandler implements GraphQLErrorHandler {

    @Override
    public List<GraphQLError> processErrors(List<GraphQLError> errors) {
        List<GraphQLError> wrappedErrors = new ArrayList<>();
        for(GraphQLError error:errors){
            if (error instanceof ExceptionWhileDataFetching) {
                Throwable t = ((ExceptionWhileDataFetching) error).getException();
                wrappedErrors.add(getGraphQLError(t));
            }
        }
        return wrappedErrors;
    }
    
    private GraphQLError getGraphQLError(Throwable t){
        if(t instanceof GraphQLError){
            return (GraphQLError)t;
        }else if(t instanceof ConstraintViolationException) {
            ConstraintViolationException constraintViolationException = (ConstraintViolationException)t;
            return new GenericGraphQLError(getConstraintViolationMessage(constraintViolationException));
        } else {
            if(t.getCause()!=null){
                return getGraphQLError(t.getCause());
            }else{
                return new GenericGraphQLError("Internal Server Error(s) while executing query");
            }
        }
    }
    
    private String getConstraintViolationMessage(ConstraintViolationException constraintViolationException){
        StringWriter sw = new StringWriter();
        Set<ConstraintViolation<?>> constraintViolations = constraintViolationException.getConstraintViolations();
        for(ConstraintViolation<?> s : constraintViolations){
            sw.write(s.getMessage());
        }
        return sw.toString();
    }
    
}
