# Server
Implemented as a Maven Java project using Jersey RESTful Web Services framework. The server used during development is Tomcat v8.0. Please import the server directory as a Maven project in Eclipse. Then you can run the app on top of a selected server.

## APIs:
- User Registration (POST):
  * url: http://localhost:8080/server/users/register
  * JSON input: 
{
 "id":"1",
 "name":"Yaling",
 "password":"correct",
 "email":"yaling@gatech.edu"
}
  * Response: 200 ok
- User Authentication ()
