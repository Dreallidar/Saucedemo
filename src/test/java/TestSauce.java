import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;
import java.util.Random;


import static org.testng.Assert.assertTrue;
import static org.testng.AssertJUnit.assertEquals;

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

    public WebElement snoozeUntilElement(By by) {
        WebDriverWait wait = new WebDriverWait(_globalDriver, Duration.ofSeconds(10));

        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(by));
        return element;
    }

    public WebElement snoozeUntilClickable(By by) {
        WebElement element = snoozeUntilElement(by);
        WebDriverWait wait = new WebDriverWait(_globalDriver, Duration.ofSeconds(10));

        element = wait.until(ExpectedConditions.elementToBeClickable(by));
        return element;
    }


    public boolean isElementPresent(WebDriver driver, By by) {
        try {
            WebElement element = driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }


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
    public void loginCartCheckoutTest() {
        _globalDriver.findElement(By.id("user-name")).sendKeys("standard_user");
        _globalDriver.findElement(By.id("login-button")).click();
        snoozeUntilElement(By.id("add-to-cart-sauce-labs-backpack")).click();
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
        snoozeUntilElement(By.id("first-name")).sendKeys(generateUsername());
        _globalDriver.findElement(By.id("last-name")).sendKeys(generateUsername());
        _globalDriver.findElement(By.id("postal-code")).sendKeys(generateUsername());
        _globalDriver.findElement(By.id("continue")).click();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        _globalDriver.findElement(By.id("finish")).click();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        _globalDriver.findElement(By.id("back-to-products")).click();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        _globalDriver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
        _globalDriver.findElement(By.id("add-to-cart-sauce-labs-bike-light")).click();
        _globalDriver.findElement(By.id("remove-sauce-labs-backpack")).click();
        _globalDriver.findElement(By.xpath("/html/body/div/div/div/div[1]/div[1]/div[3]/a/span")).click();
        _globalDriver.findElement(By.id("checkout")).click();
        _globalDriver.findElement(By.id("cancel")).click();
        _globalDriver.findElement(By.id("continue-shopping")).click();
        String kaina = _globalDriver.findElement(By.xpath("/html/body/div/div/div/div[2]/div/div/div/div[2]/div[2]/div[2]/div")).getText();
        _globalDriver.findElement(By.xpath("/html/body/div/div/div/div[1]/div[1]/div[3]/a/span")).click();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        _globalDriver.findElement(By.id("checkout")).click();
        snoozeUntilElement(By.id("first-name")).sendKeys(generateUsername());
        _globalDriver.findElement(By.id("last-name")).sendKeys(generateUsername());
        _globalDriver.findElement(By.id("postal-code")).sendKeys(generateUsername());
        _globalDriver.findElement(By.id("continue")).click();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        String kainaEnd = _globalDriver.findElement(By.xpath("/html/body/div/div/div/div[2]/div/div[2]/div[6]")).getText();
        Assert.assertEquals(kainaEnd, "Item total: " + kaina);
        _globalDriver.findElement(By.id("cancel")).click();
        _globalDriver.findElement(By.id("remove-sauce-labs-bike-light")).click();
        WebElement xLink = _globalDriver.findElement(By.xpath("/html/body/div/div/footer/ul/li[1]/a"));
        Assert.assertEquals(xLink.getText(), "Twitter");
        Assert.assertEquals(xLink.getAttribute("href"), "https://twitter.com/saucelabs");
        _globalDriver.findElement(By.xpath("/html/body/div/div/div/div[1]/div[2]/div/span/select")).click();
        _globalDriver.findElement(By.xpath("/html/body/div/div/div/div[1]/div[2]/div/span/select/option[3]")).click();

        snoozeUntilElement(By.xpath("/html/body/div/div/div/div[1]/div[1]/div[3]/a")).click();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        _globalDriver.findElement(By.id("checkout")).click();
        snoozeUntilElement(By.id("first-name")).sendKeys(generateUsername());
        _globalDriver.findElement(By.id("last-name")).sendKeys(generateUsername());
        _globalDriver.findElement(By.id("postal-code")).sendKeys(generateUsername());
        _globalDriver.findElement(By.id("continue")).click();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        _globalDriver.findElement(By.id("finish")).click();
        _globalDriver.quit();


    }

    @Test
    public void logincarttestProblem() {
        _globalDriver.findElement(By.id("user-name")).sendKeys("problem_user");
        _globalDriver.findElement(By.id("login-button")).click();
        snoozeUntilElement(By.id("add-to-cart-sauce-labs-backpack")).click();
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
        snoozeUntilElement(By.id("first-name")).sendKeys(generateUsername());
        _globalDriver.findElement(By.id("last-name")).sendKeys(generateUsername());
        _globalDriver.findElement(By.id("postal-code")).sendKeys(generateUsername());
        _globalDriver.findElement(By.id("continue")).click();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Boolean arYraFinish = isElementPresent(_globalDriver, By.id("finish"));
        Assert.assertEquals(arYraFinish, false);

    }

    @Test
    public void loginAddAllToCart() {
        _globalDriver.findElement(By.id("user-name")).sendKeys("problem_user");
        _globalDriver.findElement(By.id("login-button")).click();
        List<WebElement> items = _globalDriver.findElement(By.className("inventory_list")).findElements(By.className("inventory_item_name"));
        for (WebElement item :items) {
            System.out.println(item.getText());

        }
    }



}
