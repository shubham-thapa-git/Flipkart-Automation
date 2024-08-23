package demo.wrappers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class Wrappers {
    /*
     * Write your selenium wrappers here
     */
     public static boolean search(WebElement searchBar, String textToSend, WebDriver driver){
        boolean status=false;

       searchBar.clear();
       searchBar.sendKeys(textToSend);
       Actions action = new Actions(driver);
       action.sendKeys(Keys.ENTER).perform();
       WebElement afterSearch = driver.findElement(By.xpath("//input[@placeholder='Search for products, brands and more']"));
       String result = afterSearch.getAttribute("value");
       if(result.equals(textToSend)){
        status = true;
        return status;
       }
       else{
        return status;
       }
        
        
    }
     
}
