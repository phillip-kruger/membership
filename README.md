# Membership service

This is an example application that demonstrate how to add [GraphQL](http://facebook.github.io/graphql) to your existing JAX-RS Application.

TODO: Insert high-level diagram

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

## Some example GraphQL queries

### Get all memberships
    {
        memberships {
            owner {
                names
            }
            type
        }
    }

### Get a single membership (by id)
    {
        membership(membershipId:2){
            owner{
                names
                surname
            }
        }

    }

## TODO:

* Add mutations
* Add filters
* Add client in Test
* Get swagger & swagger ui to work (https://dzone.com/articles/creating-documented-rest-apis-with-wildfly-swarm)
* Look at flyway for test data creation ?
* Create [fraction](https://wildfly-swarm.gitbooks.io/wildfly-swarm-users-guide/fraction_authoring.html)