Prerequisites

-Eclipse IDE - get installation file from:
	https://www.eclipse.org/downloads/
	Then:
	1) Launch the installation file
	2) Choose folder 
	3) Install IDE
- EclEmma - Detailed instructions:
	https://www.eclemma.org/installation.html
	
	
Installing

-When you have the proper development environment(Eclipse IDE)
clone the repository and do a build by running the build program with no special arguments. 
	Step by step:
	1) Launch Eclipse IDE
	2) Choose directory where cloned project is
	3) Open project in IDE: File -> Open Projects from File Fystem -> directory
		Then choose project(ConsoleBankApp folder) and click open
	4) Build project: Project -> Build all
		
Running the tests

-To start application in command line mode use CommandLineTest.java as a start point:
	1) Double click on  src/test/java/net/yevstaf/UseCaseTest.java
	2) Then you need to run project: Run -> Run. On the top panel.
	3) Enter "help" in the console to see the available commands

-After installing EclEmma, open the package with modular tests: ConsoleBankApp / src / test / java / net / yevstaf / UseCaseTest.
Then right-click one of the tests in the folder -> Coverage As JUnitTest

Built With 
Maven - Dependency Management
