package Avic;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class AvicSmokeTests {

    private WebDriver driver;

    @BeforeTest
    public void profileSetup() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver");
    }

    @BeforeMethod
    public void testsSetup() {

        driver = new ChromeDriver();

        driver.manage().window().maximize();

        driver.get("https://avic.ua/");

    }

    @Test(priority = 1)
    public void checkUrlContainsSearchWord(){
        // Находим элемент для ввода
        WebElement inputSearchField = driver.findElement(By.xpath("//input[@id='input_search']"));
        inputSearchField.sendKeys("iPhone 13 Pro"); // отправляем данные ввода
        // Находим элемент - кнопку поиска
        WebElement submitButton = driver.findElement(By.xpath("//button[@class='button-reset search-btn']"));
        submitButton.click(); // нажимаем на эту кнопку

        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS); // Ожидание на 30 секунд

        assertTrue(driver.getCurrentUrl().contains("query=iPhone")); // проверяем что урла содержит фразу iPhone

    }

    @Test(priority = 2)
    public void checkAmountsOfElementsAfterSearch(){
        // Находим элемент для ввода
        WebElement inputSearchField = driver.findElement(By.xpath("//input[@id='input_search']"));
        inputSearchField.sendKeys("iPhone 13 Pro"); // отправляем данные ввода
        // Находим элемент - кнопку поиска
        WebElement submitButton = driver.findElement(By.xpath("//button[@class='button-reset search-btn']"));
        submitButton.click(); // нажимаем на эту кнопку

        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS); // Ожидание на 30 секунд
        // Находим список элементов
        List<WebElement> elementList = driver.findElements(By.xpath("//div[@class='prod-cart__descr']"));

//        assertEquals(elementList.size(), 12); // проверяем что этих элементов 12

        for (WebElement webElement : elementList){

            assertTrue(webElement.getText().contains("iPhone 13 Pro"));

        } // проверяем что в каждом элементе списка есть фраза iPhone 13 Pro

    }

    @AfterMethod
    public void tearDown(){

        driver.close();

    }
}
