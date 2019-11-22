# Room booking system

Aim of this project was to create REST API that would allow to book rooms available in the office.
Application is able to:
- manage available rooms
- manage users
- book room if it is available in particular time frame
- present booking schedule for all rooms for particular time frame
- present booking schedule for selected room for particular time frame
- present booking schedule for a user for particular time frame

## Manage user

### PUT on /user/
- creates new user and stores it in database
- login of user is his id at the same time. It can't be changed later on
- during creation of new user all fields are required
- PUT request body JSON:

`curl "http://localhost:8080/user" -i -X PUT -H "Content-Type: application/json" -d "{ "login":"jkowalski", "name":"Jan","surname":"Kowalski", "password":"123456"}"`

`{ "login":"jkowalski", "name":"Jan","surname":"Kowalski", "password":"123456"}`

- user entity description
  - name: text, max. length 50, required
  - surname: text, max. length 100, required
  - login: text, max. length 100, required, unique (allows to identify user)
  - password: text, max. length 100, min. length 6, required

### POST on /user/{userLogin}
- updates user properties 
- login is obligatory as it identifies the user entity. Other properties are optional and when they are missing, system don't change them in database
- POST request body JSON
- following example changes name of jkowalski user from Jan to John. Rest of jkowalski data remains the same.

`curl "http://localhost:8080/user/jkowalski" -i -X PUT -H "Content-Type: application/json" -d "{ "login":"jkowalski", "name":"John"}"`

`{ "login":"jkowalski", "name":"John"}`

### DELETE on /user/{userLogin}

- removes given user from database

`curl "http://localhost:8080/user/jkowalski" -i -X DELETE`

### GET on /users/
- returns all user from database 

`curl "http://localhost:8080/user/" -i -X GET`

returns:

`[{"login":"jsmith","name":"John","surname":"Smith"},{"login":"jdoe","name":"Jane","surname":"Doe"}]`

## Manage rooms

### PUT on /room/
- creates new room and stores it in database
- room name is id at the same time. It can't be changed later on
- PUT request body JSON:

`curl "http://localhost:8080/user" -i -X PUT -H "Content-Type: application/json" -d "{ "name":"Large Room", "location":"1st floor", "numberOfSeats":20, "hasProjector":"true", "phoneNumber": "22-22-22-22" }"`

`{ "name":"Large Room", "location":"1st floor", "numberOfSeats":20, "hasProjector":"true", "phoneNumber": "22-22-22-22" }`

- room entity description
  - room name: text, max. length 50, required, unique (allows to identify room)
  - location description: text, max. length 256, optional
  - number of seats: number, max. 100, required
  - projector: yes/no, optional, default: no
  - phone number: text, max. length 100, optional

### POST on /room/{roomName}

- updates room properties 
- room name is obligatory as it identifies the room entity. Other properties are optional and when they are missing, system don't change them in database
- POST request body JSON
- following example changes number of seats in Large Room from 20 to 30. Rest of room data remains the same.

`curl "http://localhost:8080/user/jkowalski" -i -X PUT -H "Content-Type: application/json" -d "{ "name":"Large Room", "numberOfSeats":30}"`

`{ "name":"Large Room", "numberOfSeats":30}`

### DELETE on /room/roomName

- removes given room from database

`curl "http://localhost:8080/room/Large Room" -i -X DELETE`

### GET on /room/

- returns all rooms from database

`curl "http://localhost:8080/room/" -i -X GET`

returns:

`[{"name":"Large Room","location":"1st floor","numberOfSeats":10,"hasProjector":true,"phoneNumber":"22-22-22-22"},{"name":"Medium Room","location":"1st floor","numberOfSeats":6,"hasProjector":true,"phoneNumber":null},{"name":"Small Room","location":"2nd floor","numberOfSeats":4,"hasProjector":false,"phoneNumber":null}}]`

## Booking management 

### PUT on /bookRoom/

- books the room and stores it in database
- PUT request body JSON:

`curl "http://localhost:8080/bookRoom" -i -X PUT -H "Content-Type: application/json" -d "{ "roomName":"Large Room","userLogin":"jsmith","dateStart":"2019-09-12 6:30:45", "dateEnd":"2019-09-12 8:09:34"}"`

- roomBook entity description
  - room name: text, max. length 50, required
  - login: text, max. length 100, required, unique (allows to identify user)
  - dateStart: text, in yyyy-MM-dd HH:mm:ss format, required
  - dateEnd, in yyyy-MM-dd HH:mm:ss format, required
  
### GET on /bookRoom/

- returns list of all bookings for a particular time frame

`curl "http://localhost:8080/bookRoom?dateStart=DATE1&dateEnd=DATE2" -i -X GET`

returns:

`[
     {
         "roomName": "Large Room",
         "userName": "John",
         "userSurname": "Smith",
         "dateStart": "2020-09-12 14:30:45",
         "dateEnd": "2020-09-12 15:09:34"
     } ...
 ]`

- query parameter description
    - dateStart: text, in yyyy-MM-dd HH:mm:ss format
    - dateEnd, in yyyy-MM-dd HH:mm:ss format
   
- if both parameter given, API returns bookings for given time frame
- if only dateStart given, API returns bookings after given date
- if only dateEnd given, API return bookings before given date
- if none of dates given, API returns all bookings stored 
    

### GET on /roomBook/givenRoom

- returns list of all bookings for a particular time frame and room

`curl "http://localhost:8080/bookRoom?dateStart=DATE1&dateEnd=DATE2&roomName=ROOMNAME" -i -X GET`

- if both parameter given, API returns bookings for given time frame
- if only dateStart given, API returns bookings after given date
- if only dateEnd given, API return bookings before given date
- if none of dates given, API returns all bookings stored 

### GET on /roomBook/givenUser

- returns list of all bookings for a particular time frame and user

`curl "http://localhost:8080/bookRoom?dateStart=DATE1&dateEnd=DATE2&roomName=ROOMNAME" -i -X GET`

- if both parameter given, API returns bookings for given time frame
- if only dateStart given, API returns bookings after given date
- if only dateEnd given, API return bookings before given date
- if none of dates given, API returns all bookings stored 

### How to run
To start the project locally use start.bat file.


Tools used:
- Java 1.8
- In mememory H2 database
- Project lombok
- Spring boot starter JPA
- Spring boot starter web