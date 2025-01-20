# Hospital Management System (Gestion Hospitalisation)

## Project Overview
A Java-based hospital management system that handles patient records and medical activities. The system provides interfaces for managing patient information and medical procedures.

## Project Structure
```
GestionHospitalisation/
├── src/
│   ├── database/
│   │   └── ConnexionDB.java         # Database connection handler
│   ├── modele/
│   │   ├── ActeMedical.java         # Medical procedure model
│   │   └── Patient.java             # Patient model
│   └── ui/
│       ├── MenuPrincipal.java       # Main menu interface
│       ├── MiseAJourActeMedical.java # Medical procedure update interface
│       ├── MiseAJourPatient.java    # Patient information update interface
│       └── Main.java                # Application entry point
├── .gitignore
└── GestionHospitalisation.iml
```

## Technical Stack
- Java
- PostgreSQL 42.7.3
- OpenJDK 23.0.1

## Features
- **Patient Management**
  - Add new patients
  - Update patient information
  - View patient records
- **Medical Procedures Management**
  - Record medical procedures
  - Update procedure information
  - Track medical history

## Database Configuration
The system uses PostgreSQL as its database. Connection settings can be configured in the `ConnexionDB` class.

## Getting Started

### Prerequisites
- Java JDK 23 or higher
- PostgreSQL 42.7.3 or higher
- IDE (Project developed with IntelliJ IDEA)

### Installation
1. Clone the repository:
   ```bash
   git clone <repository_url>
   ```
2. Import the project into your IDE.
3. Configure PostgreSQL connection in `ConnexionDB.java`.
4. Build and run the project using the `Main` class.

### Running the Application
1. Execute the `Main.java` file.
2. Use the main menu interface to navigate through different functionalities.
3. Follow the on-screen instructions for managing patients and medical procedures.

## Development
- The project follows a Model-View architecture.
- Database operations are centralized in the `ConnexionDB` class.
- UI components are separated in the `ui` package.
- Business logic and data models are in the `modele` package.

## Contributing
1. Fork the repository.
2. Create your feature branch:
   ```bash
   git checkout -b feature/AmazingFeature
   ```
3. Commit your changes:
   ```bash
   git commit -m 'Add some AmazingFeature'
   ```
4. Push to the branch:
   ```bash
   git push origin feature/AmazingFeature
   ```
5. Open a Pull Request.

## License
This project is licensed under the MIT License - see the LICENSE file for details.
