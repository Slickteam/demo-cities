
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

    // run via “./gradlew chromeFuncTest”
    // See: http://code.google.com/p/selenium/wiki/ChromeDriver
    chrome {

        driver = { new ChromeDriver() } //"chrome"
    }

    // run via “./gradlew firefoxFuncTest”
    // See: http://code.google.com/p/selenium/wiki/FirefoxDriver
    firefox {
        driver = { new FirefoxDriver() }

        //don't know why but without that firefox test don't work :(
        atCheckWaiting = 1
    }

}

// To run the tests with all browsers just run “./gradlew functionalTests”

//baseUrl = "http://localhost:8080"
