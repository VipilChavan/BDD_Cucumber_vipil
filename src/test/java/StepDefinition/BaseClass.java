package StepDefinition;

import PageObject.AddNewCustomersPage;
import PageObject.LoginPage;
import PageObject.SearchCustomerPage;
import Utitlities.ReadConfig;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.logging.log4j.*;
import org.openqa.selenium.WebDriver;

public class BaseClass {
    public WebDriver driver;
    public LoginPage lp;

    public AddNewCustomersPage addNewCustpg;
    public SearchCustomerPage searchCustPg;

    public static Logger logs;

    ReadConfig readConfig;
    public String generateRandomEmailId()
    {
       return RandomStringUtils.randomAlphabetic(5);
    }
}
