# 🩺 Smart Medical Analyzer

A modern, medical-themed desktop application built with **Java Swing** and **MS Access**. It allows users to sign up, log in, and analyze test results for **Liver**, **Heart**, and **Kidney**.

---

## 💡 Features

- 🌐 Full-screen responsive UI with custom rounded borders and gradient buttons
- 🔐 Secure **Sign Up** & **Login** system with `BCrypt` password hashing
- 🧪 Organ-wise medical analyzers:
  - Liver Analyzer (AST/ALT, ALP/GGT, Bilirubin)
  - Heart Analyzer (LDL/HDL, Heart Rate/QT, Blood Pressure)
  - Kidney Analyzer (Creatinine/BUN, Urine Albumin, EGFR)
- 🔒 Password confirmation and duplicate username checks
- ✅ Clean navigation between forms
- 📦 Organized using OOP principles (`Analyzable` interface, abstract `BodyPart` class, etc.)

---

## 🛠 Technologies

- Java Swing
- MS Access (`.accdb`) database
- JDBC with **UCanAccess** driver
- `jBCrypt` for password hashing

---

## 🚀 Getting Started

### ✅ Prerequisites

- Java JDK 8 or later
- [UCanAccess JDBC Driver](https://ucanaccess.sourceforge.net/site.html)
- MS Access installed (or at least `.accdb` support)
- Optional: NetBeans or IntelliJ IDE

---

### 📂 Project Setup

1. **Clone the repo**:
   ```bash
   git clone https://github.com/NazarAbbas12/SmartMedicalAnalyzer.git
   cd SmartMedicalAnalyzer
 

Download and add UCanAccess libraries:

Add these .jar files to your project build path:

ucanaccess-<version>.jar

jackcess-<version>.jar

commons-lang3-<version>.jar

commons-logging-<version>.jar

hsqldb-<version>.jar

Configure the database path
The application uses a hardcoded path to the MS Access file:

java
Copy
Edit
String dbPath = "C:/Users/HAROON TRADERS/OneDrive/Documents/MedicalDatabase.accdb";
⚠️ If you're running on another system:
Place your .accdb file in a known location.

Update the path in all relevant files (e.g., SignUp.java, Login.java) like this:

java
Copy
Edit
String dbPath = "your/path/to/MedicalDatabase.accdb";
Run the application

Compile and run SignUp.java or Login.java.

🗂 Folder Structure
arduino
Copy
Edit
medicalreportanalyst/
├── SignUp.java
├── Login.java
├── LiverAnalyzerUI.java
├── HeartAnalyzerUI.java
├── KidneyAnalyzerUI.java
├── Organ analyzer test files...
└── MedicalDatabase.accdb   <-- Make sure this exists or change the path!
🔒 Security Note
All passwords are hashed using BCrypt before being saved to the database.

No plain-text passwords are stored.

📢 Author
Nazar Abbas
Software Engineering, Bahria University
📍 Pakistan

📌 License
This project is open-source and free to use for learning and academic purposes.

yaml
Copy
Edit
