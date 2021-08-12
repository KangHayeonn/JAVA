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
	
	// ũ�Ѹ� �� URL
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
		
		// â�� ������ʰ� �۾��ϰ� ������
        // ChromeOptions options = new ChromeOptions();
        // options.addArguments("headless");
		
	}
	
	public void crawl() {
		try {
			// get page (= ���������� url�� �ּ�â�� ���� �� request �� �Ͱ� ����)
			driver.get(base_url);
			
			
	
            
			//iframe���� ������ ���� �ش� ���������� ��ȯ��Ų��.
            //driver.switchTo().frame(driver.findElement(By.id("loginForm")));
            
            webElement = driver.findElement(By.xpath("//*[@id=\"inner_login\"]/a[1]"));
            webElement.click();
            
          //*[@id="inner_login"]/a[1]
            
            //iframe ���ο��� id �ʵ� Ž��
            webElement = driver.findElement(By.id("id"));
            Thread.sleep(500); 
            String daum_id ="rkdgkdus522";
            webElement.sendKeys(daum_id);
            //#inner_login > a:nth-child(1)
            
            //iframe ���ο��� pw �ʵ� Ž��
            webElement = driver.findElement(By.id("inputPwd"));
            Thread.sleep(500); 
            String daum_pw ="sj7131513!";
            webElement.sendKeys(daum_pw);
            
 
            //�α��� ��ư Ŭ��
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
		} finally { // ������ ����Ǵ� ��
			driver.close();
		}
	}
}
