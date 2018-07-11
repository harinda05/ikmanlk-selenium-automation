package seleniumdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.util.List;

@Test
public class doescrawlikman {

        public void doesWebCrawler(){

            // webdriver settings
            System.setProperty("webdriver.chrome.driver", "E:/iCS_codelab/installations/idealC/IntelliJ IDEA Community Edition 2017.2.3/plugins/chromedriver/chromedriver.exe");
            WebDriver driver = new ChromeDriver();

            //open ikman.lk
            driver.get("http://ikman.lk");
            WebDriverWait wait = new WebDriverWait(driver, 15);


            //click on property btn
            String cssOfPropertyButton = "body > div.app-content > div.home-top > div > div.home-focus > div > div:nth-child(1) > div:nth-child(2) > a";
            WebElement propertyButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(cssOfPropertyButton)));
            propertyButton.click();

            //click on houses btn
            String cssOfHouseButton = "body > div.app-content > div > div.serp-listing > div.ui-panel.is-rounded.serp-panel > div.ui-panel-content.ui-panel-block > div:nth-child(1) > div.col-12.lg-3.lg-filter-area > div > div > form > div > div:nth-child(3) > div > ul > li > ul:nth-child(2) > li > ul > li.ui-link-tree-item.cat-411 > a";
            WebElement houseButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(cssOfHouseButton)));
            houseButton.click();

            //click on colombo

            String cssOfColomboButton = "body > div.app-content > div > div.serp-listing > div.ui-panel.is-rounded.serp-panel > div.ui-panel-content.ui-panel-block > div:nth-child(1) > div.col-12.lg-3.lg-filter-area > div > div > form > div > div:nth-child(4) > div > ul > li > ul > li.ui-link-tree-item.loc-1506 > a";
            WebElement colomboButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(cssOfColomboButton)));
            colomboButton.click();

            //click on price arrow
            String cssOfPriceArrow = "body > div.app-content > div > div.serp-listing > div.ui-panel.is-rounded.serp-panel > div.ui-panel-content.ui-panel-block > div:nth-child(1) > div.col-12.lg-3.lg-filter-area > div > div > form > div > div.ui-accordion-item.filter-price > a > div > svg";
            WebElement priceArrow = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(cssOfPriceArrow)));
            priceArrow.click();

            //set min price
            String cssOfMinField = "input[name='filters[0][minimum]']";
            WebElement minField = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(cssOfMinField)));
            String minPrice = "5000000";
            minField.sendKeys(minPrice);

            //set max price
            String cssOfMaxField = "input[name='filters[0][maximum]']";
            WebElement maxField = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(cssOfMaxField)));
            String maxPrice = "7500000";
            maxField.sendKeys(maxPrice);

            //click on apply button
            String cssOfMinMaxApplyButton = "button[class='ui-btn is-standard btn-apply']";
            WebElement minMaxApplyButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(cssOfMinMaxApplyButton)));
            minMaxApplyButton.click();

            //click on beds arrow

            String cssOfBedsArrow = "body > div.app-content > div > div.serp-listing > div.ui-panel.is-rounded.serp-panel > div.ui-panel-content.ui-panel-block > div:nth-child(1) > div.col-12.lg-3.lg-filter-area > div > div > form > div > div.ui-accordion-item.filter-enum.filter-bedrooms > a > div > svg";
            WebElement arrowBeds = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(cssOfBedsArrow)));
            arrowBeds.click();

            //select number of beds as 3
            Integer noOfBeds = 3;
            String cssOfCheckboxBeds = "input[id='filters2values-"+ noOfBeds + "']";
            WebElement checkboxBeds = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(cssOfCheckboxBeds)));
            checkboxBeds.click();

            //get number of ads

            String cssOfAdsCount = "body > div.app-content > div > div.serp-listing > div.ui-panel.is-rounded.serp-panel > div.ui-panel-content.ui-panel-block > div:nth-child(1) > div.col-12.lg-9 > div > div > div:nth-child(1) > div > div > div > span";
            String adsCount = driver.findElement(By.cssSelector(cssOfAdsCount)).getText();
            String[] splits = adsCount.split("Showing 1-25 of | ads");
            String a_count = splits[1];
            System.out.println("No of Ads Found : "+ a_count );


            //get price of each ad
            Integer count = (Integer.parseInt(a_count)/25);

            Integer index = 0;
            Integer ad_index = 1;

            while( index <= count ) {
                String cssOfAdDisplay = "div[class='ui-item']";
                List<WebElement> elements= driver.findElements(By.cssSelector(cssOfAdDisplay));



                for(WebElement e : elements) {
                    String adtext = e.getText();
                    //System.out.println(adtext);

                    String price = adtext.substring(adtext.indexOf("Rs ")+3,adtext.indexOf("Rs ")+12);
                    String beds =  adtext.substring(adtext.indexOf("Beds: ")+6,adtext.indexOf("Beds: ")+7);



                    // checking validity of the results

                    Integer intMinPrice = Integer.parseInt(minPrice); // converting string minprice to int
                    Integer intMaxPrice = Integer.parseInt(maxPrice); // converting string maxprice to int

                    Integer intBeds = Integer.parseInt(beds); // converting ad's string bedcount to int

                    String digitPrice = price.replaceAll("[^0-9.]", ""); // removing commas in origina ad text price
                    Integer intPrice = Integer.parseInt(digitPrice); // converting string price to int

                    String validity = "";


                    if(intPrice>=intMinPrice && intPrice<=intMaxPrice && intBeds==noOfBeds){
                        validity = "valid result";
                    }
                    else{
                        validity = "Invalid result";
                    }

                    //print price results and the validity
                    System.out.println("Ad Number "+ ad_index + " Price is : RS "+ price + " - "+ validity);
                    ad_index++;

                }


                //If available go to next result page
                String cssOfNextBtn = "a[class='col-6 lg-3 pag-next']";

                if(!driver.findElements(By.cssSelector(cssOfNextBtn)).isEmpty()){
                    WebElement nextButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(cssOfNextBtn)));
                    nextButton.click();
                }
                index++;
            }







        }

}
