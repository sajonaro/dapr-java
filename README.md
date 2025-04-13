## issues

- issue with unavailable docker.sock on linux
```bash
 # chown docker.sock 
 $ sudo chown $(whoami):$(whoami) /var/run/docker.sock
```