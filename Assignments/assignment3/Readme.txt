Running Crawler:
note: The 5 at the end is the parameter for depth limit
    * PowerShell:
        mvn exec:java "-Dexec.mainClass=assignment3.CrawlerMain" "-Dexec.args=https://www.kijiji.ca/b-apartments-condos/canada/c37l0 5"
   
    * Linux / Command Prompt
        mvn exec:java -Dexec.mainClass="assignment3.CrawlerMain" -Dexec.args="https://www.kijiji.ca/b-apartments-condos/canada/c37l0 5"

Running Webserver:
note: server runs on localhost:8080
    * All
        tomcat7:Run