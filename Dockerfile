FROM java:8 

# Install maven
RUN apt-get update
RUN apt-get install -y maven
RUN apt-get install -y libboost-regex-dev libicu-dev zlib1g-dev
RUN apt-get install -y libboost-system-dev libboost-program-options-dev libboost-thread-dev

WORKDIR /code

# Prepare by downloading dependencies
ADD pom.xml /code/pom.xml
RUN ["mvn", "dependency:resolve"]
RUN ["mvn", "verify"]

# Add freeling
# ADD freeling-4.0-trusty-amd64.deb /code/freeling-4.0-trusty-amd64.deb
RUN pwd
RUN wget https://github.com/TALP-UPC/FreeLing/releases/download/4.0/freeling-4.0-trusty-amd64.deb
# RUN dpkg -i /code/freeling-4.0-trusty-amd64.deb

# Adding source, compile and package into a fat jar
ADD src /code/src
RUN ["mvn", "package"]

EXPOSE 4567
CMD ["/usr/lib/jvm/java-8-openjdk-amd64/bin/java", "-jar", "target/sparkexample-jar-with-dependencies.jar"]
