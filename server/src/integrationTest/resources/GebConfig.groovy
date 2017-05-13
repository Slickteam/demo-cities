/*
	This is the Geb configuration file.
	
	See: http://www.gebish.org/manual/current/#configuration
*/


import org.openqa.selenium.chrome.ChromeDriver


waiting {
    timeout = 2
}

environments {

    // run via “./gradlew chromeTest”
    // See: http://code.google.com/p/selenium/wiki/ChromeDriver
    chrome {
        driver = { new ChromeDriver() } //"chrome"
    }

}

// To run the tests with all browsers just run “./gradlew test”

baseUrl = "http://localhost:8080"
reportsDir = new File("build/runtime_reports_dir")