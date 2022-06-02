import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.time.Duration;

/*public class Main {

    public static void main(String[] args) {

        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        WebDriver driver = new ChromeDriver(options);
        driver.get("https://www.youtube.com/");
        String Tittle=driver.getTitle();
        System.out.println("Tittle of Page is"+Tittle);



        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");

        //ChromeDriver driver = new ChromeDriver();

        ChromeOptions o= new ChromeOptions();
        o.addArguments("−−incognito");
        DesiredCapabilities c = new DesiredCapabilities();
        c.setCapability(ChromeOptions.CAPABILITY, o);
        WebDriver driver = new ChromeDriver(o);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.navigate().to("https://www.youtube.com/");

    }
}*/
