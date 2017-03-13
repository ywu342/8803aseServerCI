## Project 1

### Registration Scenario

#### Mobile-Server
* API defined by students:
  * POST `/api/accounts/create`
    * Input: AccountAuth object
    * Output: Account object
    * Description: "Get the current user"
* **Comments:**
  * The account object, returned as output, contains the authorization token which is probably created at server side (this is not how I would do it but it should work)
  * I would not add `create` in the URL because it is not necessary and usually not the style of REST APIs
  * I would add first and last name to the AccountAuth object

#### Web-Server
* API defined by students:
  * Same as in the case of Mobile-Server
* **Comments:**
  * Same considerations as in the case of Mobile-Server hold here

#### Server-Backend
* API defined by students:
  * POST `/api/users`
    * Input: User name, password, email
    * Output: User id
    * Description: "Create a new user"
* **Comments:**
  * I like this api better than the one for Mobile-Server and Web-Server
  * You should also pass, as input parameter, the name of the user because you have a user object with a name field
  * I guess the output value (User id) indicates whether the registration of the new user was successful. **The way in which this should be implemented is by returning `201 Created` status code and maybe (if necessary) including in the entity of the response the URI(s) of the newly created resource as mentioned [here]( http://www.restapitutorial.com/httpstatuscodes.html) or the object representing the user**

### Login Scenario

#### Mobile-Server
* API defined by students:
  * POST `/api/accounts/login`
    * Input: AccountAuth object
    * Output: Account object
    * Description: "Get the user with the provided user ID"
* **Comments:**
  * This should be a GET not a POST
  * I would not add `login` in the URL because it is not necessary and usually not the style of REST APIs
  * The description should probably be updated. How would you know the user id before logging in?

#### Web-Server
* API defined by students:
  * Same as in the case of Mobile-Server
* **Comments:**
  * Same considerations as in the case of Mobile-Server hold here

#### Server-Backend
* GET	`/api/users/{userid}`
  * Input:
  * Output: User Object
  * Description: "Get the user with a particular user id (restricted access)"
* **Comments:**
  * How would you know the user id before logging in? Maybe you query the backend based on the AccountAuth passed from the client to get the user id first?
  * You should use a return value (`200 OK`) together with the User Object.