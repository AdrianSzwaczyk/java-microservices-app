$scriptDir = Split-Path -Parent $MyInvocation.MyCommand.Definition
Set-Location $scriptDir

# docker build --no-cache -t gateway ./lab4/gateway
# docker build --no-cache -t city-management ./lab4/city-management
# docker build --no-cache -t country-management ./lab4/country-management
# docker build --no-cache -t angular-app ./angular-app
# docker build --no-cache -t discovery-service ./discovery-service
# docker build --no-cache -t config-server ./config-server

docker-compose down
docker-compose up -d
