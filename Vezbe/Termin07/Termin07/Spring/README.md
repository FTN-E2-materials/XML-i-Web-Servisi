# Microservices Demo

Restaurant application implemented in spring boot framework. NGINX is used as
API Gateway and Docker DNS service as service discovery.

In order to set up infrastructure run the following command:
`docker-compose up --build`.

Application testing is available via `web_requests.sh` script.

In order to destroy infrastructure run the following command:
`docker-compose down -v`.
