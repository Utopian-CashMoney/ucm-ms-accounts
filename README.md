# Accounts Microservice
## Configuration
1. Set the following environment variables:
    * mysql-host
    * mysql-port
    * mysql-db
    * mysql-user
    * mysql-pass
    * h2-user
    * h2-pass
2. Set the active profile
    * Setting an environment variable (Unix/Linux): `export spring_profiles_active=local`
    * Setting an environment variable (NT): `set spring_profiles_active=local`
    * Currently, supports `local`, `staging`
