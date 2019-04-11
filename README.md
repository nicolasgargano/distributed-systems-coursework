## Create docker images
`sbt docker:publishLocal` will create an image for each service.

- `distributed-systems-course/products:latest`
- `distributed-systems-course/users:latest`

## Run containers
`docker-compose up -d` will provide:

- PostgreSQL database at port 5432
- GraphQL Playground at http://localhost:5000/playground
- ProductServers at ports 8001 and 8002
- UserServers at ports 9001 and 9002
