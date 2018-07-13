package seleniumdriver;
import MainRun.MainClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;





public class doescrawlikman extends MainClass {
    WebDriver driver = null;

    @BeforeMethod
    public void setDriver() {
        System.setProperty("webdriver.chrome.driver", "E:/iCS_codelab/installations/idealC/IntelliJ IDEA Community Edition 2017.2.3/plugins/chromedriver/chromedriver.exe");
        driver = new ChromeDriver();
    }

    @Test(priority = 1)
    public void tests(){
        openikman(driver);
        crawler(driver);
        setfilters(driver,"5000000", "7500000", 3);
        getdetails(driver, "5000000", "7500000", 3 );
    }
}
