import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class HomeWork {
    private static ChromeDriver chromeDriver;
    public static WebDriver driver;

    @BeforeClass
    public void before_class() {
        System.setProperty("webdriver.chrome.driver", Constants.CHROMEDRIVER_PATH);
//        ChromeOptions chromeOptions = new ChromeOptions();
//        chromeOptions.addArguments("--headless");
//        driver = new ChromeDriver(chromeOptions);
        driver = new ChromeDriver();

    }

    //1

    /**
     * Create a TestNG test with the following:
     *  Enter https://dgotlieb.github.io/WebCalculator/
     *  Print “7” button dimensions.
     *  Check if “6” button is displayed
     *  Prepare a String variable with any number
     *  Calculate a mathematical formula that will give the result
     * you choose in the String variable earlier.
     *  Use assert to check if you got the expected result
     * <p>
     * * For example: press 4  press + button  press 2  Press = button
     * The above will generate the result 6 (4+2).
     */
    @Test
    public void test_01_calculator() {
        driver.get("https://dgotlieb.github.io/WebCalculator/");
        System.out.println(driver.findElement(By.id("seven")).getSize());
        System.out.println(driver.findElement(By.id("six")).isDisplayed());
        String result = "8";
        driver.findElement(By.id("five")).click();
        driver.findElement(By.id("add")).click();
        driver.findElement(By.id("five")).click();
        driver.findElement(By.id("equal")).click();
        driver.findElement(By.id("screen")).getText();
        Assert.assertEquals(driver.findElement(By.id("screen")).getText(), result);


    }

    //2

    /**
     * Create a test that assert** Website URL
     * * Prepare strings variable containing the title and URL before
     * Upload your homework code into a Github repository and provide the link
     */

    @Test
    public void test_02_assert_Website_URL() {
        String bingURL = "https://www.bing.com/";
        driver.get("https://www.bing.com/");
        Assert.assertEquals(driver.getCurrentUrl(), bingURL);
    }

    //3

    /**
     * In one of the browsers do the following:
     *  Create a String variable with the website’s title
     *  Refresh website
     * Get website title and use assertion to test if website’s name is equal to
     * the String variable you created in clause 1.
     */

    @Test
    public void test_03_test_title() {
        String titleBin = "Search - Microsoft Bing";
        driver.get("https://www.bing.com/");

        driver.navigate().refresh();
        Assert.assertEquals(driver.getTitle(), titleBin);

    }

    //4

    /**
     * Run browsers without extensions.
     */

    @Test
    public void test_04_extensions() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--disable-extensions");
        chromeDriver = new ChromeDriver(chromeOptions);
    }

    //5

    /**
     * Create a TestNG test with the following:
     *  Enter https://dgotlieb.github.io/Actions
     *  Take a screenshot of the box element
     *  Double click the text “Double-click...” check what happened
     * and create assertion on result.
     *  Make a mouse hover on X image.
     *  Select two items from food list.
     *  Upload a file.
     *  Scroll down to “click me button” try both scroll to element
     * and scroll to location.
     */
    @Test
    public void test_05_actions_screen_shot() {
        driver.get("https://dgotlieb.github.io/Actions");


        takeElementScreenshot(driver.findElement(By.id("div1")));
    }


    private static void takeElementScreenshot(WebElement element) {
        File screenShotFile = element.getScreenshotAs(OutputType.FILE); // take the screenshot
        try {
            FileUtils.copyFile(screenShotFile, new File("element-screenshot.png")); // save screenshot to disk
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_06_actions_doubleClick() {
        driver.get("https://dgotlieb.github.io/Actions");


        WebElement buttonElement = driver.findElement(By.id("double"));
        Actions myAction = new Actions(driver);
        myAction.doubleClick(buttonElement);
        myAction.perform();

        String result = driver.findElement(By.id("demo")).getText();
        Assert.assertEquals("You double clicked", result);
    }

    @Test
    public void test_07_actions_mouse_hover() {
        driver.get("https://dgotlieb.github.io/Actions");

        WebElement buttonElement = driver.findElement(By.id("close"));
        Actions myAction = new Actions(driver);
        myAction.moveToElement(buttonElement);
        myAction.build().perform();

    }

    @Test
    public void test_08_actions_food_list() {
        driver.get("https://dgotlieb.github.io/Actions");

        List<WebElement> elementsList = driver.findElements(By.name("kind"));
        Actions builder = new Actions(driver);
        builder.clickAndHold(elementsList.get(0)).clickAndHold(elementsList.get(1)).click();
        builder.build().perform();
    }

    @Test
    public void test_09_actions_upload_file() {
        driver.get("https://dgotlieb.github.io/Actions");

        driver.findElement(By.name("pic")).sendKeys("C:\\Users\\Sergii\\Desktop\\AllClases.pdf");

    }

    @Test
    public void test_10_actions_scroll_down() {
        driver.get("https://dgotlieb.github.io/Actions");


        WebElement element = driver.findElement(By.id("clickMe"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    @Test
    public void test_11_scrollToLocation() {
        driver.get("https://dgotlieb.github.io/Actions");

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("javascript:window.scrollBy(250,350)");

    }

    //6

    /**
     *  Open https://dgotlieb.github.io/Controllers/
     *  Select “Cheese option” in radio buttons.
     *  Select one of the options from the drop down below.
     *  Print all elements in drop down.
     */

    @Test
    public void test_12_controllers() {
        driver.get("https://dgotlieb.github.io/Controllers/");

        List<WebElement> list = driver.findElements(By.name("group1"));
        for (WebElement tempElement : list) {
            if (tempElement.getAttribute("value").equals("Cheese")) {
                tempElement.click();
            }

        }

        Select selection = new Select(driver.findElement(By.name("dropdownmenu")));
        selection.selectByValue("Milk");
        for (int i = 0; i < selection.getOptions().size(); i++) {
            System.out.println(selection.getOptions().get(i).getText());
        }
    }

    //7

    /**
     * Create a TestNG test with the following:
     *  Enter https://dgotlieb.github.io/WebCalculator/
     *  Print “2” button height.
     *  Print “6” button width.
     */

    @Test
    public void test_13_webcalculator() {
        driver.get("https://dgotlieb.github.io/WebCalculator/");

        System.out.println(driver.findElement(By.id("two")).getRect().getHeight());
        System.out.println(driver.findElement(By.id("six")).getRect().getWidth());

    }


    @AfterClass
    public void afterAll() {
        driver.quit();
    }
}


