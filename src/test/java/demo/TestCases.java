package demo;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;


// import io.github.bonigarcia.wdm.WebDriverManager;
import demo.wrappers.Wrappers;

public class TestCases {
    ChromeDriver driver;
     

    /*
     * TODO: Write your tests here with testng @Test annotation. 
     * Follow `testCase01` `testCase02`... format or what is provided in instructions
     */
 @Test(alwaysRun = true)
    public void testCase01() throws InterruptedException{
        System.out.println("Start Test case: testCase01");
        driver.get("http://www.flipkart.com");
        // try{
        //     WebElement loginpopup = driver.findElement(By.xpath("//span[@class='_30XB9F']"));
        //     loginpopup.click();
        // }
        // catch(Exception e){
        //     System.out.println("login popup not found");
        // }

        WebElement searchBar = driver.findElement(By.xpath("//input[@placeholder='Search for Products, Brands and More']"));
        Boolean status = Wrappers.search(searchBar, "Washing Machine", driver);
        if(status){
            System.out.println("search successfull");
        }
        else{
            System.out.println("search failed");
        }
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='Popularity']")));
        WebElement popularityButton = driver.findElement(By.xpath("//div[text()='Popularity']"));
        
        popularityButton.click();
        int count=0;
        Thread.sleep(3000);
        List<WebElement> washingMachines = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy((By.xpath("//div[@class='XQDdHH']"))));
        for (WebElement elem : washingMachines) {
            try {
                String temp = elem.getText();
                //System.out.println(temp);
                Double tempNum = Double.parseDouble(temp);
                if (tempNum > 0.0 && tempNum <= 4.0) {
                    count++;
                }
            } catch (StaleElementReferenceException e) {
                // Re-locate the element and retry
                WebElement updatedElem = driver.findElement(By.xpath("//div[@class='XQDdHH']"));
                String temp = updatedElem.getText();
                try {
                    Double tempNum = Double.parseDouble(temp);
                    if (tempNum > 0.0 && tempNum <= 4.0) {
                        count++;
                    }
                } catch (NumberFormatException nfe) {
                    System.out.println("Failed to parse number: " + temp);
                }
            } catch (NumberFormatException e) {
                System.out.println("Failed to parse number: " + elem.getText());
            }
        }

        if (count > 0) {
            System.out.println("Number of washing machines with ratings less than or equal to 4 stars: " + count);
        } else {
            System.out.println("No machine found upto 4*");
        }

        System.out.println("End Test case: testCase01");
    }

    @Test(alwaysRun = true)
    public void testCase02() {
        System.out.println("Start Test case: testCase02");
        driver.get("http://www.flipkart.com");
        try{
            WebElement loginpopup = driver.findElement(By.xpath("//span[@class='_30XB9F']"));
            loginpopup.click();
        }
        catch(Exception e){
            System.out.println("login popup not found");
        }
        WebElement search = driver.findElement(By.xpath("//input[@placeholder='Search for Products, Brands and More']"));
       
       boolean status = Wrappers.search(search, "iPhone", driver);
       if(status){
        System.out.println("search successfull");
       }
       else{
        System.out.println("search not performed");
       }

       WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(30));
        List<WebElement> iphoneTitles = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy((By.xpath("//div[@class='KzDlHZ']"))));
        List<WebElement> discounts = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@class='UkUFwK']/span")));

        ArrayList<String> results = new ArrayList<>();
        for (int i = 0; i < iphoneTitles.size(); i++) {
            String title = iphoneTitles.get(i).getText();
            String disc = discounts.get(i).getText();
            disc = disc.replace("% off","").trim();
            Double disNum = 0.0;
            try {
                
                disNum = Double.parseDouble(disc);
                
                
                if (disNum >= 17) {
                  
                    results.add(title + " has a discount of " + disNum+"%");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid rating format for: " + title + ", rating: " + disNum);
            }
        }

        for(int i=0;i<results.size();i++){
            System.out.println(results.get(i));
        }
       

        System.out.println("End Test case: testCase02");
    }

    @Test(alwaysRun = true)
    public void testCase03() throws InterruptedException{
        System.out.println("Start Test case: testCase03");

        driver.get("http://www.flipkart.com");

        try{
            WebElement loginpopup = driver.findElement(By.xpath("//span[@class='_30XB9F']"));
            loginpopup.click();
        }
        catch(Exception e){
            System.out.println("login popup not found");
        }
        WebElement search = driver.findElement(By.xpath("//input[@placeholder='Search for Products, Brands and More']"));
       

        boolean status = Wrappers.search(search, "Coffee Mug", driver);
        if(status){
            System.out.println("search performed");
        }
        else{
            System.out.println("search failed");
        }

       
        WebElement selectRating = driver.findElement(By.xpath("//div[@class='ewzVkT _3DvUAf']//div//div[@class='_6i1qKy']"));
        selectRating.click();
        Thread.sleep(3000);
        List<Integer> actualCountsOfReviews = new ArrayList<Integer>();
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(30));
        List<WebElement> mugsReviewPoints = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//span[@class='Wphh3N']")));
        for(WebElement elem :mugsReviewPoints){
           String counts = elem.getText();
           counts =counts.replaceAll("[^0-9]","");
           
           actualCountsOfReviews.add(Integer.parseInt(counts));
        }

        Collections.sort(actualCountsOfReviews, Collections.reverseOrder());
        List<Integer> reducedList = new ArrayList<Integer>(5);
        for(Integer in: actualCountsOfReviews){
            reducedList.add(in);
            if(reducedList.size()==5){
                break;
            }
            else{
                continue;
        }
    }
    List<String> strList = new ArrayList<String>();
    List<String> finalList = new ArrayList<>();
    for(int i=0;i<reducedList.size();i++){
        strList.add(reducedList.get(i).toString());
    }
    StringBuilder sb = new StringBuilder();
    //need to format the string and add ',' and ()
    //need to calculate the string length and add the ','accordingly
    for(String st:strList){
        if(st.length()<=3){
    sb.append(String.format("(%s)", st));
    finalList.add(sb.toString());
            sb= new StringBuilder();
    }
    else if(st.length()==4 ){
        char c = st.charAt(0);
        sb.append(String.format("("+c+",%s)", st.substring(1, st.length())));
        finalList.add(sb.toString());
        sb= new StringBuilder();
    }
    else if(st.length()==5){
        String s = st.substring(0,1);
        sb.append(String.format("("+s+",%s)", st.substring(2, st.length())));
        finalList.add(sb.toString());
        sb= new StringBuilder();
    }
}
        for(String st:finalList){
       WebElement imageElem = driver.findElement(By.xpath("//span[@class='Wphh3N' and text()='"+st+"']//ancestor::div[@class='slAVV4']/a/div/div//div[@class='_4WELSP']/img"));
        String imageSrc = imageElem.getAttribute("src");
        WebElement titleElem = driver.findElement(By.xpath("//span[@class='Wphh3N'and text()='"+st+"']//ancestor::div[@class='slAVV4']//a[2]"));
        String title = titleElem.getAttribute("title");
        System.out.println("Rating: "+st);
        System.out.println("Image source :"+imageSrc);
        System.out.println("Title = " +title);
    
        
        }
        System.out.println("End Test case: testCase03");


    }
       

     
    /*
     * Do not change the provided methods unless necessary, they will help in automation and assessment
     */
    @BeforeTest
    public void startBrowser()
    {
        System.setProperty("java.util.logging.config.file", "logging.properties");

        // NOT NEEDED FOR SELENIUM MANAGER
        // WebDriverManager.chromedriver().timeout(30).setup();

        ChromeOptions options = new ChromeOptions();
        LoggingPreferences logs = new LoggingPreferences();

        logs.enable(LogType.BROWSER, Level.ALL);
        logs.enable(LogType.DRIVER, Level.ALL);
        options.setCapability("goog:loggingPrefs", logs);
        options.addArguments("--remote-allow-origins=*");

        System.setProperty(ChromeDriverService.CHROME_DRIVER_LOG_PROPERTY, "build/chromedriver.log"); 

        driver = new ChromeDriver(options);

        driver.manage().window().maximize();
    }

   
    
    @AfterTest
    public void endTest()
    {
        driver.close();
        driver.quit();

    }
}
