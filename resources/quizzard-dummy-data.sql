-- Create some base user roles
insert into user_roles 
values 
	('7c3521f5-ff75-4e8a-9913-01d15ee4dc96', 'ADMIN'),
	('7c3521f5-ff75-4e8a-9913-01d15ee4dc97', 'BASIC_USER'),
	('7c3521f5-ff75-4e8a-9913-01d15ee4dc98', 'PREMIUM_USER');


-- Create a new Quizzard user
insert into app_users 
values ('7c3521f5-ff75-4e8a-9913-01d15ee4dc99', 'Tester', 'McTesterson', 'tester@revature.com', 'tester99', 'p4$$W0RD', '7c3521f5-ff75-4e8a-9913-01d15ee4dc97');

-- Create some categories for flashcards and study sets
insert into categories
values 
    ('7c3521f5-ff75-4e8a-9913-01d15ee4dc9a', 'Java'),
    ('7c3521f5-ff75-4e8a-9913-01d15ee4dc9b', 'SQL');

-- Create some flashcards made by the newly created user
insert into flashcards
values 
	('7c3521f5-ff75-4e8a-9913-01d15ee4dc9a', 'What does JDK stand for?', 'Java Development Kit', '7c3521f5-ff75-4e8a-9913-01d15ee4dc99', '7c3521f5-ff75-4e8a-9913-01d15ee4dc9a'),
	('7c3521f5-ff75-4e8a-9913-01d15ee4dc9b', 'What does JVM stand for?', 'Java Virtual Machine', '7c3521f5-ff75-4e8a-9913-01d15ee4dc99', '7c3521f5-ff75-4e8a-9913-01d15ee4dc9a');

-- Create a study set belonging to the new user
insert into study_sets 
values ('7c3521f5-ff75-4e8a-9913-01d15ee4dc9c', 'Tester Java Set', '7c3521f5-ff75-4e8a-9913-01d15ee4dc99', '7c3521f5-ff75-4e8a-9913-01d15ee4dc9a');

-- Add the new flashcards to the newly created study set
insert into study_set_cards 
values
    ('7c3521f5-ff75-4e8a-9913-01d15ee4dc9c', '7c3521f5-ff75-4e8a-9913-01d15ee4dc9a'),
    ('7c3521f5-ff75-4e8a-9913-01d15ee4dc9c', '7c3521f5-ff75-4e8a-9913-01d15ee4dc9b');

   
-- Get user by username
select * from app_users where username = 'tester99';

-- Get user by email
select * from app_users where email = 'tester@revature.com';

-- Get user by username and password 
select * from app_users where username = 'tester99' and password = 'p4$$W0RD';

-- Get a user's study sets and the contained cards within
select au.username, ss.name, c.category_name, f.question_text, f.answer_text
from study_set_cards ssc 
join study_sets ss 
on ssc.study_set_id = ss.id 
join flashcards f
on ssc.flashcard_id = f.id
join categories c 
on ss.category = c.id
join app_users au 
on ss.owner_id = au.id
where ss.owner_id = '7c3521f5-ff75-4e8a-9913-01d15ee4dc99';

-- Get a study set and its category + user info
select ss.name, c.category_name, au.username
from study_sets ss
join categories c
on ss.category = c.id
join app_users au 
on ss.owner_id  = au.id 
where ss.id = '7c3521f5-ff75-4e8a-9913-01d15ee4dc9c';



