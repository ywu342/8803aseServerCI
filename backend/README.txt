Backend - README

Implementation has been performed using JDO. And deploying to Google App
Engine. This is a Java Project. To run, please import to eclipse. Maven
is not required. The libraries outside of typical jars that have been
used are as follows. Please download then and add them to the WEB-INF
folder, in case of error.
https://mvnrepository.com/artifact/com.google.code.gson/gson/2.3.1

Once imported the project is imported in Eclipse, use, run as Web
Application to test the project. Please use PostMan or
AdvancedRestClient for testing the APIs.

User Registration: 
URL: http://localhost:8888/userregistration
JSON: 
{
"name": "Ricket",
    "email": "jiji@gmail.com",
    "password": "bbt312rr33"
}

Response:
{
  "code": 200,
  "descrip": "User Created Successfully",
  "smallUser": {
    "key": {
      "kind": "User",
      "id": 6473924464345088
    },
    "name": "Ricket",
    "email": "jiji@gmail.com",
    "password": "bbt312rr33"
  }
}

If the user being created has an email that belongs to an existing user:

Response:
{
  "code": 400,
  "descrip": "User could not be created Successfully.User with email id already exists",
  "smallUser": {
    "name": "Ricket",
    "email": "jiji@gmail.com",
    "password": "bbt312rr33"
  }
}



User Login: 
URL: http://localhost:8888/userregistration?name=Hannah&email=anuanu79ww39@gmail.com&password=bbt312

Response:

{
  "code": 200,
  "descrip": "User Found",
  "smallUser": {
    "key": {
      "kind": "User",
      "id": 5348024557502464
    },
    "name": "James",
    "email": "local@gmail.com",
    "password": "books"
  }
}


If the user is not found:
Response:
{
  "code": 404,
  "descrip": "User not found"
}




