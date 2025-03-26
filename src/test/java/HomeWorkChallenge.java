import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HomeWorkChallenge {

    public static WebDriver driver;

    @BeforeClass
    public void before_class() {
        System.setProperty("webdriver.chrome.driver", Constants.CHROMEDRIVER_PATH);
        driver = new ChromeDriver();
    }


    //1

    /**
     *  Open any browser on "The Marker" website.
     *  https://www.themarker.com/
     *  Check how many times the word "news" exists in the page
     * source.
     *  Hint: use driver.getPageSource() method.
     */

    @Test
    public void test_01_marker_words() {
        driver.get("https://www.themarker.com/");

        String in = driver.getPageSource();
        int i = 0;
        Pattern p = Pattern.compile("news");
        Matcher m = p.matcher(in);
        while (m.find()) {
            i++;
        }
        System.out.println(i);
    }

    //2

    /**
     * Using JavaScriptExecutor
     *  Print the current page (The same as if you pressed file 
     * print in your browser).
     */

    @Test
    public void test_02_print_page() {
        driver.get("https://www.themarker.com/");

        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;

        jsExecutor.executeScript("window.print();");

    }
}

