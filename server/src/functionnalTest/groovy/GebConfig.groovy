
/*
	This is the Geb configuration file.
	
	See: http://www.gebish.org/manual/current/#configuration
*/


import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.firefox.FirefoxDriver

waiting {
    timeout = 2
}


environments {

    // run via “./gradlew chromeTest”
    // See: http://code.google.com/p/selenium/wiki/ChromeDriver
    chrome {

        driver = { new ChromeDriver() } //"chrome"
    }

    // run via “./gradlew firefoxTest”
    // See: http://code.google.com/p/selenium/wiki/FirefoxDriver
    firefox {
        println("%%%%%%%%%%%%%%%%%")
        driver = { new FirefoxDriver() }
    }

}

// To run the tests with all browsers just run “./gradlew test”

baseUrl = "http://localhost:8080"
reportsDir = new File("build/runtime_reports_dir")