#Doodle Backend/API


##TDLR
Run the following to create a container image from code and start the container as well as a MongoDB instance.

```bash
./gradlew jibDockerBuild & docker-compose up -d
```

## Building Container Image

You can run the command below to create a container image using *Jib*. 

```bash
./gradlew jibDockerBuild
```

Instead of writing our own Docker file, we relied on [Jib](https://github.com/GoogleContainerTools/jib) to create a lean container image and to benefit from a pretty reasonable image build time.
Spring Boot's built-in image build capability has also seen a lot of improvement lately, the images Spring natively however tend to be a bit on the larger side. We would not make such a call very easily in a real project. Some important aspects to consider would be : 
- What version of a container base image we want to use(an Org's preferred distro, security(Jib created images attack surface are much smaller) )
- What version of a JDK we care to run on (Jib supported JDK11 as of now)
- If Org has enough experience with Containers, it might go for creating its own Docker files to have more precise control over every aspect. 

## Running Backend and its dependencies

Running the following command will run API component and a MongoDB instance. 
```bash
docker-compose up -d
```

App configuration file (*application.properties*) contains the coordinates of the MongoDB and in this case, the server address is set to *mongodb*. The name 'mongodb' happens to be the docker-compose service name and when the API component also runs a container from the same docker-compose, it is able to resolve it with no issues.
If you were to run the java component outside the container, you would need to make sure that hostname denoted by property *spring.data.mongodb.host* is resolvable. [In test configuration](test/resources/application.properties), we set  *spring.data.mongodb.host* to be localhost.

## Test Data

Running java tests will populate the database with sample data that is in the 'test/resources/data/polls.json' file. You can run the java test with the command below:

```bash
 ./gradlew clean test
```

Build.gradle file contains a commented in-memory MongoDB reference, you can uncomment it to run your tests towards an in-memory db if required.
 

   

