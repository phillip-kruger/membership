### Get all memberships:
    {
        memberships {
            owner {
                names
            }
            type
        }
    }

#### with a fragment:

    {
        memberships {
         ...fullMembership
        }
    }

    fragment fullMembership on Membership {
            membershipId
        owner{
           ...owner
        }
        type
    }

    fragment owner on Person {
        id
        names
        surname  
    }

#### with a fragment and named:

    query Memberships {
        memberships{
            ...fullMembership
        }
    }

    fragment fullMembership on Membership {
        membershipId
        owner{
            ...owner
        }
        type
    }

    fragment owner on Person {
        id
        names
        surname  
    }

#### and only FREE memberships

    query FilteredMemberships {
        memberships(filter:{
            type:FREE
        }){
            ...fullMembership
        }
    }

### Get a single membership (by id):
    {
        membership(membershipId:2){
            owner{
                names
                surname
            }
        }
    }

#### with a fragment, variable and named:

    query Membership($id:Int!) {
        membership(membershipId:$id){
            ...fullMembership
        }
    }

    fragment fullMembership on Membership {
        membershipId
        owner{
          ...owner
        }
        type
    }

    fragment owner on Person {
        id
        names
        surname  
    }

and then variable:

    {
        "id":1
    }

#### with a fragment, variable, named and directive

    query Membership($id:Int!,$withOwner: Boolean!) {
        membership(membershipId:$id){
            ...fullMembership
        }
    }

    fragment fullMembership on Membership {
        membershipId
        owner @include(if: $withOwner){
          ...owner
        }
        type
    }

    fragment owner on Person {
        id
        names
        surname  
    }

and then variable:

    {
      "id": 1,
      "withOwner": false
    }

#### with a fragment, variable, named, directive and default value

    query Membership($id:Int!,$withOwner: Boolean!,$noId: Boolean = false) {
        membership(membershipId:$id){
            ...fullMembership
        }
    }

    fragment fullMembership on Membership {
        membershipId @skip(if: $noId)
        owner @include(if: $withOwner){
          ...owner
        }
        type
    }

    fragment owner on Person {
        id @skip(if: $noId)
        names
        surname  
    }

and then variable:

    {
      "id": 1,
      "withOwner": true,
      "noId": true
    }

### Pagination

    query Memberships($itemsPerPage:Int!,$pageNumber:Int!) {
        memberships(
            first:$itemsPerPage,
                skip:$pageNumber) {
            membershipId
                owner{
                    names
                    surname
                }
            type
        }
    }

and then variables:

    {
      "itemsPerPage": 1,
      "pageNumber": 0
    }

increase the pageNumber variable to page
### Create a new member:

    mutation CreateMember {
        createMembership(membership: {type:FULL,owner: {names: "Piet",surname:"Pompies"}}) {
            membershipId
                owner{
                    id
                    names
                    surname
                }
            type
        }
    }

#### with a fragment:

    mutation CreateMember {
        createMembership(membership: {type:FULL,owner: {names: "Minki",surname:"van der Westhuizen"}}) {
            ...fullMembership
        }
    }

    fragment fullMembership on Membership {
        membershipId
        owner{
            ...owner
        }
        type
    }

    fragment owner on Person {
        id
        names
        surname  
    }

#### with a fragment and variables:

    mutation CreateMember($membership: MembershipInput!) {
        createMembership(membership:$membership) {
            ...fullMembership
        }
    }

    fragment fullMembership on Membership {
        membershipId
        owner{
            ...owner
        }
        type
    }

    fragment owner on Person {
        id
        names
        surname  
    }

and then variable:

    {
        "membership": {
            "owner": {
                "names": "Christina",
                "surname": "Storm"
            },
            "type": "FULL"
        }
    }   

### Edit a membership


    mutation EditMember($membership: MembershipInput!) {
        createMembership(membership:$membership) {
            ...fullMembership
        }
    }

#### Add umlaut on the surname
    {
        "membership": {
          "membershipId": 2,
            "owner": {
                "names": [
                "Charmaine",
                "Juliet"
              ],
                "surname": "Krüger"
            },
            "type": "FULL"
        }
    }

### Delete a membership
    
    mutation DeleteMembership($id:Int!){
        deleteMembership(membershipId:$id){
          ...fullMembership
        }
    }

and then variable:

    {
        "id":1
    }


### Exceptions

    mutation CreateMember($membership: MembershipInput!) {
        membership(membership:$membership) {
            ...fullMembership
        }
    }

and then variable:

    {
        "membership": {
            "owner": {
                "names": "Christina",
                "surname": "S"
            },
            "type": "FULL"
        }
    }   

## Introspection

    {
        __schema {
            queryType {
                name
                fields {
                    name
                }
            }
            mutationType{
                name
                fields{
                    name
                }
            }
            subscriptionType {
                name
                fields{
                    name
                }
            }
        }
    }

    {
        __type(name: "Membership") {
            name
            kind
            fields {
                name
                args {
                    name
                }
            }
        }
    }