# Membership service

This is an example application that demonstrate how to add [GraphQL](http://facebook.github.io/graphql) to your existing JAX-RS Application.

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

Build all:

    mvn -DskipTests=true clean install

Start wildfly-swarm:

    cd membership-service
    mvn -Pwildfly-swarm-start clean install

Populate test data:

    cd ../memberships-integration-tests
    mvn clean install

### From release

TODO

## Links:

* GraphiQL GUI: http://localhost:8080/membership-service/
* Example get all Ids:  http://localhost:8080/membership-service/graphql?query={memberships{membershipId}}
* Example REST get all: http://localhost:8080/membership-service/rest
* [Some example GraphQL queries](EXAMPLE.md)
* [TODO](TODO.md)

