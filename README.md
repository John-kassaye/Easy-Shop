
# 🛍️ E-Commerce Backend API

A Spring Boot REST API backend for an e-commerce web application. This backend supports user registration and login, product browsing with filtering, admin-controlled category and product management, shopping cart functionality, and user profile handling.

---
<details>

<summary> 📦 Features </summary>

### ✅ Existing Functionality
- User Registration & Login
- Browse products by category
- Search and filter products

</details>

<br>

<details>
<summary> 🔐 Security </summary>

- Role-based access:
  - `USER`: View products, manage own cart, profile
  - `ADMIN`: Manage categories and products
- Spring Security + JWT authentication (if included)

</details>

<br>

<details>
<summary> 🗃️ Tech Stack </summary>
- Java 17+
- Spring Boot
- Spring Security
- MySQL
- JPA / JDBC
- Maven

</details>

<br>

<details>
<summary> 🚀 Running the Application </summary>

1. **Configure Database**:  
   Update `application.properties` with your MySQL credentials.
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/ecommerce
   spring.datasource.username=root
   spring.datasource.password=yourpassword
   ```

2. **Run the Application**:
   ```bash
   ./mvnw spring-boot:run
   ```

3. **Test Endpoints**:  
   Use [Postman](https://www.postman.com/) or a frontend to interact with:
   - `http://localhost:8080/products`
   - `http://localhost:8080/categories`
   - `http://localhost:8080/cart`
   - `http://localhost:8080/profile`


</details>

<br>

<details>
<summary> 📁 Project Structure (Important Files) </summary>

```
src/
├── main/
│   ├── java/
│   │   └── com.example.ecommerce/
│   │       ├── controller/
│   │       │   ├── CategoriesController.java
│   │       │   ├── ProductsController.java
│   │       │   └── ShoppingCartController.java
│   │       ├── dao/
│   │       │   ├── MySqlCategoriesDao.java
│   │       │   └── MySqlProfileDao.java
│   │       ├── model/
│   │       │   ├── Category.java
│   │       │   └── Product.java
│   │       └── EcommerceApplication.java
│   └── resources/
│       └── application.properties
```

---
</details>

<br>

<details>
<summary> 📌 Notes </summary>

- Login is required for cart and profile endpoints.
- Ensure role checking is enforced with annotations (e.g., `@PreAuthorize`, `@Secured`).
- Keep search functionality and update methods under test coverage to catch future bugs.

</details>
