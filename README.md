<div align="center">

<img src="https://github.com/user-attachments/assets/69c60e9d-344b-496f-8358-5bb65c0f7ba0" alt="Sunrise Dental Clinic Logo" width="250" />

# Sunrise Dental Clinic Management System

An enterprise-grade, distributed Java desktop application built to manage the daily operations of the Sunrise Dental Clinic. This system features a robust 3-tier MVC architecture, advanced MySQL database integration, real-time SMTP email notifications, and automated PDF invoice generation.

</div>

<hr>

# Sunrise Dental Clinic Management System

A comprehensive, automated software solution designed to streamline the daily operations of a modern dental facility, replacing traditional paper-based record-keeping.

> Developed as part of the **CIS6003 - Advanced Programming** assessment (Cardiff Met / ICBT Campus).

---

## 📖 Overview

Sunrise Dental Clinic is a busy private dental center that previously managed patient appointments and treatment records manually. This system digitizes that entire workflow - from staff login, to appointment scheduling, to billing - while preventing common issues like double bookings and lost records.

---

## 🚀 Key Features

- **Secure Authentication** - Staff login protected with salted SHA-256 password hashing (no plain-text passwords stored).
- **Appointment Management** - Real-time scheduling with database-level double-booking prevention (MySQL trigger).
- **Automated Billing** - Dynamic total cost calculation and automated PDF receipt generation using iTextPDF.
- **Notifications** - Background, multithreaded email alerts for appointment confirmations, built using the Factory design pattern.
- **Data Management** - Complete patient and dentist history tracking, with search by appointment number or patient name.
- **Help Section** - Built-in step-by-step guidance for new staff members.

---

## 🛠️ Technologies & Architecture

| Category | Details |
|---|---|
| **Language** | Java |
| **Database** | MySQL (relational schema with triggers & stored procedures) |
| **IDE** | Apache NetBeans |
| **Architecture** | 3-Tier (Presentation - Business Logic - Data Access) |
| **Design Patterns** | MVC, DAO, Singleton, Factory Method |
| **PDF Generation** | iTextPDF |
| **Email Service** | Jakarta Mail (SMTP) |
| **CI/CD** | Automated build & test pipeline via GitHub Actions (Ant) |
| **Testing** | JUnit 5 |

---

## 🏗️ Architecture Diagram

The system follows a clean **3-tier architecture**, where each layer only communicates with the one directly below it:

### 1️⃣ Presentation Layer
> `view` package
Java Swing UI - Login screen, Dashboard, and all data-entry forms.

⬇️

### 2️⃣ Business Logic Layer
> `controller` package
Controllers & Services - Authentication, Appointment handling, Billing, and Notifications.

⬇️

### 3️⃣ Data Access Layer
> `dao` package
DAO classes - all SQL queries live here, isolated from the UI, with a Singleton-managed DB connection.

⬇️

### 4️⃣ MySQL Database
The single source of truth for all patient, dentist, appointment, and billing records.

---

## 📂 Project Structure

**SunriseDentalClinic/**

- **`src/model/`** - Entity classes (`Patient`, `Dentist`, `Staff`, `Appointment`, `Bill`, ...)
- **`src/dao/`** - Data Access Objects (SQL queries, fully isolated from the UI)
- **`src/controller/`** - Business logic & services (Auth, Appointment, Notification)
- **`src/view/`** - Swing UI forms (Login, Dashboard, Register, Search, Bill, ...)
- **`src/util/`** - `DBConnection` (Singleton), `PasswordUtil`, `ValidationUtil`
- **`src/db/`** - Database schema & seed data (SQL script)
- **`test/`** - JUnit test classes
- **`README.md`** - Project documentation

---

## ⚙️ Setup & Installation

1. **Clone the repository**
   ```
   git clone <repository-url>
   ```

2. **Database setup** - Import the `sunrise_dental_clinic.sql` file (located in the `db` folder) into your MySQL server via phpMyAdmin/WAMP to create the schema and seed data.

3. **Configure credentials** - Copy `config.properties.example` (in the project root) to `config.properties` and fill in your own database and email credentials. This file is git-ignored and never committed, so your credentials stay private.
   ```properties
   db.url=jdbc:mysql://localhost:3306/sunrise_dental_clinic
   db.user=root
   db.password=

   email.sender=your_email@gmail.com
   email.app.password=your_16_char_app_password
   ```

4. **Open project** - Open the `SunriseDentalClinic` directory using Apache NetBeans IDE.

5. **Build & Run** - Clean and Build the project, then run `LoginUI.java` to start the application.

   **Default staff login** - Username: `staff1` / Password: `s123`

---

## 🔒 Security Notes

- Passwords are never stored or compared in plain text - a salted SHA-256 hash (`util.PasswordUtil`) is used for all staff credentials.
- Database and email credentials are kept out of source control via `config.properties` (see setup step 3) - no secrets are hardcoded in the repository.
- All SQL queries use `PreparedStatement` to prevent SQL injection.

---

## ✅ Testing

JUnit test classes cover authentication, appointment logic, and billing calculations. Run them directly from NetBeans (`Test Packages` → right-click → **Run**), or via the GitHub Actions CI pipeline, which runs automatically on every push.

---

## 📄 License

This project was developed for academic purposes as part of the CIS6003 Advanced Programming module and is not licensed for commercial use.

---

## 👤 Author

Developed by Gayan Dhanushka - CIS6003, Advanced Programming, 2026.
