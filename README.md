# Grocery Application API Documentation

This is Spring Boot application provides RESTful APIs to manage grocery items and orders.

## API Documentation

The API documentation is generated using Swagger. You can access the Swagger UI to explore and test the APIs.

Swagger UI: `http://localhost:8081/swagger-ui/index.html`

## End points

### Grocery Controller

#### Save Grocery

- **Description:** Saves a new grocery item.
- **URL:** `/v1/admin/groceries`
- **Method:** `POST`
- **Request Body:**
    - JSON object representing the grocery item to be saved.
- **Response:**
    - 200 OK: Grocery saved successfully.
    - 400 Bad Request: Grocery not saved.
    - 403 Forbidden: Unauthorized access.
    - 500 Internal Server Error: Server encountered an unexpected error.

#### Delete Grocery

- **Description:** Deletes a grocery item by ID.
- **URL:** `/v1/admin/groceries/{id}`
- **Method:** `DELETE`
- **Path Variable:**
    - `id`: ID of the grocery item to be deleted.
- **Response:**
    - 200 OK: Grocery deleted successfully.
    - 403 Forbidden: Unauthorized access.
    - 500 Internal Server Error: Server encountered an unexpected error.

#### Update Grocery

- **Description:** Updates an existing grocery item.
- **URL:** `/v1/admin/groceries`
- **Method:** `PUT`
- **Request Body:**
    - JSON object representing the updated grocery item.
- **Response:**
    - 200 OK: Grocery updated successfully.
    - 400 Bad Request: Grocery not updated.
    - 403 Forbidden: Unauthorized access.
    - 500 Internal Server Error: Server encountered an unexpected error.

#### Find Grocery by ID

- **Description:** Retrieves a grocery item by ID.
- **URL:** `/v1/admin/groceries/{id}`
- **Method:** `GET`
- **Path Variable:**
    - `id`: ID of the grocery item to retrieve.
- **Response:**
    - 200 OK: Grocery found successfully.
    - 403 Forbidden: Unauthorized access.
    - 500 Internal Server Error: Server encountered an unexpected error.

#### Find All Groceries

- **Description:** Retrieves all grocery items.
- **URL:** `/v1/admin/groceries`
- **Method:** `GET`
- **Response:**
    - 200 OK: Groceries found successfully.
    - 403 Forbidden: Unauthorized access.
    - 500 Internal Server Error: Server encountered an unexpected error.

### Order Controller

#### Save Orders

- **Description:** Saves a list of orders.
- **URL:** `/v1/user/orders`
- **Method:** `POST`
- **Request Body:**
    - JSON array representing the list of orders to be saved.
- **Response:**
    - 200 OK: Orders saved successfully.
    - 400 Bad Request: Orders not saved.
    - 403 Forbidden: Unauthorized access.
    - 500 Internal Server Error: Server encountered an unexpected error.

#### Get All Orders by User

- **Description:** Retrieves all orders for the current user.
- **URL:** `/v1/user/orders`
- **Method:** `GET`
- **Response:**
    - 200 OK: Orders retrieved successfully.
    - 403 Forbidden: Unauthorized access.
    - 500 Internal Server Error: Server encountered an unexpected error.

### User Controller

#### Save User

- **Description:** Saves a new user.
- **URL:** `/v1/admin/users`
- **Method:** `POST`
- **Request Body:**
    - JSON object representing the user to be saved.
- **Response:**
    - 200 OK: User saved successfully.
    - 400 Bad Request: User already exists or not saved.
    - 403 Forbidden: Unauthorized access.
    - 500 Internal Server Error: Server encountered an unexpected error.

#### Update User

- **Description:** Updates an existing user.
- **URL:** `/v1/admin/users`
- **Method:** `PUT`
- **Request Body:**
    - JSON object representing the updated user.
- **Response:**
    - 200 OK: User updated successfully.
    - 400 Bad Request: User not updated.
    - 403 Forbidden: Unauthorized access.
    - 500 Internal Server Error: Server encountered an unexpected error.

#### Delete User

