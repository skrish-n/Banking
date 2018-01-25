# Banking
A simple banking application using servlets.

# Features
-User login in (assuming user exists in db) 
-select user role(only available)
-transfer money to another account present in the db
-logout 
-cookies to remember login details.

# Oracle Database
There are two tables used:
1. Name: 'Banking'
   columns:3 
   col1: name varchar
   col2: password varchar
   col3: role varchar
2. Name: 'Account_details'
   columns:4
   col1: name varchar
   col2: balance int
   col3: account_number
   col4: phone_number
  
  
