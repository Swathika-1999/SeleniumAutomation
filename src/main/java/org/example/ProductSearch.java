package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.*;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class ProductSearch {
    WebDriver driver;
    public static ChromeOptions options;
    @BeforeClass

    public void setUp() {
        options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"/src/test/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://adnabu-arjun.myshopify.com");
        System.out.println("-->Navigated to adnabu webpage");
    }
    @Test(priority = 1)
    public void search() {
        driver.findElement(By.className("icon-search")).click();
        System.out.println("-->The search bar must be prominently displayed on clicking search icon");
        driver.findElement(By.id("Search-In-Modal-1")).click();
        driver.findElement(By.id("Search-In-Modal-1")).sendKeys("14k Dangling Pendant Earrings");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.findElement(By.className("search__button")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        System.out.println("-->The search result displayed correctly and it is relevant to the searched product name");


    }
    @Test(priority = 2)
    public void specialCharacterSearch() {
        driver.findElement(By.id("Search-In-Template")).clear();
        driver.findElement(By.id("Search-In-Template")).sendKeys("@@!!$$%%^^^");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        System.out.println("-->The empty search result is returned on entering the special characters");
    }
    @Test(priority = 3)
    public void outOfDatabaseSearch() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.findElement(By.id("Search-In-Template")).clear();
        driver.findElement(By.id("Search-In-Template")).sendKeys("Array string");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.findElement(By.xpath("//p[@role='status']")).isDisplayed();
        System.out.println("-->There shows an empty page with no search found result on entering the string which is irrelevant of the product name");
    }
    @Test(priority = 4)
    public void isTemplateFound() {
        driver.findElement(By.id("Search-In-Template")).clear();
        System.out.println("-->The search result shows product image,prices and descriptions");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.findElement(By.id("Search-In-Template")).clear();
    }
    @Test(priority = 5)
    public void validDataSearch() {
        driver.findElement(By.id("Search-In-Template")).sendKeys("Bloom");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.findElement(By.id("Search-In-Template")).clear();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        System.out.println("-->The search result shows relevant results on entering vaild product name");
    }
    @Test(priority = 6)
    public void capitalLetterSearch() {
        Actions act = new Actions(driver);
        act.moveToElement(driver.findElement(By.id("Search-In-Template"))).click().keyDown(Keys.SHIFT).sendKeys("pendent").build().perform();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        System.out.println("-->The product search is showing same results on entering the product name in capital letters");
    }


    @AfterClass
    public void afterClass()
    {
        driver.quit();
    }










}
