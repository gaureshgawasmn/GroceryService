# Use the official MySQL image as the base image
FROM mysql:latest

# Copy the initialization SQL script into the Docker container
COPY MysqlInit.sql /docker-entrypoint-initdb.d/init.sql

# Define environment variables
ENV MYSQL_ROOT_PASSWORD=admin
ENV MYSQL_DATABASE=qp-assessment
ENV MYSQL_USER=admin
ENV MYSQL_PASSWORD=admin

# Expose MySQL port
EXPOSE 3306