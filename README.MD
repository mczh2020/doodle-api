#Doodle Backend/API

## Building Container Image

You can run the command below to create a container image using *Jib* . 

```bash
./gradlew jibDockerBuild
```

Instead of writing our own Docker file, we relied on [Jib](https://github.com/GoogleContainerTools/jib) to create a lean contanier image and image build time is pretty reasonable.
 

   

