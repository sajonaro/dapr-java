
## Description 

This project explores [dapr](https://docs.dapr.io/), more specifically, `dapr's` [service invocation](https://docs.dapr.io/getting-started/quickstarts/serviceinvocation-quickstart/) capability, applied to 2 microservices ran in docker containers, both written in Java:
- `sender` invokes service `receiver's` /ping method via HTTP using dapr


## How to demo the PoC

- Step 1
    ```bash
    # stand up the docker compose stack
    $ docker compose up -d --build
    ```

- Step 2
  
    ```bash
    # check that receiving service is up and running via curl from the host
    $ curl http://localhost/ping
    ```
- Step 3
  
    ```bash
    # attach to `sender` container and run the java application in it
    $ docker compose exec sender java -jar /app/target/SenderService-1.0.jar
    ```
- Alternatively (Step 4)
  ```bash
  # run the curl command directly from within `sender` container
  $ docker compose exec sender curl http://localhost:3500/v1.0/invoke/receiver/method/ping
  ```

## Explanation

 - docker-compose.yml contains definitions of `sender` and `receiver` services (sender  invokes receiver's 'ping' method), to enable dapr based communication between `sender` and `receiver` for each service we define `service_dapr` container with daprd (a.k.a. sidecar) sharing service container's network via network_mode: {namespace}:{server_name} configuration)    
 - Steps 3 and 4 were only possible because dapr enabled method invocation via well defined path: http://localhost:3500/v1.0/invoke/**SERVICE_NAME**/method/**API_ROUTE** where:  
    - 3500 is the default dapr HTTP port
    - **SERVICE_NAME** - is the name of the service `receiver` in the network
    - **API_ROUTE** - is the URL of the method to inkoke relative to the service `receiver` 
 - Final detail: (actually optional) we use [traefik](https://traefik.io/traefik/) as api proxy to give `receiver's` method the route `/ping` relative to the receiver's host, e.g. `curl http://localhost/ping` run from the host invokes this microservice. In other words, without traefik, this we can just use `curl http://localhost:8087` command
## issues

- issue with unavailable docker.sock on linux
```bash
 # chown docker.sock 
 $ sudo chown $(whoami):$(whoami) /var/run/docker.sock
```