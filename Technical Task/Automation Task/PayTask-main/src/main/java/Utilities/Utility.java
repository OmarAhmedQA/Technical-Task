package Utilities;

import com.assertthat.selenium_shutterbug.core.Capture;
import com.assertthat.selenium_shutterbug.core.Shutterbug;
import io.qameta.allure.Allure;
import io.restassured.response.Response;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.*;


public class Utility
{

    public static WebElement findWebElement(WebDriver driver, By locator)
    {
        return driver.findElement(locator);

    }




    public static void scrolling(WebDriver driver, By locator)
    {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", findWebElement(driver, locator));

    }




    public static void selectingFromDropDown(WebDriver driver, By locator, String option)
    {
        new Select(findWebElement(driver, locator)).selectByVisibleText(option);

    }





    public static String getTimeStamp()
    {
        return new SimpleDateFormat("yyyy-MM-dd-hh-mm-ssa").format(new Date());

    }




    private static final String SCREENSHOTS_PATH = "test-outputs/Screenshots/";
    public static void takeScreenShot(WebDriver driver, String screenshotName)
    {

        try
        {
            // Capture screenshot using TakesScreenshot
            File screenshotSrc = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

            // Save screenshot to a file if needed
            File screenshotFile = new File(SCREENSHOTS_PATH + screenshotName + "-" + getTimeStamp() + ".png");
            FileUtils.copyFile(screenshotSrc, screenshotFile);

            // Attach the screenshot to Allure
            Allure.addAttachment(screenshotName, Files.newInputStream(Path.of(screenshotFile.getPath())));
        }
        catch (Exception e)
        {
            LogsUtils.error(e.getMessage());
        }

    }

    public static void takeHighlightedScreenshot(WebDriver driver, By locator)
    {
        try
        {
            Shutterbug.shootPage(driver, Capture.FULL_SCROLL)
                    .highlight(findWebElement(driver, locator))
                    .save(SCREENSHOTS_PATH);
        }
        catch (Exception e)
        {
            LogsUtils.error(e.getMessage());
        }

    }




    public static void clickingOnElement(WebDriver driver, By locator)
    {
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.elementToBeClickable(locator));
        driver.findElement(locator).click();

    }




    public static void sendData(WebDriver driver, By locator, String data)
    {
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(locator));
        driver.findElement(locator).sendKeys(data);

         }




    public static String getText(WebDriver driver, By locator)
    {
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(locator));
        return driver.findElement(locator).getText();
    }




    public static WebDriverWait generalWait(WebDriver driver)
    {
        return new WebDriverWait(driver, Duration.ofSeconds(5));
    }




    public static int generateRandomNumber(int MaxNumber)
    {
        return new Random().nextInt(MaxNumber) + 1;
   }




    public static Set<Integer> generateUniqueNumber(int NoOfUniqueNumbers, int MaxNumber)
    {
        Set<Integer> generatedNumbers = new HashSet<>();
        while (generatedNumbers.size() < NoOfUniqueNumbers)
        {
            int randomNumber = generateRandomNumber(MaxNumber);
            generatedNumbers.add(randomNumber);
        }
        return generatedNumbers;
    }




    public static boolean VerifyURL(WebDriver driver, String expectedURL)
    {
        try
        {
            generalWait(driver).until(ExpectedConditions.urlToBe(expectedURL));
        }
        catch (Exception e)
        {
            return false;
        }
        return true;
    }




    public static File getLatestFile(String folderPath) {
        File folder = new File(folderPath);
        File[] files = folder.listFiles();
        assert files != null;
        if (files.length == 0)
            return null;
        Arrays.sort(files, Comparator.comparingLong(File::lastModified).reversed());

        return files[0];
   }





    public static void scrollAndHoverAndClick(WebDriver driver, By elementYYLocator, By elementXXLocator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Scroll down to find element YY
        WebElement elementYY = wait.until(ExpectedConditions.visibilityOfElementLocated(elementYYLocator));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", elementYY);

        // Hover over element YY
        Actions actions = new Actions(driver);
        actions.moveToElement(elementYY).perform();

        // Click on element XX
        WebElement elementXX = wait.until(ExpectedConditions.elementToBeClickable(elementXXLocator));
        elementXX.click();
    }



}
