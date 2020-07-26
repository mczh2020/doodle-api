# Doodle Backend/API


## TDLR
Run the following to create a container image from code and start the container as well as a MongoDB instance.

```bash
./gradlew jibDockerBuild & docker-compose up -d
```

## Building Container Image

You can run the command below to create a container image using *Jib*. 

```bash
./gradlew jibDockerBuild
```

Instead of writing our own Docker file, we relied on [Jib](https://github.com/GoogleContainerTools/jib) to create a lean container image and to benefit from a pretty reasonable image build time and also to spare some time.
Spring Boot's built-in image build capability has also seen a lot of improvement lately, the images Spring natively however tend to be a bit on the larger side. We would not make such a call very easily in a real project. Some important aspects to consider would be : 
- What version of a container base image we want to use(an Org's preferred distro, security(Jib created images attack surface are much smaller) )
- What version of a JDK we care to run on (Jib supported JDK11 as of now)
- If Org has enough experience with Containers, it might go for creating its own Docker files to have more precise control over every aspect. 

## Running Backend and its dependencies

Running the following command will run API component and a MongoDB instance. 
```bash
docker-compose up -d
```

App configuration file (*application.properties*) contains the coordinates of the MongoDB and in this case, the server address is set to *mongodb*. 
The name 'mongodb' happens to be the docker-compose service name and when the API component also runs a container from the same docker-compose, it is able to resolve it with no issues.
If you were to run the java component outside the container, 
you would need to make sure that hostname denoted by property *spring.data.mongodb.host* is resolvable. [In test configuration](test/resources/application.properties), we set  *spring.data.mongodb.host* to be localhost.

## Test Data

Running java tests will populate the database with sample data that is in the 'test/resources/data/polls.json' file. You can run the java test with the command below:

```bash
 ./gradlew clean test
```

Build.gradle file contains a commented in-memory MongoDB reference, you can uncomment it to run your tests towards an in-memory db if required.
 

   
## Design considerations

### API
Poll search capability is published under */api/v1/polls*. *v1* is handled on the controller level to potentially allow different 
classes to handle different versions of the api. *api* is handle on the servlet context level to avoid duplication if additional rest resources were to be added.

Exercise requires the following functionality to be implemented:
>
>1. List all polls created by a user
>2. Search polls by its title
>3. List all polls created after a certain date

As it's not clear for me from this definition whether it should be possible to do a search which expects all search criteria to be applied that the same time, 
it was decided to implement the list/search capability in mutual-exclusive manner i.e. either search for polls from a user **OR** search for polls with a title **OR** search for polls created after a date.

So *polls* was chosen to represent an endpoint that would return one more polls based on criteria that are defined py different url parameters. 

**Paging** , **Authentication**, **Authorization** were deliberately left out in order to finish the assignment in a short amount of time.


### Spring Boot & Gradle
Spring Boot was chosen to have web/rest & data repository capabilities provided out of the box. Overall programming experience with Spring Boot - given how much can be easily accomplished in a little amount - usually does not disappoint.  
Gradle is a first class citizen with Spring and together with its DSL, configuration becomes easier to read/write compared to goold old Maven.

###  Data store / MongoDB
Data seamed well suited for a NoSQL store and given the potential scaling-out benefits and how well it's accepted in the industry, MongoDB was chosen. It plays pretty well with Spring and the query language of MongoDB is easy to grasp.
Some indexes were added to data, these can provide some potential quick gains. Though further work would be required for performance optimization.
For converting JSON data to Java POJOs an online tool http://www.jsonschema2pojo.org/ is used. Long was used to represent all numeric values for simplicity/lack of time, 
knowing more about the actual data and potential values, Pojos and their representations in MongoDB could/should be optimized.  

### Containerization

See the 'Building Container Image' section for considered aspects.
