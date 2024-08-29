### Project Overview

This automation framework automates the testing of Hello Eyes android application. The framework includes multiple test scenarios, such as validating user sign-up
and sign-in functionalities with different input combinations.

#### Key Features:

- Modular Design: The framework follows the Page Object Model, ensuring clear separation between test logic and page actions.
- Data-Driven: The framework supports data-driven testing using JSON files to store and manage test data.
- Reporting: Extent Reports are integrated for generating test execution reports.

#### Tech Stack

- Programming Language: Java
- Automation Tool: Appium
- Testing Framework: TestNG
- Build Tool: Maven
- Design Pattern: Page Object Model (POM)
- Dependency Management: Maven
- Data Management: JSON
- Reporting: Extent Reports
- Device: Android Emulator
- Version Control: GitHub

#### Key Components

- BaseTest.java: Contains the setup and teardown methods, including driver initialization and configuration settings.
- pages: Contains all page classes with locators and actions for respective pages (SignUpPage and SignInPage).
- test: Contains test classes that hold the test methods and scenarios (SignUpTest and SignInTest).
- utils: Utility classes, such as TestListener for managing TestNG listeners and TestDataUtil for handling JSON data.
- pom.xml: Maven POM file with project dependencies and plugins.

#### Test Scenarios 

#### Sign-Up Test Scenarios:

1. Valid Sign-Up: Enter a valid email and password, and assert that the user is redirected to the User Account screen.
2. Invalid Email Format: Enter an incorrectly formatted email and assert that an appropriate error message is displayed.
3. Weak Password: Enter a password that does not meet the requirements and assert that an appropriate error message is displayed.
4. Duplicate Email: Attempt to sign up with an email that has already been registered and assert that an appropriate error message is displayed.

#### Sign-In Test Scenarios:

1. Valid Sign-In: Enter correct credentials and assert that the user is redirected to the User Account screen.
2. Invalid Email Format: Enter an incorrectly formatted email and assert that an appropriate error message is displayed.
3. Invalid Password: Enter an incorrect password and assert that a toast message indicating the failure is displayed.
4. Empty Email Field: Attempt to sign in with an empty email field while the password field is filled and assert that an appropriate error message is displayed.
5. Empty Password Field: Attempt to sign in with a filled email field and an empty password field and assert that an appropriate error message is displayed.
6. Empty Email and password Fields: Attempt to sign in with both the email and password fields empty and assert that an appropriate error message is displayed.
7. Unregistered Email: Attempt to sign in with an unregistered email and valid password and assert that an appropriate error message is displayed.
8. Case Sensitivity in Email: Attempt to sign in with a valid email but with different case sensitivity (e.g., "TestUser@example.com" instead of "testuser@example.com") and assert that the user is redirected to the User Account screen. 

#### Generating Reports

Extent Reports are generated automatically after test execution. The reports can be found in the test-output directory.