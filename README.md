# Membership service

This is an example application that demonstrate how to add [GraphQL](http://facebook.github.io/graphql) to your existing JAX-RS Application.

***
## Watch a short [screencast here](http://bit.ly/gql-screencast)
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

Also:

* MicroProfile Health: http://localhost:8080/health/

### Run some examples:

In the [GraphiQL](http://localhost:8080/membership/graph/graphiql/) GUI try out some of the [examples](EXAMPLE.md)

## Openshift

You can also deploy and run this in OpenShift.
First log into the OpenShift enviroment:

    oc login
    
If you do not already have a project, create one:

    oc new-project demo --description="Some demo applications" --display-name="Demo"

Then run 

    mvn clean fabric8:deploy -Popenshift


## MicroProfile GraphQL Proposal.

To get this example running using the MicroProfile GraphQL Proposed API:

1. Get the MicroProfile GraphQL Api from the sandbox and build it locally:

    ```bash
    git clone https://github.com/eclipse/microprofile-sandbox.git
    cd microprofile-sandbox
    mvn clean install
    ```
    This will add ```io.microprofile.sandbox | microprofile-graphql-api``` to your local maven repo.

1. Get the [graphql-spqr (microprofile-proto branch)](https://github.com/leangen/graphql-spqr/tree/microprofile-proto) and build it locally:

    ```bash
    git clone https://github.com/leangen/graphql-spqr.git
    cd graphql-spqr
    git checkout microprofile-proto
    mvn clean install
    ```
    This will add ```io.leangen.graphql | spqr``` (microprofile-proto branch) to your local maven repo.

1. Get this example and switch to the microprofile branch:

    ```bash
    git clone https://github.com/phillip-kruger/membership.git
    cd membership
    git checkout microprofile
    mvn -Pthorntail clean install
    ```

This will start the example as describe above, but this time using the MicroProfile API

### TODO

Move all to a thorntail fraction, and not as part of the war.
