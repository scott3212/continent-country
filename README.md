### Pre-requisite
- Make sure gradle is installed and configured properly
- Make sure docker is installed and configured properly
- This project was written under JDK17, however JDK15, and JDK11 are also tested. For Other JDK version, make sure change the jdk version in Dockerfile.

### Build Steps
- Navigate to root direction of the project
- Run `gradle build`
- Once gradle build completed, run `docker build -t bootdocker:1 .`
- Once docker build completed, run `docker run -d --name bootdocker -p 8080:8080 bootdocker:1`
- The app should now run on port 8080, go to Swagger URL: [Continent Country API](http://localhost:8080/swagger-ui.html)

### Quick walk through
- All requirements under "Notes" are completed.
- Minimum test coverage due to time constraints.
- There are totally 3 endpoints in this project

| Endpoint | is secured? | Description |
| -------- | ----------- | ------------|
| POST /api/register | No | Allow user to register |
| POST /api/login    | No | Allow user to login, so it can be used for Bonus Task |
| GET /api/continent/othercountries?codes=CA,US | Both | This is the required endpoint in this takehome project |

- Postman collection is also attached, file name `ContinentCountries.postman_collection.json`

### Bonus Task progress:
- Check if a user is authenticated - Done
- Limit request per seconds based on user login status - Not Implemented