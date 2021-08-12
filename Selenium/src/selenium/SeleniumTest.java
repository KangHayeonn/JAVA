package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

//import com.sun.tools.javac.util.List;

public class SeleniumTest {
	public static void main(String[] args) {
		
		SeleniumTest selTest = new SeleniumTest();
		selTest.crawl();
		
	}
	
	// WebDriver
	private WebDriver driver;
	
	private WebElement webElement;
	
	// Properties
	public static final String WEB_DRIVER_ID = "webdriver.chrome.driver";
	public static final String WEB_DRIVER_PATH = "C:\\Users\\Drimsys\\selenium/chromedriver.exe";
	
	// 크롤링 할 URL
	private String base_url;
	
	public SeleniumTest() {
		super();
		
		// System Property Setup
		System.setProperty(WEB_DRIVER_ID, WEB_DRIVER_PATH);
		
		// Driver SetUp
		ChromeOptions options = new ChromeOptions();
        options.setCapability("ignoreProtectedModeSettings", true);
        driver = new ChromeDriver(options);
		//driver = new ChromeDriver();
		base_url = "https://www.daum.net";
		
		// 창을 띄우지않고 작업하고 싶을때
        // ChromeOptions options = new ChromeOptions();
        // options.addArguments("headless");
		
	}
	
	public void crawl() {
		try {
			// get page (= 브라우저에서 url을 주소창에 넣은 후 request 한 것과 같다)
			driver.get(base_url);
			
			
	
            
			//iframe으로 구성된 곳은 해당 프레임으로 전환시킨다.
            //driver.switchTo().frame(driver.findElement(By.id("loginForm")));
            
            webElement = driver.findElement(By.xpath("//*[@id=\"inner_login\"]/a[1]"));
            webElement.click();
            
          //*[@id="inner_login"]/a[1]
            
            //iframe 내부에서 id 필드 탐색
            webElement = driver.findElement(By.id("id"));
            Thread.sleep(500); 
            String daum_id ="rkdgkdus522";
            webElement.sendKeys(daum_id);
            //#inner_login > a:nth-child(1)
            
            //iframe 내부에서 pw 필드 탐색
            webElement = driver.findElement(By.id("inputPwd"));
            Thread.sleep(500); 
            String daum_pw ="sj7131513!";
            webElement.sendKeys(daum_pw);
            
 
            //로그인 버튼 클릭
            /*
            webElement = driver.findElement(By.id("loginBtn"));
            webElement.submit();
            
            webElement = driver.findElement(By.xpath("//*[@id=\"afterBtn\"]"));
            webElement.click();
            
            */
            
            webElement = driver.findElement(By.xpath("//*[@id=\"loginBtn\"]"));
            webElement.click();
          
         
            Thread.sleep(500); 
            
            webElement = driver.findElement(By.xpath("//*[@id=\"mArticle\"]/div[1]/div[2]/ul/li[1]/a"));
            webElement.click();
            
            
            /*
            webElement el = driver.findElement(By.xpath("//*[@id=\"mailList\"]/div[1]/div/ul/li[1]/div[2]/a"));
            if(el != null) {
            	System.out.println(el.getText());
                
            }*/
            
          
            //webElement el = driver.findElement(By.xpath("strong"));
            
            
          //*[@id="mailList"]/div[1]/div/ul/li[1]/div[2]/a
            
          //*[@id="mailList"]/div[1]/div/ul/li[1]/div[3]/a[1]/strong
          
            
            Thread.sleep(20000);
            
            System.out.println(driver.getPageSource());
			//System.out.println(driver.getPageSource());
            
           
		} catch(Exception e) {
			e.printStackTrace();
		} finally { // 무조건 실행되는 문
			driver.close();
		}
	}
}
