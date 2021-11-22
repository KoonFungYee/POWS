# POWS

Pre-required steps
Step 1: Install erlang, download link: http://www.erlang.org/downloads
Step 2: Install RabbitMQ, download link: https://www.rabbitmq.com/download.html

Kindly modify application.properties to configure path, port and db connection (postgreSQL) after clone the projects. RabbitMQ transaction occur on update stock in product and stock quantity will be deducted based on message received from quantity's ordered. Message can be view in RabbitMQ screen via http://localhost:15672/#/
