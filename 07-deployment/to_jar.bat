@echo off
set SERVICES=(lab4\gateway lab4\city-management lab4\country-management config-server discovery-service)

for %%S in %SERVICES% do (
    start cmd /k "cd %%S && mvn clean package -DskipTests && exit"
)

echo All build processes started in separate windows.
pause