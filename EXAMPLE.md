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

### Create a new member:

    mutation CreateMember {
        membership(membership: {type:FULL,owner: {names: "Piet",surname:"Pompies"}}) {
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
        membership(membership: {type:FULL,owner: {names: "Minki",surname:"van der Westhuizen"}}) {
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
        membership(membership:$membership) {
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