## Create docker images
`sbt docker:publishLocal` will create an image for each service.

- `distributed-systems-course/products:latest`
- `distributed-systems-course/users:latest`

## Run containers
`docker-compose up -d` will use the images created to run two instances of each service.

- ProductServers at ports 8001 and 8002
- UserServers at ports 9001 and 9002