# Spring-Boot-REST-API

Simple CRUD Book store RESTful API

Dependencies:
> Spring Boot Web

> Spring Boot Data JPA

> Spring Boot Security

> Lombok

> MySQL / H2

## REST API endpoints 
Admin routes
| Method | URL  | Response code | Description | Auth
|--|--|--|--|--|
| POST  | /api/admin | 201 | Create new user | Required
| DELETE  | /api/admin/deleteUser/{id} | 200 | Delete user | Required
| GET| /api/admin/listAllUsers | 200 | List all users | Required

User routes
| Method | URL  | Response code | Description | Auth
|--|--|--|--|--|
| GET | /api/users/checkEmailAvailability | 200 |  Check if email is available | None
| GET | /api/users/checkUsernameAvailability | 200 |  Check if usernaime is available | None
| GET | /api/users/me | 200 |  Show logged user profile | Required
| GET | /api/users/profile/{username} | 200 |  Search users profile | Required
| PUT | /api/users/update/{username} | 201 | Update user | Required

Auth routes
| Method | URL  | Response code | Description
|--|--|--|--|
| POST | /api/auth/signup | 201 |  Sign up

Book routes
| Method | URL  | Response code | Description | Auth
|--|--|--|--|--|
| GET | /api/books | 200 |  List all books | None
| DELETE | /api/books/delete/{id} | 200 |  Delete book | Required
| GET | /api/books/findByAuthor | 200 |  Find by author | None
| GET | /api/books/findByCategory/{id} | 200 |  Find by category | None
| GET | /api/books/findByDesc | 200 |  Find by description | None
| GET | /api/books/findByISBN/{isbn} | 200 |  Find by ISBN | None
| GET | /api/books/findByTitle | 200 |  Find by title | None
| POST | /api/books/save | 201 |  Create new book | Required
| PUT | /api/books/update/{id} | 201 | Update book | Required

Category routes
| Method | URL  | Response code | Description | Auth
|--|--|--|--|--|
| GET | /api/category| 200 |  List all categories | None
| POST | /api/category | 201 |  Create new category | Required
| DELETE | /api/category/deleteCategory/{id} | 200 |  Delete category | Required
| PUT | /api/category/update/{id} | 201 | Update category | Required
