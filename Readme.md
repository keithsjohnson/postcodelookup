AWS DynamoDB Postcode Store and Locator
---------------------------------------

Uses Spring Boot, Spring Data 

Postcode Location URLs
----------------------
Save a Postcode and location:
http://localhost:8080/savepostcodelocation?postcode=ST7 2YB?latitude=1&longitude=2&population=100&households=30
http://localhost:8080/savepostcodelocation?postcode=SK4 2HD?latitude=8&longitude=9&population=0&households=0

Locate a Postcode:
http://localhost:8080/locatepostcode?postcode=ST7 2YB
http://localhost:8080/locatepostcode?postcode=SK4 2HD
