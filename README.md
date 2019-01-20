# Membership service

This is an example application that demonstrate how to add [GraphQL](http://facebook.github.io/graphql) to your existing JAX-RS Application.

***
## You can play around with an [online demo here](http://bit.ly/gql-demonstration)
***

This application use these libraries:

* [graphql-java](https://github.com/graphql-java/graphql-java)
* [graphql-java-servlet](https://github.com/graphql-java/graphql-java-servlet)
* [graphQL-spqr](https://github.com/leangen/GraphQL-SPQR)
* [graphiql](https://github.com/graphql/graphiql)


![](https://raw.githubusercontent.com/phillip-kruger/membership/master/membership.png)

Simple CRUD Application that can create, update, delete and get a membership. Every membership has a owner. The application use JPA to persist the Object in a DB.
"Business Logic" is in the Membership Service (EJB) and the JAX-RS API in MembershipRestApi.

Added annotations and MembershipGraphQLApi to expose API as GraphQL.

## Getting started

### From source

Get the source:

    git clone https://github.com/phillip-kruger/membership.git
    cd membership


This demo runs on Thorntail
Build and start:

    mvn clean install -Pthorntail

The server is now up and running with some test data populated (see resources/META-INF/load.sql)

### Available endpoints:

* Front page : http://localhost:8080/membership/
* Swagger UI : http://localhost:8080/membership/rest/openapi-ui/
* GraphiQL   : http://localhost:8080/membership/graph/graphiql/

### Run some examples:

In the [GraphiQL](http://localhost:8080/membership/graph/graphiql/) GUI try out some of the [examples](EXAMPLE.md)