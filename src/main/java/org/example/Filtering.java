package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Filtering {
    WebDriver driver;
    public static ChromeOptions options;

    @BeforeClass
    public void setUp() {
        options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/src/test/resources/chromedriver.exe");

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        //Testcase 1
        driver.get("https://adnabu-arjun.myshopify.com");
    }

    @Test(priority = 1)
    public void search() {
        driver.findElement(By.className("icon-search")).click();
        System.out.println("-->The search bar must be prominently displayed on clicking search icon");
        //Testcase 2
        driver.findElement(By.id("Search-In-Modal-1")).click();
        driver.findElement(By.id("Search-In-Modal-1")).sendKeys("14k");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.findElement(By.className("search__button")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        System.out.println("-->The search result displayed correctly and it is relevant to the searched product name");
    }
   @Test(priority = 2)
    public void stockFilter() {
        driver.findElement(By.xpath("//*[@id=\"Details-1-template--14768207462497__main\"]/summary/div")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.findElement(By.xpath("//*[@id=\"Facet-1-template--14768207462497__main\"]/fieldset/ul[1]/li[1]")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.findElement(By.xpath("//*[@id=\"Details-1-template--14768207462497__main\"]/summary")).click();
        System.out.println("-->The items which are in stock is displaying here");
        driver.findElement(By.xpath("//*[@id=\"FacetFiltersForm\"]/div[2]/facet-remove[1]/a/span")).isDisplayed();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.findElement(By.xpath("//*[@id=\"FacetFiltersForm\"]/div[2]/facet-remove[2]/a")).click();
       System.out.println("-->Filter removed and all the items related to the search are displaying");
    }
    @Test(priority = 3)
    public void outOfStockFilter() {
        driver.findElement(By.xpath("//*[@id=\"Details-1-template--14768207462497__main\"]/summary/div")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.findElement(By.xpath("//*[@id=\"Facet-1-template--14768207462497__main\"]/fieldset/ul[1]/li[2]")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.findElement(By.xpath("//*[@id=\"Details-1-template--14768207462497__main\"]/summary")).click();
        System.out.println("-->The items which are in out of stock is displaying here");
        driver.findElement(By.xpath("//*[@id=\"FacetFiltersForm\"]/div[2]/facet-remove[1]/a/span")).isDisplayed();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.findElement(By.xpath("//*[@id=\"FacetFiltersForm\"]/div[2]/facet-remove[2]/a")).click();
        System.out.println("-->Filter removed and all the items related to the search are displaying");
    }
    @AfterClass
    public void afterClass()
    {
        driver.quit();
    }

}
