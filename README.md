# HelloEyes_AndroidFramework
This project is an automation framework for testing the signup functionality of an Android app using Appium, Java, TestNG, and Maven. The framework is built using the Page Object Model (POM) design pattern, which helps to keep the test scripts clean and maintainable by separating the test logic from the page-specific actions and elements.
Key Components
Page Object Model (POM):
BaseClass: Contains common setup and teardown methods, including driver initialization and management.
SignUpPage: Represents the signup screen in the app. Contains locators and methods to interact with elements on this screen.
SignUpTest: Contains test methods that validate different aspects of the signup process, such as valid signup, invalid email format, weak password, and duplicate email.
Test Data Management:
JSON File: A testData.json file stores the data (e.g., email and password) used in the tests. The data is dynamically generated using Java Faker to ensure that each test run has unique input data.
Extent Reports:
Extent reports are used to generate detailed test execution reports, including screenshots of failures.
Driver Management:
UiAutomator2Options: This is used instead of DesiredCapabilities for driver setup, which includes options like resetting the app between tests to ensure a clean state.
