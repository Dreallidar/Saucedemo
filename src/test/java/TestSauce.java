import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.Random;

public class TestSauce {
    public String generateUsername() {
        String[] characters = {"abcdefghijklmnopqrstuvwxyz", "0123456789"};

        Random random = new Random();

        StringBuilder username = new StringBuilder();

        // Generate username part
        int usernameLength = random.nextInt(10) + 5; // Random length between 5 to 14 characters
        for (int i = 0; i < usernameLength; i++) {
            String characterSet = characters[random.nextInt(2)]; // Selecting either alphabets or numbers
            char randomChar = characterSet.charAt(random.nextInt(characterSet.length()));
            username.append(randomChar);

        }
        return username.toString();
    }
    WebDriver _globalDriver;


    @BeforeTest
    public void SetupWebDriver() {
    _globalDriver = new ChromeDriver();
    _globalDriver.get("https://www.saucedemo.com");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        _globalDriver.findElement(By.id("password")).sendKeys("secret_sauce");

    }

    @Test
    public void loginCartCheckoutTest(){
        _globalDriver.findElement(By.id("user-name")).sendKeys("standard_user");
        _globalDriver.findElement(By.id("login-button")).click();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        _globalDriver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
        _globalDriver.findElement(By.id("add-to-cart-sauce-labs-bike-light")).click();
        _globalDriver.findElement(By.id("add-to-cart-sauce-labs-bolt-t-shirt")).click();
        _globalDriver.findElement(By.id("add-to-cart-sauce-labs-fleece-jacket")).click();
        _globalDriver.findElement(By.id("add-to-cart-sauce-labs-onesie")).click();
        _globalDriver.findElement(By.id("add-to-cart-test.allthethings()-t-shirt-(red)")).click();
        _globalDriver.findElement(By.xpath("/html/body/div/div/div/div[1]/div[1]/div[3]/a/span")).click();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        _globalDriver.findElement(By.id("checkout")).click();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        _globalDriver.findElement(By.id("first-name")).sendKeys(generateUsername());
        _globalDriver.findElement(By.id("last-name")).sendKeys(generateUsername());
        _globalDriver.findElement(By.id("postal-code")).sendKeys(generateUsername());







    }

}
