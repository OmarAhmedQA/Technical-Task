package Pages;

import Utilities.Utility;
import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import static org.testng.Assert.assertTrue;


public class Magento_Page
{
    private static WebDriver driver;

    public Magento_Page(WebDriver driver) {
        Magento_Page.driver = driver;
    }


    private final By CreatAccUpperBtn = By.xpath("//div[@class='panel header']//a[normalize-space()='Create an Account']");
    private final By FirstName = By.xpath("//input[@id='firstname']");
    private final By LastName = By.xpath("//input[@id='lastname']");
    private final By Email = By.xpath("//input[@id='email_address']");
    private final By Password = By.xpath("//input[@id='password']");
    private final By ConfPassword = By.xpath("//input[@id='password-confirmation']");
    private final By CreatAccLowerBtn = By.xpath("//button[@title='Create an Account']//span[contains(text(),'Create an Account')]");
    private static final By FirstItemHov = By.xpath("(//div[@aria-label='Color'])[1]");
    private static final By FirstItemAddComp = By.xpath("(//a[@title='Add to Compare'])[1]");
    private static final By SecItemHov = By.xpath("(//div[@aria-label='Color'])[2]");
    private static final By SecItemAddComp = By.xpath("(//a[@title='Add to Compare'])[2]");
    private static final By ComparisonListBtn = By.xpath("//a[normalize-space()='comparison list']");




    public Magento_Page clickCreateAccUpper()
    {
        Utility.clickingOnElement(driver, CreatAccUpperBtn);
        return new Magento_Page(driver);
    }

    public Magento_Page clickCreateAccLower()
    {
        Utility.clickingOnElement(driver, CreatAccLowerBtn);
        return new Magento_Page(driver);
    }

    public Magento_Page MSGassertionAndGoHome()
    {
        String expectedMessage = "Thank you for registering with Main Website Store.";
        String actualMessage = driver.findElement(By.xpath("//div[@data-bind='html: $parent.prepareMessageForHtml(message.text)']")).getText();
        Assert.assertEquals(actualMessage, expectedMessage);
        driver.get("https://magento.softwaretestingboard.com/");
        return new Magento_Page(driver);
    }

    public Magento_Page AddingItem1()
    {
        Utility.scrollAndHoverAndClick(driver,FirstItemHov, FirstItemAddComp);
        assertTrue(driver.findElement(ComparisonListBtn).isDisplayed(), "Comparison List Button is not displayed on the page.");
        return new Magento_Page(driver);

    }
    public Magento_Page AddingItem2()
    {
        Utility.scrollAndHoverAndClick(driver,SecItemHov, SecItemAddComp);
        assertTrue(driver.findElement(ComparisonListBtn).isDisplayed(), "Comparison List Button is not displayed on the page.");
        return new Magento_Page(driver);

    }


    public Magento_Page filling() {
        Faker faker = new Faker();
        // Generate random data
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String email = faker.internet().emailAddress();
        String password = generateStrongPassword();

        Utility.sendData(driver, FirstName, firstName);
        Utility.sendData(driver, LastName, lastName);
        Utility.sendData(driver, Email, email);
        Utility.sendData(driver, Password, password);
        Utility.sendData(driver, ConfPassword, password);
        return this;
    }
    // Inner method to generate a strong password
    private String generateStrongPassword() {
        Faker faker = new Faker();
        String upperCase = faker.regexify("[A-Z]{2}");
        String lowerCase = faker.regexify("[a-z]{2}");
        String digits = faker.regexify("[0-9]{2}");
        String symbols = faker.regexify("[!@#$%^&*]{2}");
        return upperCase + lowerCase + digits + symbols;
    }






}

