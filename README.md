🚗 Car Rental System – Vehicle Booking Platform

📌 Overview-
The Car Rental System is a backend web application built using Spring Boot that enables customers to book cars and manage rentals, while admins handle cars, users, and bookings.
The system provides role-based access control using Spring Security and BCrypt password encryption for secure operations.


🛠️ Tech Stack

-Backend: Spring Boot, Spring Security, Spring Data JPA
-Database: MySQL
-Language: Java
-Security: BCrypt Password Encryption, Role-based Access Control
-Testing: Postman
-Build Tool: Maven

✨ Features

👤 User Features

Register and login securely
View available cars
Book cars with rental dates, pickup & dropoff locations
Cancel bookings (changes status instead of deleting)
View active and past booking history


🧑‍💼 Admin Features

Add, update, and delete car listings
View all users and bookings
Permanently delete bookings if required
Manage cars’ availability and rental pricing 


🔒 Security Features

Authentication and Authorization with Spring Security
Password encryption using BCrypt
Role-based access for Admin and User
Protected endpoints with access control


🧩 API Endpoints
🔑 Authentication (Public)

| Method | Endpoint                    | Description                                           |
| ------ | --------------------------- | ----------------------------------------------------- |
| POST   | `/api/auth/register`        | Register new user *(default role: USER)*              |
| PUT    | `/api/auth/update-password` | Update password using username, old, and new password |



🚗 Car Management (Admin Only)

| Method | Endpoint                   | Description                                        |
| ------ | -------------------------- | -------------------------------------------------- |
| POST   | `/api/booking`             | Book a car                                         |
| GET    | `/api/booking/me`          | Get bookings for the logged-in user                |
| PUT    | `/api/booking/{id}/cancel` | Cancel a booking                                   |
| GET    | `/api/booking/active`      | View all active bookings for logged-in user        |
| GET    | `/api/booking/history`     | View user booking history (cancelled or completed) |
| GET    | `/api/booking/all`         | Get all bookings *(Admin only)*                    |
| GET    | `/api/booking/active/all`  | Get all active bookings *(Admin only)*             |


📅 Booking Management

(User & Admin Access – Role-Based)
| Method | Endpoint                   | Description                                        |
| ------ | -------------------------- | -------------------------------------------------- |
| POST   | `/api/booking`             | Book a car                                         |
| GET    | `/api/booking/me`          | Get bookings for the logged-in user                |
| PUT    | `/api/booking/{id}/cancel` | Cancel a booking                                   |
| GET    | `/api/booking/active`      | View all active bookings for logged-in user        |
| GET    | `/api/booking/history`     | View user booking history (cancelled or completed) |
| GET    | `/api/booking/all`         | Get all bookings *(Admin only)*                    |
| GET    | `/api/booking/active/all`  | Get all active bookings *(Admin only)*             |


🧑‍💻 Admin Management (Admin Only)

com.CarRentalSystem
 ┣ 📂 config              # Security configuration (Spring Security)
 ┣ 📂 controller          # REST controllers (Auth, Car, Booking, Admin)
 ┣ 📂 dto                 # DTOs (BookingResponseDTO, PasswordUpdateRequest)
 ┣ 📂 model               # Entity classes (User, Car, Booking)
 ┣ 📂 repositories        # JPA Repositories
 ┣ 📂 service             # CustomUserDetailsService for authentication
 ┣ 📜 CarRentalSystemApplication.java
 ┗ 📜 application.properties

⚙️ Installation & Setup

1️⃣ Clone the repository-
git clone https://github.com/Tejas767/CarRentalSystem.git
cd CarRentalSystem

2️⃣ Configure MySQL-
Create a database named car_rental_db
Update your MySQL credentials in application.properties

3️⃣ Run the project-
mvn spring-boot:run


4️⃣ Test APIs-
Use Postman to test APIs at:
👉 http://localhost:8080
