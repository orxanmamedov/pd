# A-Music2-Etalon-eureka-server

### Running a project locally with Docker

 Run docker compose and then u can enter to swagger api:localhost:9001/swagger-ui/index.html#/


 Because i had no time for configuring reverse proxy verification of email is not working when app was run from docker because keycloak doesn`t allow connections from external host. So run everything beside the app from docker:
1. Run keycloack, keycloak db, and postgres db from docker
2. Run express bank app from ide or from artifact

Flow

![image](https://github.com/orxanmamedov/pd/assets/140556961/57a38488-b3a0-4f6a-a73d-f3e53b4c539d)

then u need to approve
![image](https://github.com/orxanmamedov/pd/assets/140556961/f877cd27-e941-49cc-aba9-ac3d1de5a229)
by clicking
![image](https://github.com/orxanmamedov/pd/assets/140556961/a6f0c06a-a780-4fc5-bef1-edce37317fc6)
and now u can login
![image](https://github.com/orxanmamedov/pd/assets/140556961/f31806b2-11e2-4566-968f-a2de946fb38e)
then pass bearer token to authorize box
![image](https://github.com/orxanmamedov/pd/assets/140556961/cb003b40-0413-4ffc-8738-2e7e7b273c13)
now u can add deposit to ur authorized user's account
![image](https://github.com/orxanmamedov/pd/assets/140556961/7671c2b4-b58a-4d5e-a6e9-f6d90af7065a)
and get list of available stocks
![image](https://github.com/orxanmamedov/pd/assets/140556961/207596b3-df11-4b62-8989-e7662a5ee710)