- **Description:** Deletes a user by user ID.
- **URL:** `/v1/admin/users/{userId}`
- **Method:** `DELETE`
- **Path Variable:**
    - `userId`: ID of the user to be deleted.
- **Response:**
    - 200 OK: User deleted successfully.
    - 403 Forbidden: Unauthorized access.
    - 500 Internal Server Error: Server encountered an unexpected error.

#### Get User by User ID

- **Description:** Retrieves a user by user ID.
- **URL:** `/v1/admin/users/{userId}`
- **Method:** `GET`
- **Path Variable:**
    - `userId`: ID of the user to be retrieved.
- **Response:**
    - 200 OK: User retrieved successfully.
    - 403 Forbidden: Unauthorized access.
    - 500 Internal Server Error: Server encountered an unexpected error.

#### Update Self Details

- **Description:** Updates self details.
- **URL:** `/v1/user/users`
- **Method:** `PUT`
- **Request Body:**
    - JSON object representing the updated user details.
- **Response:**
    - 200 OK: User details updated successfully.
    - 400 Bad Request: User details not updated.
    - 403 Forbidden: Unauthorized access.
    - 500 Internal Server Error: Server encountered an unexpected error.

## Installation and Setup

1. Clone the repository:
   ```bash
   git clone https://github.com/gaureshgawasmn/GroceryService
    ```
2. Import the project into your IDE as a Gradle project. (It should automatically build your project if not then use the `gradle build` command from the directory where build.gradle is present)
3. After succesful build, run the following command to run the application.
   ```bash
   gradle bootRun
   ```
4. The application will be accessible at `http://localhost:8081/`.
5. Access the swagger UI at `http://localhost:8081/swagger-ui/index.html` to test the APIs.

### Version Information
1. Java 17 is required to run the application.
2. Gradle 8.5 is required to build the application.

### Docker Image Support
1. There are two option available to create docker image
   - Gradle task 
     - To create the docker images added the gradle task `dockerBuildImage` in build.gradle file.
     - Before running the docker image, make sure to change the `customImageName` in build.gradle file.
     - To create the docker image run the following command from the directory where build.gradle is present.
        ```bash
        gradle dockerBuildImage
        ```
   - Using Dockerfile
     - To create the image using docker file run the following command from the root directory
       ```bash
       docker build -t <customImageName> .
       ```
       example 
        ```bash
       docker build -t gaureshgawasmn/techlab-grocery-service .
       ```
     - Using DockerfileUsingJar (this will create image out of the already created jar in libs folder)
       ```bash
       docker build -f DockerFileUsingJar -t <customImageName> .
       ``` 
       example
       ```bash
       docker build -f DockerfileUsingJar -t gaureshgawasmn/techlab-grocery-service:0.0.1 .
       ```
       
2. To run the docker image run the following command.
   ```bash
    docker run -p <RequiredPortToExpose>:8081 <customImageName>
    ```
   example :
   ```bash
    docker run -p 8085:8081 docker.io/gaureshgawasmn/techlab-grocery-service
    ```
   ```bash
    docker run -p 8082:8081 docker.io/gaureshgawasmn/techlab-grocery-service:0.0.1
    ```
4. The application will be accessible at `http://localhost:<RequiredPortToExpose>/`.
5. Access the swagger UI at `http://localhost:<RequiredPortToExpose>/swagger-ui/index.html` to test the APIs.


## Predefined User Roles

### Admin Users

- **Default Admin**
  - **Username:** admin
  - **Password:** admin
  - **User Role:** ADMIN, USER

- **Ram**
  - **Username:** ram@xyz.com
  - **Password:** Ram@123
  - **User Role:** ADMIN, USER

### Regular Users

- **Default User**
  - **Username:** user
  - **Password:** user
  - **User Role:** USER

- **Sundar**
  - **Username:** sundar@xyz.com
  - **Password:** sundar@123
  - **User Role:** USER

- **Anil**
  - **Username:** anil@xyz.com
  - **Password:** anil@123
  - **User Role:** USER

- **Gopal**
  - **Username:** gopal@xyz.com
  - **Password:** gopal@123
  - **User Role:** USER
