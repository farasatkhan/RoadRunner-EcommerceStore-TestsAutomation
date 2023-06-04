import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class RoadRunnerTests {

	public static void main(String[] args) {
		
        System.setProperty("webdriver.chrome.driver", "C:\\personal\\DevOps\\RoadRunner-EcommerceStore-TestsAutomation\\chromedriver_win32\\chromedriver.exe");
        // Enable headless mode
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");

        // Create an instance of ChromeDriver
        WebDriver driver = new ChromeDriver(options);
        
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(100, TimeUnit.SECONDS);
        
        testUserValidLogin(driver);
        testUserInvalidLogin(driver);
        
        testAdminValidLogin(driver);
        testAdminInvalidLogin(driver);
        
        testAddNewAdmin(driver);
        testBanAdminInValid(driver);
        
        testBanUserInValid(driver);
        testBanUserInValid(driver);

        driver.quit();
	}
	
	public static void testUserValidLogin(WebDriver driver) {
        driver.navigate().to("http://localhost:9000/login");

        WebElement emailField = driver.findElement(By.id("email"));
        WebElement passwordField = driver.findElement(By.id("password"));

        emailField.sendKeys("farasatkahan@gmail.com");
        passwordField.sendKeys("password1");

        WebElement loginButton = driver.findElement(By.id("Login"));
        loginButton.click();
        
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        String expectedUrl = "http://localhost:9000/items";
        String actualUrl = driver.getCurrentUrl();

        if (actualUrl.equals(expectedUrl)) {
            System.out.println("Valid User Login Test - Passed");
        } else {
            System.out.println("Valid User Login Test - Failed");
        }
        
        driver.navigate().to("http://localhost:9000/logout");
    }

    public static void testUserInvalidLogin(WebDriver driver) {
        driver.get("http://localhost:9000/login");

        WebElement emailField = driver.findElement(By.id("email"));
        WebElement passwordField = driver.findElement(By.id("password"));

        emailField.sendKeys("invalid@gmail.com");
        passwordField.sendKeys("invalid123");

        WebElement loginButton = driver.findElement(By.id("Login"));
        loginButton.click();
        
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        String expectedUrl = "http://localhost:9000/login";
        String actualUrl = driver.getCurrentUrl();

        if (actualUrl.equals(expectedUrl)) {
            System.out.println("Invalid User Login Test - Passed");
        } else {
            System.out.println("Invalid User Login Test - Failed");
        }
    }
    
    public static void testAdminValidLogin(WebDriver driver) {
        driver.navigate().to("http://localhost:9000/adminLogin");

        WebElement emailField = driver.findElement(By.id("email"));
        WebElement passwordField = driver.findElement(By.id("password"));

        emailField.sendKeys("admin@gmail.com");
        passwordField.sendKeys("password1");

        WebElement loginButton = driver.findElement(By.id("Login"));
        loginButton.click();
        
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        String expectedUrl = "http://localhost:9000/admin_dashboard";
        String actualUrl = driver.getCurrentUrl();

        if (actualUrl.equals(expectedUrl)) {
            System.out.println("Valid Admin Login Test - Passed");
        } else {
            System.out.println("Valid Admin Login Test - Failed");
        }
        
        driver.navigate().to("http://localhost:9000/logoutAdmin");
    }
    
    public static void testAdminInvalidLogin(WebDriver driver) {
        driver.get("http://localhost:9000/adminLogin");

        WebElement emailField = driver.findElement(By.id("email"));
        WebElement passwordField = driver.findElement(By.id("password"));

        emailField.sendKeys("invalid@gmail.com");
        passwordField.sendKeys("invalid123");

        WebElement loginButton = driver.findElement(By.id("Login"));
        loginButton.click();
        
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        String expectedUrl = "http://localhost:9000/adminLogin";
        String actualUrl = driver.getCurrentUrl();

        if (actualUrl.equals(expectedUrl)) {
            System.out.println("Invalid Admin Login Test - Passed");
        } else {
            System.out.println("Invalid Admin Login Test - Failed");
        }
    }
    
    public static void testAddNewAdmin(WebDriver driver) {
    	
    	driver.navigate().to("http://localhost:9000/adminLogin");

        WebElement emailField = driver.findElement(By.id("email"));
        WebElement passwordField = driver.findElement(By.id("password"));

        emailField.sendKeys("admin@gmail.com");
        passwordField.sendKeys("password1");

        WebElement loginButton = driver.findElement(By.id("Login"));
        loginButton.click();
        
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        
        
        driver.navigate().to("http://localhost:9000/admin_addAdmin");

        WebElement newAdminEmailField = driver.findElement(By.id("email"));
        WebElement newAdminPasswordField = driver.findElement(By.id("password"));

        newAdminEmailField.sendKeys("admin_new@gmail.com");
        newAdminPasswordField.sendKeys("password1");


        String expectedUrl = "http://localhost:9000/admin_addAdmin";
        String actualUrl = driver.getCurrentUrl();

        if (actualUrl.equals(expectedUrl)) {
            System.out.println("Add Admin Test - Passed");
        } else {
            System.out.println("Add Admin Test - Failed");
        }
        
        
        driver.navigate().to("http://localhost:9000/logoutAdmin");
    }
    
    public static void testBanAdminInValid(WebDriver driver) {
    	
    	driver.navigate().to("http://localhost:9000/adminLogin");

        WebElement emailField = driver.findElement(By.id("email"));
        WebElement passwordField = driver.findElement(By.id("password"));

        emailField.sendKeys("admin@gmail.com");
        passwordField.sendKeys("password1");

        WebElement loginButton = driver.findElement(By.id("Login"));
        loginButton.click();
        
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        
        
        driver.navigate().to("http://localhost:9000/admin_banAdmin");

        WebElement newAdminEmailField = driver.findElement(By.id("email"));

        newAdminEmailField.sendKeys("invalidAdmin@gmail.com");
        
        WebElement BanAdminButton = driver.findElement(By.id("Login"));
        BanAdminButton.click();

        String ErrorTest = "ERROR: User not found";
        
        WebElement errorElement = driver.findElement(By.xpath("//p[contains(text(), '" + ErrorTest + "')]"));

        if (errorElement != null) {
            System.out.println("Ban Invalid Admin Test - Passed");
        } else {
            System.out.println("Ban Invalid Admin Test - Failed");
        }
        
        
        driver.navigate().to("http://localhost:9000/logoutAdmin");
    }
    
    public static void testBanUserInValid(WebDriver driver) {
    	
    	driver.navigate().to("http://localhost:9000/adminLogin");

        WebElement emailField = driver.findElement(By.id("email"));
        WebElement passwordField = driver.findElement(By.id("password"));

        emailField.sendKeys("admin@gmail.com");
        passwordField.sendKeys("password1");

        WebElement loginButton = driver.findElement(By.id("Login"));
        loginButton.click();
        
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        
        
        driver.navigate().to("http://localhost:9000/admin_banUser");

        WebElement newAdminEmailField = driver.findElement(By.id("email"));

        newAdminEmailField.sendKeys("invalidUser@gmail.com");
        
        WebElement BanAdminButton = driver.findElement(By.id("Login"));
        BanAdminButton.click();

        String ErrorTest = "ERROR: User not found";
        
        WebElement errorElement = driver.findElement(By.xpath("//p[contains(text(), '" + ErrorTest + "')]"));

        if (errorElement != null) {
            System.out.println("Ban Invalid User Test - Passed");
        } else {
            System.out.println("Ban Invalid User Test - Failed");
        }
        
        
        driver.navigate().to("http://localhost:9000/logoutAdmin");
    }


}
