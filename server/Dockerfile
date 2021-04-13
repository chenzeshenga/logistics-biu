FROM openjdk:8-jdk
MAINTAINER chenzeshenga "chenzeshenga@163.com"
VOLUME /tmp/biu
ADD target/server-1.0.jar /server-1.0.jar
EXPOSE 8888
EXPOSE 11111
ENTRYPOINT ["java","-jar","server-1.0.jar -DXmx=500m -DXms=500m -Djava.rmi.server.hostname=10.2.149.212 -Dcom.sun.management.jmxremote -Dcom.sun.management.jmxremote.port=11111 -Dcom.sun.management.jmxremote.authenticate=false -Dcom.sun.management.jmxremote.ssl=false"]