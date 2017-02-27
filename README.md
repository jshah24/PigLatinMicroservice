Install apache-tomcat-8.0.14 </br>
Change the server.xml in the tomcat configuration to run on port 80 </br>
(http://stackoverflow.com/questions/4756039/how-to-change-the-port-of-tomcat-from-8080-to-80)
Copy the jar file (translation.jar) in the tomcat-->webapps folder </br>
Start Server</br>
Use any REST client and call the microservice at: </br>
HTTP method supported: POST </br>
Request url: http://localhost:80/translation/PigLatin/getTranslation </br>
Raw Payload: <String or paragraph to be translated to Pig Latin> </br>

Curl: </br>
curl -X POST -H "Cache-Control: no-cache" -d 'This is a good exercise!' "http://localhost/translation/PigLatin/getTranslation" </br>


Assumptions: </br>
A word with punctuations before, after or in between the word will preserve the punctuation order. </br>
Example: </br>
What? --> atWhay? </br>
!Bill --> !illBay </br>
I've --> Iay'evay </br>
