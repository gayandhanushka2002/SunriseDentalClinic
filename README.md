# Sunrise Dental Clinic Management System 🦷

Welcome to the repository of the Sunrise Dental Clinic Management System. This is a comprehensive, automated software solution designed to streamline the daily operations of a modern dental facility, replacing traditional paper-based record-keeping.

## 🚀 Key Features
* **Secure Authentication:** Protected staff login system utilizing secure Password Hashing.
* **Appointment Management:** Real-time scheduling with database-level double-booking prevention.
* **Automated Billing:** Dynamic total cost calculation and automated PDF receipt generation using iTextPDF.
* **Notifications:** Background multithreaded Email and SMS patient alerts using the Factory Design Pattern.
* **Data Management:** Complete patient and dentist history tracking.

## 🛠️ Technologies & Architecture
* **Language:** Java 
* **Database:** MySQL (Relational Database with Triggers & Stored Procedures)
* **IDE:** Apache NetBeans
* **Architecture:** 3-Tier Architecture (Presentation, Business Logic, Data Access)
* **Design Patterns:** MVC, DAO, Singleton, and Factory Method
* **CI/CD:** Automated testing and build pipeline via GitHub Actions (Ant Build)

## ⚙️ Setup & Installation Instructions
1. **Clone the Repository:** Download or clone this repository to your local machine.
2. **Database Setup:** Import the `sunrise_dental_clinic.sql` file (located in the `db` folder) into your MySQL server to create the schema and initial data.
3. **Open Project:** Open the `SunriceDentalClinic` directory using Apache NetBeans IDE.
4. **Build & Run:** Clean and Build the project. Run `LoginUI.java` to start the application. 
*(Default Staff Login - Username: staff1 / Password: s123)*

---
*Developed as part of the Advanced Programming Assessment (CIS6003).*
