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
