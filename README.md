# 🦷 Sunrise Dental Clinic Management System

A comprehensive, automated software solution designed to streamline the daily operations of a modern dental facility, replacing traditional paper-based record-keeping.

> Developed as part of the **CIS6003 – Advanced Programming** assessment (Cardiff Met / ICBT Campus).

---

## 📖 Overview

Sunrise Dental Clinic is a busy private dental center that previously managed patient appointments and treatment records manually. This system digitizes that entire workflow — from staff login, to appointment scheduling, to billing — while preventing common issues like double bookings and lost records.

---

## 🚀 Key Features

- **Secure Authentication** — Staff login protected with salted SHA-256 password hashing (no plain-text passwords stored).
- **Appointment Management** — Real-time scheduling with database-level double-booking prevention (MySQL trigger).
- **Automated Billing** — Dynamic total cost calculation and automated PDF receipt generation using iTextPDF.
- **Notifications** — Background, multithreaded email alerts for appointment confirmations, built using the Factory design pattern.
- **Data Management** — Complete patient and dentist history tracking, with search by appointment number or patient name.
- **Help Section** — Built-in step-by-step guidance for new staff members.

---

## 🛠️ Technologies & Architecture

| Category | Details |
|---|---|
| **Language** | Java |
| **Database** | MySQL (relational schema with triggers & stored procedures) |
| **IDE** | Apache NetBeans |
| **Architecture** | 3-Tier (Presentation → Business Logic → Data Access) |
| **Design Patterns** | MVC, DAO, Singleton, Factory Method |
| **PDF Generation** | iTextPDF |
| **Email Service** | Jakarta Mail (SMTP) |
| **CI/CD** | Automated build & test pipeline via GitHub Actions (Ant) |
| **Testing** | JUnit 5 |

### Architecture Diagram

```
┌─────────────────────┐
│   Presentation      │   Java Swing (Login, Dashboard, Forms)
│   (view package)    │
└──────────┬──────────┘
           │
┌──────────▼──────────┐
│   Business Logic     │   Controllers & Services
│ (controller package) │   (Authentication, Appointment, Billing, Notification)
└──────────┬──────────┘
           │
┌──────────▼──────────┐
│   Data Access Layer  │   DAO classes (Singleton DB connection)
│   (dao package)      │
└──────────┬──────────┘
           │
┌──────────▼──────────┐
│   MySQL Database      │
└──────────────────────┘
```

---

## 📂 Project Structure

```
SunriseDentalClinic/
├── src/
│   ├── model/          # Entity classes (Patient, Dentist, Staff, Appointment, Bill...)
│   ├── dao/            # Data Access Objects (SQL queries, isolated from UI)
│   ├── controller/      # Business logic & services (Auth, Appointment, Notification)
│   ├── view/            # Swing UI forms (Login, Dashboard, Register, Search, Bill...)
│   ├── util/            # DBConnection (Singleton), PasswordUtil, ValidationUtil
│   └── db/              # Database schema & seed data (SQL script)
├── test/                # JUnit test classes
└── README.md
```

---

## ⚙️ Setup & Installation

1. **Clone the repository**
   ```
   git clone <repository-url>
   ```

2. **Database setup** — Import the `sunrise_dental_clinic.sql` file (located in the `db` folder) into your MySQL server via phpMyAdmin/WAMP to create the schema and seed data.

3. **Configure credentials** — Copy `config.properties.example` (in the project root) to `config.properties` and fill in your own database and email credentials. This file is git-ignored and never committed, so your credentials stay private.
   ```properties
   db.url=jdbc:mysql://localhost:3306/sunrise_dental_clinic
   db.user=root
   db.password=

   email.sender=your_email@gmail.com
   email.app.password=your_16_char_app_password
   ```

4. **Open project** — Open the `SunriseDentalClinic` directory using Apache NetBeans IDE.

5. **Build & Run** — Clean and Build the project, then run `LoginUI.java` to start the application.

   **Default staff login** — Username: `staff1` / Password: `s123`

---

## 🔒 Security Notes

- Passwords are never stored or compared in plain text — a salted SHA-256 hash (`util.PasswordUtil`) is used for all staff credentials.
- Database and email credentials are kept out of source control via `config.properties` (see setup step 3) — no secrets are hardcoded in the repository.
- All SQL queries use `PreparedStatement` to prevent SQL injection.

---

## ✅ Testing

JUnit test classes cover authentication, appointment logic, and billing calculations. Run them directly from NetBeans (`Test Packages` → right-click → **Run**), or via the GitHub Actions CI pipeline, which runs automatically on every push.

---

## 📸 Screenshots

<img width="584" height="358" alt="image" src="https://github.com/user-attachments/assets/ef4b863d-9398-4924-ab73-7d18a21b345c" />
<img width="675" height="834" alt="image" src="https://github.com/user-attachments/assets/858e1cdc-466a-4990-9108-efbeb4476f66" />
<img width="1065" height="587" alt="image" src="https://github.com/user-attachments/assets/36d72faf-4d09-4a61-93fa-e0545d053265" />
<img width="500" height="544" alt="image" src="https://github.com/user-attachments/assets/0e096e41-d947-4ccb-bb97-55d80b595a07" />
<img width="854" height="632" alt="Screenshot 2026-09-05 140113" src="https://github.com/user-attachments/assets/4aa35df9-85fb-4ddc-bae0-31edf8d58375" />
<img width="729" height="740" alt="image" src="https://github.com/user-attachments/assets/78746893-16df-48b0-89c5-a80cae39e3fe" />
<img width="856" height="928" alt="image" src="https://github.com/user-attachments/assets/cc26ee96-fea5-48e6-86b3-93aad3a8fc01" />
<img width="689" height="995" alt="image" src="https://github.com/user-attachments/assets/94867b05-0fd7-4c0a-a3d1-8180220c83a6" />

---

## 📄 License

This project was developed for academic purposes as part of the CIS6003 Advanced Programming module and is not licensed for commercial use.

---

## 👤 Author

Developed by Gayan Dhanushka — CIS6003, Advanced Programming, 2026.
