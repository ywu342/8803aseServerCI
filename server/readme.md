# Server
Implemented as a Maven Java project using Jersey RESTful Web Services framework. The server used during development is Tomcat v8.0. Please import the server directory as a Maven project in Eclipse. Then you can run the app on top of a selected server.

## APIs:
- User Registration (POST):
  * URL: http://localhost:8080/server/users/register
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
  * URL: http://localhost:8080/server/users/login
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
