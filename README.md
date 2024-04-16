# Hexagon Challenge

## Architecture

### Overview
The project architecture follows the MVVM + Clean pattern. It is divided into four main layers:

1. **Data Layer:** Responsible for database connections, repositories, and handling local and remote entities.
2. **Dependency Injection (DI):** Manages dependency injection using Hilt.
3. **Domain Layer:** Handles business logic and use cases, making business rules reusable.
4. **UI Layer:** Manages components, view models, navigation, UI states, and events.

### Technologies Used
The project utilizes Kotlin, Jetpack Compose, Realm, Coil, Hilt, and Version Catalogs.

## Features

### Upsert Employee
Allows inserting or updating an employee via a form page. The employee object includes name, city, birth date, document, profile picture URI, and situation.

### Delete Employee
Deletes an employee from the local database. This can be done through the "X" symbol in the employee list or the "Exclude" button in the employee details.

### List Employee
Displays a list of employees with multiple options accessible via buttons on the employee cards.

### Filter Employee
Filters the employee list by name, observing the query typed in the employee list screen.

### Activate/Deactivate Employee
Changes the "active" property of an employee using the edit button or the activate/deactivate button, depending on the current situation.

### Authentication via Device Credentials
Prompts the user to authenticate using available device methods.

## Summary of Libraries Used

1. **Dagger Hilt:** Dependency injection library for managing dependencies.
2. **Jetpack Compose:** Modern UI toolkit for building native Android UI.
3. **Coil:** Image loading library for displaying images efficiently.
4. **Realm:** Mobile database for managing local data storage.
5. **Android Material:** Provides components and styles for a consistent Android UI.
6. **Compose Material 3:** Material Design components designed for Jetpack Compose.

## Conclusion
The project effectively implements the MVVM + Clean architecture, providing a clear separation of concerns and facilitating maintainability and scalability. By leveraging modern technologies like Jetpack Compose and Hilt, the application delivers a smooth user experience with efficient UI rendering and dependency management. The features such as upserting, deleting, listing, filtering, and managing employee activation status offer comprehensive functionality for managing employee data. Integration of authentication via device credentials enhances security and user trust.
