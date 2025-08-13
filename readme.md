
#  Simple Todo Application

##  Created using Spring Boot

---

##  Getting Started

###  Clone the repository
```bash
git clone https://github.com/Arul3011/Simple-todo-Backend-Spring-boot.git
cd Simple-todo-Backend-Spring-boot
```

---

###  Install dependencies
```bash
mvn install
```

---

###  Run the application
```bash
mvn spring-boot:run
```

---

###  Access the application  
**Base URL:**  
```
http://localhost:8080
```

---

## API Endpoints

### **Auth Controller**

#### Register User
**POST** `/auth/reg`  
**Request Body:**
```json
{
  "email": "user@example.com",
  "password": "mypassword"
}
```
**Response:**
```
"User registered successfully"
```

---

#### Login User
**POST** `/auth/login`  
**Request Body:**
```json
{
  "email": "user@example.com",
  "password": "mypassword"
}
```
**Response Example:**
```json
{
  "token": "your-jwt-token",
  "user": {
    "email": "user@example.com"
  }
}
```

---

####  Test Authentication
**GET** `/auth/test`  
**Response:**
```
"Authorized"
```

---

### **Todo Controller**

####  Add a Todo
**POST** `/todo/add`  
**Request Body:**
```json
{
  "id": 1,
  "task": "Buy milk"
}
```
**Response:**
```
"task added"
```

---

####  Get All Todos
**GET** `/todo/get`  
**Response Example:**
```json
[
  { "id": 1, "task": "Buy milk", "status": false },
  { "id": 2, "task": "Read a book", "status": true }
]
```

---

####  Get Paginated Todos
**GET** `/todo/page`  

**Query Parameters:**
- `page` (integer, default = 0) — Page number
- `size` (integer, default = 10) — Page size

**Example:**
```
/todo/page?page=0&size=5
```

**Response Example:**
```json
{
  "totalPages": 3,
  "totalElements": 12,
  "content": [
    { "id": 1, "task": "Buy milk", "status": false },
    { "id": 2, "task": "Read book", "status": true }
  ]
}
```

---

####  Delete a Todo
**DELETE** `/todo`  
**Request Body:**
```json
1
```
(where `1` is the todo ID)  

**Response:**
```json
true
```

---

####  Change Todo Status
**PATCH** `/todo/task`  
**Request Body:**
```json
1
```
(where `1` is the todo ID)  

**Response:**
```
"Status updated"
```

---

## Summary of Endpoints

| Method | Endpoint    | Description         |
| ------ | ----------- | ------------------- |
| POST   | /auth/reg   | Register new user   |
| POST   | /auth/login | Login and get token |
| GET    | /auth/test  | Test authentication |
| POST   | /todo/add   | Add new todo        |
| GET    | /todo/get   | Get all todos       |
| GET    | /todo/page  | Get paginated todos |
| PATCH  | /todo/task  | Change todo status  |
| DELETE | /todo       | Delete todo by ID   |
