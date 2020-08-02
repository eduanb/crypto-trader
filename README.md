**Crypto Trader**

This ia a small Kotlin Spring Boot crypto trader bot. This is only for experimental purposes and should not be used as a real bot with your real money.

Currently, the only implementation is for Binance.

To use just fill in these 2 properties:

`binance.key`

`binance.secret`

**NOTE: DO NOT COMMIT THESE TO GIT. MAKE USE OF ENVIRONMENT VARIABLES OR IDE RUN SETTINGS!**

This app stores state in a Postgres DB. To run a local Postgres in Docker:

`docker run --name crypto-trader -e POSTGRES_DB=crypto-trader -e POSTGRES_USER=crypto-trader -e POSTGRES_PASSWORD=mysecretpassword -p 5432:5432 -d postgres`