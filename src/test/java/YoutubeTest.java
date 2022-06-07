
import io.github.bonigarcia.wdm.WebDriverManager;
import org.checkerframework.checker.units.qual.C;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.concurrent.TimeUnit;


//ANGOL nyelvű böngésző és Youtube kell hozzá



@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class YoutubeTest {



    static WebDriver driver;
    Actions action;


    @BeforeAll
    public static void Setup(){

        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-extensions");
        //options.addArguments("--headless");
        options.addArguments("--window-size=1920,1080");
        options.addArguments("start-maximized","--incognito", "--lang=en");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("https://www.youtube.com/");
    }


    @Test
    @Order(1)
    public void login() {




        WebElement signin = driver.findElement(By.xpath("//ytd-button-renderer[@class=\"signin style-scope ytd-consent-bump-v2-lightbox style-suggestive size-default\"]"));
        signin.click();
        Actions action = new Actions(driver);
        action.pause(Duration.ofMillis(200));
        WebElement email = driver.findElement(By.xpath("//*[@id=\"identifierId\"]"));
        email.click();
        action.moveToElement(email);
        email.sendKeys("youtube.test@freemail.hu");


        WebElement nextButton = driver.findElement(By.xpath("//*[@class=\"qhFLie\"]//button"));
        nextButton.click();
        WebElement password = driver.findElement(By.xpath("//*[@id=\"password\"]//input"));
        password.click();
        password.sendKeys("!Codecool3");
        WebElement next = driver.findElement(By.xpath("//*[@id=\"passwordNext\"]/div/button/span"));
        next.click();
    }



    @Test
    @Order(2)
    public void logout(){

        login();

        WebElement avatar = driver.findElement(By.xpath("//ytd-topbar-menu-button-renderer[@class=\"style-scope ytd-masthead\"]"));
        avatar.click();
        WebElement logout = driver.findElement(By.xpath("//a[@href=\"/logout\"]"));
        logout.click();
    }

    @Test
    @Order(3)
    public void settings() throws InterruptedException {



        login();
        WebElement settings = driver.findElement(By.xpath("//a[@href=\"/account\"]"));
        settings.click();
        WebElement moresettings = driver.findElement(By.xpath("//*[@href=\"https://myaccount.google.com/\"]"));
        moresettings.click();


        WebElement personalinfo = driver.findElement(By.xpath("//*[@class=\"BBRNg\"][2]"));
        personalinfo.click();


        WebElement name = driver.findElement(By.xpath("//a[@href=\"profile/name?continue=https%3A%2F%2Fmyaccount.google.com%2Fpersonal-info\"]"));
        name.click();

        WebElement nameInput = driver.findElement(By.xpath("//*[@aria-label=\"Edit Nickname\"]"));
        nameInput.click();

        WebElement sendName = driver.findElement(By.xpath("//input[@class=\"VfPpkd-fmcmS-wGMbrd CtvUB\"]"));
        sendName.sendKeys("Codecool");

        WebElement save = driver.findElement(By.xpath("//button[@class=\"VfPpkd-LgbsSe VfPpkd-LgbsSe-OWXEXe-k8QpJ VfPpkd-LgbsSe-OWXEXe-dgl2Hf nCP5yc AjY5Oe DuMIQc qfvgSe SdOXCb\"]"));
        save.click();

        WebElement selector = driver.findElement(By.xpath("//a[@class=\"gb_A\"]"));
       selector.click();

       driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@role=\"presentation\"]")));


        WebElement youtube = driver.findElement(By.xpath("//ul[@class=\"LVal7b u4RcUd\"]//li[4]"));
        youtube.click();

    }

    @Test
    @Order(4)
    public void search() throws InterruptedException {

        login();
        WebElement serachbox = driver.findElement(By.xpath("//input[@id=\"search\"]"));
        serachbox.sendKeys("Dunakanyar Rally - Márianosztrai szakasz");

        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
        Actions action = new Actions(driver);
        action.pause(Duration.ofMillis(1000));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"search-icon-legacy\"]")));



        WebElement searchicon = driver.findElement(By.xpath("//*[@id=\"search-icon-legacy\"]"));
        action.pause(Duration.ofMillis(2000));

        searchicon.click();


        action.pause(Duration.ofMillis(1000));


        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,500)");
        action.pause(Duration.ofSeconds(1)).perform();
        js.executeScript("window.scrollBy(0,500)");

        action.pause(Duration.ofMillis(2000));

        WebElement video = driver.findElement(By.xpath("//*[@src=\"https://i.ytimg.com/vi/-abT7oksftU/hq720.jpg?sqp=-oaymwEcCNAFEJQDSFXyq4qpAw4IARUAAIhCGAFwAcABBg==&rs=AOn4CLCPV56an1mSPtxg1x3FFDFpehdfug\"]"));
        wait.until(ExpectedConditions.visibilityOf(video));
        video.click();
        Thread.sleep(5000);

    }

    @Test
    @Order(5)
    public void history(){

        login();
        WebElement history = driver.findElement(By.xpath("//a[@title=\"History\"]"));
        history.click();

        WebElement historyVideo = driver.findElement(By.xpath("//img[@src=\"https://i.ytimg.com/vi/-abT7oksftU/hqdefault.jpg?sqp=-oaymwEcCNACELwBSFXyq4qpAw4IARUAAIhCGAFwAcABBg==&rs=AOn4CLBWYYelaXjY60T3ZbKi1l6Ma9zQ7Q\"]"));

        historyVideo.click();

    }

    @Test
    @Order(6)
    public void historyDelete() throws InterruptedException {

        login();
        WebElement history = driver.findElement(By.xpath("//a[@title=\"History\"]"));
        history.click();


        WebElement deleter = driver.findElement(By.xpath("//*[@id=\"top-level-buttons-computed\"]//yt-icon-button[1]"));
        deleter.click();
        Thread.sleep(5000);

    }

    @Test
    @Order(7)
    public  void subscribe(){


        login();
        WebElement serachbox = driver.findElement(By.xpath("//input[@id=\"search\"]"));
        serachbox.sendKeys("Rallydream");
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
        Actions action = new Actions(driver);
        action.pause(Duration.ofMillis(1000));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"search-icon-legacy\"]")));
        action.pause(Duration.ofMillis(1000));
        WebElement searchicon = driver.findElement(By.xpath("//*[@id=\"search-icon-legacy\"]"));
        action.pause(Duration.ofMillis(1000));
        searchicon.click();
        action.pause(Duration.ofMillis(2000));
        WebElement firstVideo = driver.findElement(By.xpath("//*[@id=\"items\"]/ytd-video-renderer[1]"));
        firstVideo.click();


        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,500)");
        action.pause(Duration.ofSeconds(1)).perform();

        WebElement subscribeButton = driver.findElement(By.xpath("//*[@id=\"subscribe-button\"]/ytd-subscribe-button-renderer"));
        subscribeButton.click();

    }

    @Test
    @Order(8)
    public  void unSubscribe() throws InterruptedException {


        login();
        WebElement serachbox = driver.findElement(By.xpath("//input[@id=\"search\"]"));
        serachbox.sendKeys("Rallydream");
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
        Actions action = new Actions(driver);
        action.pause(Duration.ofMillis(1000));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"search-icon-legacy\"]")));
        action.pause(Duration.ofMillis(1000));
        WebElement searchicon = driver.findElement(By.xpath("//*[@id=\"search-icon-legacy\"]"));
        action.pause(Duration.ofMillis(2000));
        searchicon.click();
        action.pause(Duration.ofMillis(2000));
        WebElement firstVideo = driver.findElement(By.xpath("//*[@id=\"items\"]/ytd-video-renderer[1]"));
        firstVideo.click();

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,500)");
        action.pause(Duration.ofSeconds(1)).perform();
        js.executeScript("window.scrollBy(0,500)");
        action.pause(Duration.ofMillis(1000));
        Thread.sleep(1000);

        WebElement subscribeButton = driver.findElement(By.xpath("//*[@id=\"subscribe-button\"]/ytd-subscribe-button-renderer"));
        subscribeButton.click();

        WebElement unSubsribeButton = driver.findElement(By.xpath("//yt-button-renderer[@id=\"confirm-button\"]"));
        unSubsribeButton.click();
    }


    @AfterEach
    public void Dispose(){


        driver.quit();;
    }


}
