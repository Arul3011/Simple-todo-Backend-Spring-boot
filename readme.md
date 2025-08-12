# Spring Boot + JWT Setup Notes

## Install dependencies
```bash
mvn clean install

```
# to run 

```bash
mvn spring-boot:run
```


# Spring Security JWT Authentication Flow

## 1. Create the Entity
- Define a `User` entity class with fields like `id`, `email`, `password`, `roles`.
- Annotate with `@Entity` for JPA to create the table.

## 2. Create the Repository
- Create an interface extending `JpaRepository<User, Long>`.
- Add custom query methods (e.g., `findByEmail`).

## 3. Create the Service
- Implement business logic for user operations.
- Interact with the repository for CRUD.
- Use `BCryptPasswordEncoder` to store passwords securely.

## 4. Create JWT Utility
- Use `Jwts.builder()` to generate tokens.
- Use `Jwts.parser()` to validate tokens.
- Include methods to:
  - Generate token
  - Validate token
  - Extract username from token

## 5. Create Middleware (Filter)
- Extend `OncePerRequestFilter`.
- Steps inside filter:
  1. Read `Authorization` header.
  2. Extract the token.
  3. Validate the token.
  4. Load the user from DB.
  5. Set authentication in `SecurityContextHolder`.

## 6. Create Authentication Controller
- **`/register`**:
  - Accept user details.
  - Encrypt password with BCrypt.
  - Save user in DB.
- **`/login`**:
  - Authenticate using `AuthenticationManager`.
  - Generate JWT token on success.
  - Return token to the client.

## 7. Configure Spring Security
- Disable CSRF.
- Allow public access to:
  - `/auth/**`
  - Swagger paths (`/swagger-ui/**`, `/v3/api-docs/**`)
- Require authentication for other endpoints.
- Add JWT filter before `UsernamePasswordAuthenticationFilter`.

## 8. Flow Summary
1. User registers → password encrypted → saved in DB.
2. User logs in → credentials verified → JWT generated → sent to client.
3. Client sends JWT in `Authorization: Bearer <token>` header.
4. Middleware verifies JWT and sets authentication.
5. Secured endpoints are accessible only with valid JWT.

