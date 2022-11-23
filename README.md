# Endava Free Course project 2022 (using Spring framework).

<p align = "justify"> This project represents a small Rest API which handles requests for two types of entities: <b><i>User</i></b> and <b><i>Mentor</i></b>.
There is also the third entity <b><i>Industry</i></b> which cannot be affected by the API. All the entities are stored in a local Postgres SQL 
database (which is generated using <b><i>hibernate</i></b>).</p>

The relations between them are: 
    <ol>
      <li>`One-to-one` between User and Mentor;</li>
      <li>`Many-to-many` between Mentor and industry;</li>
    </ol>

<p align = "justify"> The relations are assigned in the <b><i>UserService</i></b> and <b><i>MentorService</i></b> classes. The methods from them are called in
<b><i>UserController</i></b> and <b><i>MentorController</i></b> respectively (as arguments they are receiving object dtos and dao interface which
extends <b><i>JpaRepository</i></b> is responsible for interracting with the database). Firstly, the industries are randomly assigned to a mentor
on creation and then when a user is created the mentor is assigned to it.</p>

<p align = "justify"> The app is protected using Basic Authentification and it can be tested when entering the link: 
<a href = "http://localhost:8080/swagger-ui/index.html#/">http://localhost:8080/swagger-ui/index.html#/</a> when the application is running. I am using <b><i>Swagger UI</i></b>
to test my app in a more easier way and provide it with a basic UI.</p> 

<p align = "justify"> There are also available tests for services but they still need to be fixed.</p>
