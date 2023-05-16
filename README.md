# ClarityTest
Start the database with docker-compose up
if running on an m1 mac i believe you need to add the line platform: linux/x86_64 into the compose file under the service declaration but i havent been able to test this

Run the app as a standard sping boot app once the db has launched. 

Ports 3306 for the db and 8080 for the app need to be free
