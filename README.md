File Operation API

Spring Boot API and upload it on the GitHub. You should include Unit Test and readme.txt file.
Implement a RESTFul API spring-boot application that provides the following APIs:
• API to upload a file with a few meta-data fields. Persist meta-data in persistence store (In memory DB or file system and store the content on a file system)
• API to get file meta-data
• API to download content stream (Optional)
• API to search for file IDs with a search criterion (Optional)
• Write a scheduler in the same app to poll for new items in the last hour and send an email (Optional)

Tech Stack:
Springboot, Maven, Swagger2.

Github Repository:
Download project from the following git repository https://github.com/manobusy/file-api

To run the application:
Download the project. Navigate to the project folder and run mvn spring-boot:run 
Read the API documentation in Swagger2 
(run http://localhost:8080/swagger-ui.html on the browser and try to do the file operation)

Upload API URL - http://localhost:8080/fileapi/v1/upload

Download API URL - http://localhost:8080/fileapi/v1/download/{fileName}
Note: It download the file from upload location...give the same file name. Also try to run from browser not swagger-ui to dowonload the file

FileMetaDatas API URL - http://localhost:8080/fileapi/v1/filemetadatas

FileMetaData by Id API URL - http://localhost:8080/fileapi/v1/filemetadata/id
Note: Get the id from the above URL output.

Also whenever application is re-run it will be clean up the DB records. Retry with upload service and then trigger find all or find by id

To view the logs
Navigate to the project folder - /log/fileapi.log
