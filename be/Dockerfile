FROM tomcat:latest

RUN rm -rf /usr/local/tomcat/webapps/*

COPY smarthome.war /usr/local/tomcat/webapps/

COPY wait-for-it.sh /usr/local/bin/wait-for-it.sh

# Make sure the wait-for-it script is executable
RUN chmod +x /usr/local/bin/wait-for-it.sh

ENTRYPOINT ["wait-for-it.sh", "db:3306", "--", "catalina.sh", "run"]

EXPOSE 8080