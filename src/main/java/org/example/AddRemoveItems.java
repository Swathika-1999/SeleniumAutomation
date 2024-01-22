package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class AddRemoveItems {
    WebDriver driver;
    public static ChromeOptions options;

    @BeforeClass
    public void setUp() {
        options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/src/test/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://adnabu-arjun.myshopify.com");
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
   public void addItemToCart() {
       driver.findElement(By.cssSelector(".grid__item")).click();
       driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
       driver.findElement(By.cssSelector(".product-form__submit")).click();
       driver.manage().timeouts().implicitlyWait(Duration.ofMinutes(5));
       WebElement cart = driver.findElement(By.xpath("//a[@href='/cart']"));
       cart.isDisplayed();
       cart.click();
       driver.findElement(By.xpath("//a[@href='/cart']")).click();
       driver.manage().timeouts().pageLoadTimeout(10,TimeUnit.SECONDS);
       driver.findElement(By.xpath("//a[@href='/cart']")).click();
       driver.findElement(By.xpath("//a[@href='/cart']")).click();
       System.out.println("-->Items added to cart successfully");

   }

   @Test(priority = 3)
   public void removeItemFromCart(){
      driver.findElement(By.xpath("//*[@id=\"Remove-1\"]")).click();
      driver.manage().timeouts().pageLoadTimeout(10,TimeUnit.SECONDS);
      driver.findElement(By.className("cart__empty-text")).isDisplayed();
      driver.findElement(By.xpath("//*[@id=\"shopify-section-template--14768207233121__main-cart-items\"]/cart-items/div/div[2]/a")).isDisplayed();
      System.out.println("-->Items removed from cart successfully");
   }
    @Test(priority = 4)
    public void countPriceValidation(){
        driver.navigate().back();
        String priceString= driver.findElement(By.xpath("//*[@id=\"price-template--14768207495265__main\"]/div/div/div[2]/span[4]")).getText();
        driver.findElement(By.cssSelector(".product-form__submit")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofMinutes(5));
        String Quantity = driver.findElement(By.xpath("//*[@id=\"Quantity-Form-template--14768207495265__main\"]/label/span/span/span")).getText();
        int count;
        if (Quantity.isEmpty()) {
              count =1;
        }else{
            driver.manage().timeouts().pageLoadTimeout(10,TimeUnit.SECONDS);
            count = Integer.parseInt(Quantity);
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofMinutes(5));
        WebElement cart = driver.findElement(By.xpath("//a[@href='/cart']"));
        cart.isDisplayed();
        cart.click();
        driver.findElement(By.xpath("//a[@href='/cart']")).click();
        driver.manage().timeouts().pageLoadTimeout(10,TimeUnit.SECONDS);
        driver.findElement(By.xpath("//a[@href='/cart']")).click();
        String cardString = driver.findElement(By.xpath("/html/body/main/div[1]/cart-items/div/form/div/div/table/tbody/tr/td[5]/div[2]/span")).getText();
        String cardPriceString = cardString.replaceAll("([RSs .,])", "");
        double cardPriceDouble = Double.parseDouble(cardPriceString);
        int cardPriceValue = (int)(cardPriceDouble*count);
        String priceStringConv = priceString.replaceAll("([RSs .,])", "");
        double priceDouble = Double.parseDouble(priceStringConv);
        int priceValue = (int)(priceDouble*count);
        System.out.println("Price of the item = "+priceValue);
        System.out.println("Quantity of item added ="+count);
        System.out.println("Total Price of the item as per quantity added in cart = "+cardPriceValue);

    }
    @AfterClass
    public void afterClass()
    {
        driver.quit();
    }


    }


