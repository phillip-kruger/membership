# membership

Membership service (to demonstrate [GraphQL](http://facebook.github.io/graphql))

## Links:

* GraphiQL GUI: http://localhost:8080/membership-service/
* Example get all Ids: http://localhost:8080/membership-service/graphql?query={getAllMemberships{membershipId}}
* Example REST: http://localhost:8080/membership-service/rest

## TODO:

* Add mutations
* Add filters
* Add client in Test
* Get swagger & swagger ui to work (https://dzone.com/articles/creating-documented-rest-apis-with-wildfly-swarm)
* Look at flyway for test data creation ?
* Create [fraction](https://wildfly-swarm.gitbooks.io/wildfly-swarm-users-guide/fraction_authoring.html)

CREATE SCHEMA `membership` DEFAULT CHARACTER SET utf8 ;


## Some example queries

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

# }
