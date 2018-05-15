-- 0 = FREE, 1 = FULL
insert into Person (id, surname) values (1, 'Kruger')
insert into Membership (membershipId, owner_id, membership_type) values (1, 1, 1)
insert into Person_names (Person_id, name) values (1, 'Natus')
insert into Person_names (Person_id, name) values (1, 'Phillip')

insert into Person (id, surname) values (2, 'Kruger')
insert into Membership (membershipId, owner_id, membership_type) values (2, 2, 1)
insert into Person_names (Person_id, name) values (2, 'Charmaine')
insert into Person_names (Person_id, name) values (2, 'Juliet')

insert into Person (id, surname) values (3, 'van der Merwe')
insert into Membership (membershipId, owner_id, membership_type) values (3, 3, 1)
insert into Person_names (Person_id, name) values (3, 'Koos')

insert into Person (id, surname) values (4, 'van der Westhuizen')
insert into Membership (membershipId, owner_id, membership_type) values (4, 4, 0)
insert into Person_names (Person_id, name) values (4, 'Minki')