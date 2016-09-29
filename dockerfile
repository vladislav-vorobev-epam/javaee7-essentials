FROM jboss/wildfly
MAINTAINER Vlad Vorobev

RUN /opt/jboss/wildfly/bin/add-user.sh admin admin_123  --silent
RUN cp /opt/jboss/wildfly/standalone/configuration/standalone-full.xml /opt/jboss/wildfly/standalone/configuration/standalone.xml
CMD ["/opt/jboss/wildfly/bin/standalone.sh",  "-c", "standalone-full.xml", "--debug", "-b", "0.0.0.0", "-bmanagement", "0.0.0.0"]

EXPOSE 8787
#ADD target/todo.war /opt/jboss/wildfly/standalone/deployments/

