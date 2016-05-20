# Simple Stock Market

This is a simple stock market application.

# Development guidelines/approach
* Write minimum amount of code to produce working software
* Unused/deprecated code not used. Commented code not left behind
* Test first approach (TDD)
* Spring Boot is used for bootstrapping the application
* Maven is used for building the application
* Class/method/variable names are self-explanatory so that comments are not needed to identify their purpose
* All the tests have meaningful names which describe what they are testing
* All the classes are packaged under a single package since this is a very small and simple implementation


# Running the application
* No UI provided
* No console input handling provided
* The application is runnable solely from its tests
* Clone the repository
* Go to the project root and execute the following 2 commands (in order)
 1. **mvn clean surefire-report:report**

 2. **mvn site -DgenerateReports=false**
* Go to the **target** -> **site** directory and open the **surefire-report.html** file to view the test results

