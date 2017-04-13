# Server
Implemented as a Maven Java project using Jersey RESTful Web Services framework. The server used during development is Tomcat v8.0. Please import the server directory as a Maven project in Eclipse. Then you can run the app on top of a selected server.

## APIs:
- User Registration (POST):
  * URL: http://35.187.194.28:8080/server/users/register
  * Input JSON: 
  ~~~~ 
  {
     "name": "Ricket",
     "email": "jiji@gmail.com",
     "password": "bbt312rr33"
  }
~~~~
  * Response:
  
  If succeed:
  ~~~~
  {
     "token": token
  }
  ~~~~
  If the user being created has an email that belongs to an existing user:
  ~~~~
  {
     "code": 400,
     "descrip": "User could not be created Successfully.User with email id already exists",
     "smallUser": {
         "name": "Ricket",
         "email": "jiji@gmail.com",
     "password": "bbt312rr33"
     }
  }
  ~~~~

- User Login (POST): 
  * URL: http://35.187.194.28:8080/server/users/login
  * Input JSON:
  ~~~~
  {
     "name": "Ricket",
     "email": "jiji@gmail.com",
     "password": "bbt312rr33"
  }
  ~~~~
  * Response:
   If succeed:
  ~~~~
  {
     "token": token
  }
  ~~~~
  If the user is not found:
  ~~~~
  {
     "code": 404,
     "descrip": "User not found"
  }
  ~~~~
~~~~
## Tests written with Junit
1. Import the project as maven project
2. Maven build the project and then run the TestServer.java as Junit.

## Unit tests listed below: All tests are based on calling http requests to the server REST API
* testLoginFUserFPass() : log in with wrong email, wrong password
* testLoginFUserTPass() : log in with wrong email, right password
* testLoginInvalidEmail() : log in with invalid email, right password
* testLoginMissEmail() : log in with empty email field, right password
* testLoginMissPassword() : log in with right email field, empty password
* testLoginResponseCodeValidUser() : log in with right email, right password, check if http call returns right response code
* testLoginTokenValidUser() : log in with right email, right password, check if http call returns right token
* testLoginTUserFPass() : log in with right email, wrong password
* testRegisterDuplicateEmail() : register with an existing email
* testRegisterInvalidEmail() : register with an invalid email
* testRegisterMissEmail() : register with an empty email field
* testRegisterMissPassword() : register with an empty password field
* testRegisterMissUsername() : register with an empty name field
* testRegisterResponseCodeValidUser() : register with right email, right password, check if http call returns right response code
* testRegisterTokenValidUser() : register with right email, right password, check if http call returns right token
