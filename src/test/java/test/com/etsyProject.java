/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test.com;

import com.itexpert.etsyproject1.FileUtil;
import com.itexpert.etsyproject1.LoginVO;
import java.util.concurrent.TimeUnit;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class etsyProject {
    private WebDriver driver;
    private String baseUrl;
    private String baseUrl2;
    private  JavascriptExecutor js;
    static  LoginVO login = null;
    
     public etsyProject() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        login = FileUtil.getLoginData();
    }
    
    @AfterClass
    public static void tearDownClass() {
       
    }
    
    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver","C:\\Users\\Vitali\\Documents\\selenium dependencies\\drivers\\chromedriver.exe");
        driver = new ChromeDriver();
        baseUrl = "https://www.etsy.com/ca/your/account?ref=hdr_user_menu-settings";
        baseUrl2 ="https://www.etsy.com/";
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
         js = (JavascriptExecutor) driver;
         
          driver.get(baseUrl);
     driver.findElement(By.id("join_neu_email_field")).sendKeys(login.getUserName());
     driver.findElement(By.id("join_neu_password_field")).sendKeys(login.getPassword());
     driver.findElement(By.xpath("//button[@name='submit_attempt']")).click();
    
    }
    
    @After
    public void tearDown() {
        driver.quit();
    }
   // @Ignore
    @Test
    public void test4ChangeCurrency() throws InterruptedException {
        driver.findElement(By.xpath("//*[@id='settings']/div[2]/nav/ul/li[2]/a")).click();
        js.executeScript("window.scrollBy(0,350)", "");
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id='update-preferences-form']/fieldset[2]/div/div/ul/li[1]/ul/li[3]/label")).click();
        Thread.sleep(1000);
           js.executeScript("window.scrollBy(0,250)", "");
           driver.findElement(By.xpath("//input[@value='Update Preferences']")).click();
            Thread.sleep(3000);
            js.executeScript("window.scrollBy(0,1050)", "");
             String currency = login.getCurrency();
         
    assertEquals(currency, driver.findElement(By.xpath("//button[@aria-label='Update your settings United States English (US) € (EUR)']"))
              .getAttribute("aria-label"));
    }
    
   //  @Ignore
     @Test
     public void test5ChangeRegion() throws InterruptedException {
         driver.findElement(By.xpath("//*[@id='settings']/div[2]/nav/ul/li[2]/a")).click();
       // js.executeScript("window.scrollBy(0,650)", "");
      Select drpRegion = new Select(driver.findElement(By.xpath("//select[@name='preferences[region_code]']")));
      Thread.sleep(3000);
          drpRegion.selectByVisibleText(login.getRegion());
         // js.executeScript("window.scrollBy(0,450)", "");
          driver.findElement(By.xpath("//input[@value='Update Preferences']")).click();
         // js.executeScript("window.scrollBy(0,1150)", "");
        String region = login.getRegionVerf();
         assertEquals(region, driver.findElement(By.xpath("//button[@aria-label='Update your settings Canada English (US) € (EUR)']"))
         .getAttribute("aria-label"));
            
     }
   // @Ignore
     @Test
     public void test6NewPasswordNotMatch() throws InterruptedException{
    driver.findElement(By.id("current-password")).sendKeys(login.getPassword());
    driver.findElement(By.id("password")).sendKeys(login.getNewPass3());
    driver.findElement(By.id("password-confirm")).sendKeys(login.getNewPass3Invalid());
    driver.findElement(By.xpath("//div[@id='change-password']")).click();
    
     String errorMessage = login.getErrorMesg3();
       WebElement element = driver.findElement(By.xpath("//div[@role='alert']/div/ul/li"));
        assertEquals(errorMessage,element.getText() );
       }
     
  //   @Ignore
     @Test
     public void test7CurrentPassInvalid() throws InterruptedException{
    driver.findElement(By.id("current-password")).sendKeys(login.getCurrentPassInvalid());
    driver.findElement(By.id("password")).sendKeys(login.getNewPass3());
    driver.findElement(By.id("password-confirm")).sendKeys(login.getNewPass3Invalid());
    driver.findElement(By.xpath("//div[@id='change-password']")).click();
       String errorMessage4 = login.getErrorMesg4();
        WebElement element4 = driver.findElement(By.xpath("//div[@role='alert']/div/ul/li[2]"));
        assertEquals(errorMessage4,element4.getText() );
      }
     
     //@Ignore
      @Test
     public void test8HelpCenterPage() {
   // js.executeScript("window.scrollBy(0,1050)", "");
    driver.findElement(By.linkText("Help Center")).click();
       String titleHelpCenter = "Etsy Help";
       assertEquals(titleHelpCenter,driver.getTitle());
     }
     
  // @Ignore
    @Test
    public void test9LanguageChange() throws InterruptedException{
        driver.findElement(By.xpath("//*[@id='settings']/div[2]/nav/ul/li[2]/a")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//div[@id='preferences-language-selector']/ul/li[4]")).click();
        js.executeScript("window.scrollBy(0,950)", "");
        driver.findElement(By.xpath("//input[@value='Update Preferences']")).click();
        System.out.println(driver.getTitle());
        String titleInSpanish= "Etsy - Preferencias";
         assertEquals(titleInSpanish,driver.getTitle());
        }
     
    @Ignore
     @Test
     public void test10ChangePassword(){
    driver.findElement(By.id("current-password")).sendKeys(login.getPassword());
    driver.findElement(By.id("password")).sendKeys(login.getNewPassword());  
    driver.findElement(By.id("password-confirm")).sendKeys(login.getNewPassword());
    driver.findElement(By.xpath("//div[@id='change-password']")).click();
    
      // String errorMesage3 ="Your new password must be at least 6 characters";
       // WebElement element3 = driver.findElement(By.xpath("//*[@id='settings']/div[2]/div[2]/div/ul/li"));
       // assertEquals(errorMesage3, element3.getText());
      }
   // @Ignore
      @Test
    public void test1sumeetEtsy() throws Exception {
        driver.get(baseUrl2);
        
        driver.findElement(By.xpath("//header[@id='gnav-header-inner']/div[4]/nav/ul/li/button")).click();
        driver.findElement(By.id("join_neu_email_field")).sendKeys(login.getUserName());
        driver.findElement(By.id("join_neu_password_field")).sendKeys(login.getPassword());
        driver.findElement(By.name("submit_attempt")).click();
        String title = driver.getTitle();
        assertEquals("Etsy - Shop for handmade, vintage, custom, and unique gifts for everyone", title);
    }
  //  @Ignore
    @Test
    public void test2InvalidEmail() throws Exception {
       
        driver.get(baseUrl2);

        driver.findElement(By.xpath("//header[@id='gnav-header-inner']/div[4]/nav/ul/li/button")).click();
        driver.findElement(By.id("join_neu_email_field")).sendKeys("shah1295@gmail.com");
        driver.findElement(By.id("join_neu_password_field")).sendKeys("Password123");
        driver.findElement(By.name("submit_attempt")).click();
          WebElement errorMessage5 = driver.findElement(By.xpath("//div[contains(text(),'Email address is invalid.')]"));
          String errorMesg5 = login.getErrorMesg5();
          assertEquals(errorMesg5,errorMessage5 .getText());
              
    }
    //@Ignore
    @Test
    public void test3InvalidPassword() throws Exception {
        driver.get(baseUrl2);
        
        driver.findElement(By.xpath("//header[@id='gnav-header-inner']/div[4]/nav/ul/li/button")).click();
        driver.findElement(By.id("join_neu_email_field")).sendKeys(login.getUserName());
        driver.findElement(By.id("join_neu_password_field")).click();
        driver.findElement(By.id("join_neu_password_field")).sendKeys(login.getNewPassword());
        driver.findElement(By.name("submit_attempt")).click();
        WebElement errorMessage6 = driver.findElement(By.xpath("//div[contains(text(), 'Password was incorrect.')]")); 
        String errorMes6 = login.getErrorMesg6();
      assertEquals(errorMes6, errorMessage6.getText());
    }



    

     
     

    
}
