sudo apt -y install maven
sudo apt-get -y install openjdk-13-jre
sudo apt-get -y install openjdk-13-jdk
mvn clean install
java -jar ./target/*.jar