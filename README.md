

#  Technologies and Libraries Used


## Languages and Frameworks

This project using the following languages and frameworks:

* [Java 8](https://www.oracle.com/sg/java/technologies/javase/javase-jdk8-downloads.html) as the programming language
* [TestNG](https://testng.org/doc/) as the UnitTest framework to support the test creation
* [Selenium WebDriver](https://www.selenium.dev/) as the web browser automation framework using the Java binding
* [AssertJ](https://joel-costigliola.github.io/assertj/) as the fluent assertion library
* [Allure Report](https://docs.qameta.io/allure/) as the testing report strategy
* [JavaFaker](https://github.com/DiUS/java-faker) as the faker data generation strategy
* [Log4J2](https://logging.apache.org/log4j/2.x/) as the logging management strategy
* [WebDriverManager](https://github.com/bonigarcia/webdrivermanager) as the Selenium binaries management
* [Owner](http://owner.aeonbits.org/) to minimize the code to handle the properties file

-**Java**

-**Selenium**

-**TestNG**

-**Maven**

-**DataProvider** (Test Data for Testing)

#  Prerequisite
Java, Maven and Allure  should be installed on your machine.

**======Allure Setup======**

**On Windows**

For Windows, Allure is available from the **Scoop** commandline-installer.

To install Allure, download and install Scoop and then execute in the Powershell:

**scoop install allure**

**On MAC**

For Mas OS, automated installation is available via [Homebrew]([https://brew.sh/](https://brew.sh/))

**brew install allure**

**On Linux**
 
 For debian-based repositories a PPA is provided:
 
sudo apt-add-repository ppa:qameta/allure

sudo apt-get update 

sudo apt-get install allure

**Check the installation**

Execute allure --version in console to make sure that allure is now available:

$ allure --version
2.13.2

## About Framework:
This framework is built on Design Pattern: Page Object Model(**POM**) with Page Factory and **WebDriverFactory**.

**Browsers:** It Supports Chrome and Firefox Browser.

**OS:** Windows,Unix and MAC.

**Parallel Execution:** Yes you can run the Test cases in parallel using TestNG.xml.
(thread-count="2" and parallel="methods")

## WebDriver Factory:
 **DriverManagerFactory:** This class calls **ChromeDriverManager** based on the browser you are executing your test case.

>  **ChromeDriverManager**  extends the
> **DriverManager** class

>  **FirefoxDriverManager**  extends the
> **DriverManager** class

**DriverManager** This class is abstract class (ChromeDriverManager and FirefoxDriverManager classes extends Drivermanager Class)

## Important Classes ,Files and Folders:

**PageGenerator:** It returns new instance of the Page.

**Base Page:** This is Base Class for all the pages, it contains all basic functions required while Automating Pages.

**PropertyManager:**  **Singleton Pattern** used To Load Property file **configuration.properties** ( it contains Application URL and existing username and password)

**ListenerClass** This class implements ITestListener that is used for capturing the logs (status of the test case)

**Retry** Retry class implements IRetryAnalyzer

**RetryListenerClass** RetryListenerClass implements IAnnotationTransformer

Both these Retry and RetryListenerClass class helps in rerun the Failed,Broken Testcases 

**logs(folder)**: All logs are generated under logs(folder). Log files are named based on the TestCase Name)

**Drivers are loaded based on the OS i.e.  based on the tests are executing on Windows,Unix or MAC**

**Driver File(ChromeDriver and GeckoDriver) :** Present under path (src/test/java/resources/windows/chromedriver) based on the OS

**BaseTest:** This class takes care of invoking the test case, creating the drivers based on OS(Windows,Unix or MAC) ,Quiting the Browser after Test Execution and Taking Screenshots.

> To capture WebDriver Events we are using **WebEventListener** class which extends **BaseTest** and  implements **WebDriverEventListener**

## How to Run Test cases:


Go To **TestNG.xml** file present under path( src/test/TestNG.xml)

Make Sure you have configurations to run parallel Tests and the browsers mentioned in parameter as following:

>   suite thread-count="2" name="Suite" parallel="methods"
>   parameter name ="browser" value="CHROME"

**Steps To Run the Test cases and generate Execution Report:**

Mention the browser on which you want to run TestCases in TestNG.xml

**For Chrome**

**MUST NEEDED STEP-->** **Mention this in TestNG.xml-->"parameter name="browser" value="CHROME"/>"**

Go To the Project location under command line or terminal:

**Below command is used to run tests from CommandLine**
>mvn clean test -Dbrowser=Chrome

**For Firefox**

**MUST NEEDED STEP-->** **Mention this in TestNG.xml-->"parameter name="browser" value="FIREFOX"/>"**

Go To the Project location under command line or terminal:

**Below command is used to run tests from CommandLine**
>mvn clean test -Dbrowser=Firefox

> Wait for Tests to Execute till you  see something like
> Results:
[INFO]
[INFO] Tests run: 6, Failures: 0, Errors: 0, Skipped: 0

***Now Test cases have run successfully lets generate report***

**Run below command to Generate Reports using Allure**

allure serve allure-results

Wait till you see till you see something like

> Generating report to temp directory...
Report successfully generated to C:\Users\T460\AppData\Local\Temp\3580264047973062799\allure-report
Starting web server...

Now a new browser instance should be invoked and you should see Test Execution Reports
