## RabbitMQ

### Installation

1. Install Homebrew (package manager for macOS):
   - Run the command:
     ```
     /bin/bash -c "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/HEAD/install.sh)"
     ```

2. Install RabbitMQ:
   - Run the command:
     ```
     brew install rabbitmq
     ```

### Starting RabbitMQ

1. Start RabbitMQ service:
   - Run the command:
     ```
     brew services start rabbitmq
     ```

2. Verify the status of RabbitMQ:
   - Run the command:
     ```
     brew services list
     ```

3. Access RabbitMQ management interface:
   - Open a browser and visit: http://localhost:15672/
   - Login with the default credentials: username: `guest`, password: `guest`

# Docker Compose README

## Prerequisites
- Docker: Make sure Docker is installed on your machine.

## Setup
1. Clone or download the repository containing the `docker-compose.yml` file to your development environment.

## Build the Application
1. Navigate to the directory that contains the `docker-compose.yml` file.
2. Run the command `docker-compose build` to build the necessary Docker images for the application.

## Start the Application
1. Make sure you are in the directory that contains the `docker-compose.yml` file.
2. Run the command `docker-compose up -d` to start the application's containers in the background (`-d` for detached mode).

3. Monitor the output in your terminal to verify that the containers have started successfully. You can also check the status of the containers using the command `docker-compose ps`.

The application should now be running and accessible according to the configuration defined in your `docker-compose.yml` file.

## Logging into PostgreSQL
1. Open your terminal or command prompt.
2. Run the command `psql -U postgres` to log in to PostgreSQL as the `postgres` user.

## Basic PostgreSQL Commands
Here are some basic commands that you can use in the PostgreSQL terminal:

- `\l`: List all databases.
- `\c database_name`: Connect to a specific database.
- `\dt`: List all tables in the current database.
- `SELECT * FROM table_name;`: Retrieve all records from a specific table.
- `INSERT INTO table_name (column1, column2) VALUES (value1, value2);`: Insert a new record into a table.

Feel free to explore more advanced PostgreSQL commands and features in the official PostgreSQL documentation.

## Conclusion
This README has provided you with the basic instructions for building and updating an application using Docker Compose. Make sure to refer to the official Docker Compose documentation for more advanced features and available options.

Happy coding!
