package StepDefinition;

import PageObject.AddNewCustomersPage;
import PageObject.LoginPage;
import PageObject.SearchCustomerPage;
import Utitlities.ReadConfig;
import io.cucumber.java.*;
import io.cucumber.java.en.*;
import io.github.bonigarcia.wdm.WebDriverManager;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.FindBy;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Steps extends BaseClass{

    @Before
    public void setup() {
        readConfig= new ReadConfig();
        logs= LogManager.getLogger("Steps");
//        System.out.println("Setup Method Executed");
        logs.info("Setup Method Executed");
        String browser =readConfig.getBrowser();

        if (browser.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();

        } else if (browser.equalsIgnoreCase("msedge")) {
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
            } else if (browser.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        } else {
            driver = null;
        }
        if (driver != null) {
            driver.manage().window().maximize();
        }
        //Implicitly Wiat
        if (driver != null) {
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        }


    }

//    @Before("@sanity")//conditional hook it will execute only for sanity test cases
//    public void setup() {
//        System.out.println("Setup Method Executed");
//        WebDriverManager.chromedriver().setup();
//        driver = new ChromeDriver();
//
//    }
//    @Before(order = 1)
//    public void setup1()
//    {
//        System.out.println("Setup Method Executed");
//        WebDriverManager.chromedriver().setup();
//        driver= new ChromeDriver();
//    }
//    @Before(order = 2)
//    public void setup1()
//    {
//        System.out.println("Setup Method Executed");
//        WebDriverManager.chromedriver().setup();
//        driver= new ChromeDriver();
//    }
    @Given("User should open chrome browser")
    public void user_should_open_chrome_browser() {

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        lp= new LoginPage(driver);
        addNewCustpg= new AddNewCustomersPage(driver);
        searchCustPg= new SearchCustomerPage(driver);

        logs.info("chrome browser launched");

    }

    @When("User enters URL {string}")
    public void user_enters_url(String url) {

        driver.get(url);
        logs.info("User enters url");
    }
    @When("User enters Email as {string} and Password as {string}")
    public void user_enters_email_as_and_password_as(String email, String password) {

        lp.enterEmail(email);
        lp.enterPassword(password);
        logs.info("User enters email and password");

    }

    @When("Click on Login button")
    public void click_on_login_button() {
        lp.clickOnLogin();
        logs.info("User clicked login button");

    }

    @Then("Page Title should be {string}")
    public void page_title_should_be(String expectedTitle) {
        String actualTitle=driver.getTitle();
        if(actualTitle.equals(expectedTitle))
        {
            logs.warn("Page Title got matched");
            Assert.assertTrue(true);
        }else {
            logs.warn("Page Title not matched");
            Assert.assertTrue("Login Not Successful",false);
        }

    }

    @When("User click on Log out link")
    public void user_click_on_log_out_link() {
        lp.clickOnLogout();
        logs.info("User clicked on log out button");

    }

    @Then("close browser")
    public void close_browser() {
        driver.close();
//        driver.quit();

        logs.info("Browser got closed ");
    }
    ///////////////ADD NEW CUSTOMERS///////////////////

    @Then("User can view Dashboad")
    public void user_can_view_dashboad() {
        String actualtitle= addNewCustpg.getPageTitle();
        String expectedTitle="Dashboard / nopCommerce administration";
        if(actualtitle.equals(expectedTitle))
        {
            logs.warn("User can view DashBoard");
            Assert.assertTrue(true);
        }else
        {
            logs.warn("User cannot view DashBoard");
            Assert.assertTrue("Page Title Not Matched",false);
        }

    }

    @When("User click on customers Menu")
    public void user_click_on_customers_menu() {
        addNewCustpg.clickOnCustomersMenu();

        logs.info("User clicked in customer menu");
    }

    @When("click on customers Menu Item")
    public void click_on_customers_menu_item() {
        addNewCustpg.clickOnCustomersMenuItem();
        logs.info("User clicked in customer menu item");
    }

    @When("click on Add new button")
    public void click_on_add_new_button() {
        addNewCustpg.clickOnAddnew();
        logs.info("User clicked in customer add new button");
    }

    @Then("User can view Add new customer page")
    public void user_can_view_add_new_customer_page() {
        String actualTitile= addNewCustpg.getPageTitle();
        String expectedTitle = "Add a new customer / nopCommerce administration";
        if(actualTitile.equals(expectedTitle))
        {
            logs.info("User_can_view_add_new_customer_page");
            Assert.assertTrue(true);
        }else
        {
            logs.info("User_cannot_view_add_new_customer_page");
            Assert.assertTrue("Page Title Not Matched",false);
        }
    }

    @When("User enter customer info")
    public void user_enter_customer_info() {
//        addNewCustpg.enterEmail("cs1294@gmail.com");
        addNewCustpg.enterEmail(generateRandomEmailId() + "@gmail.com");
        addNewCustpg.enterPassword("test1");
        addNewCustpg.enterFirstName("Prachi");
        addNewCustpg.enterLastName("Gupta");
        addNewCustpg.enterGender("Female");
        addNewCustpg.enterDob("6/13/1988");
        addNewCustpg.enterCompanyName("CodeStudio");
        addNewCustpg.enterAdminContent("Admin content");
        addNewCustpg.enterManagerOfVendor("Vendor 1");

        logs.info("User information got entered");
    }

    @When("click on Save button")
    public void click_on_save_button() {
        addNewCustpg.clickOnSave();
        logs.info("User clicked on save button");
    }

    @Then("User can view confirmation message {string}")
    public void user_can_view_confirmation_message(String exptectedConfirmationMsg) {
        String bodyTagText = driver.findElement(By.tagName("Body")).getText();
        if(bodyTagText.contains(exptectedConfirmationMsg))
        {
            logs.info("user_can_view_confirmation_message");
            Assert.assertTrue(true);//pass

        }
        else
        {
            logs.warn("user_cannot_view_confirmation_message");
            Assert.assertTrue(false);//fail

        }
    }

    @When("Enter customer EMail")
    public void enter_customer_e_mail() {
        logs.info("User enter_customer_e_mail ");
        searchCustPg.enterEmailAdd("victoria_victoria@nopCommerce.com");
    }

    @When("Click on search button")
    public void click_on_search_button() {
        searchCustPg.clickOnSearchButton();
//        log.info("Clicked on search button.");

        logs.info("User clicked on search button");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    @Then("User should found Email in the Search table")
    public void user_should_found_email_in_the_search_table() {
        String expectedEmail = "victoria_victoria@nopCommerce.com";

//           Assert.assertTrue(searchCustPg.searchCustomerByEmail(expectedEmail));

        if( searchCustPg.searchCustomerByEmail(expectedEmail) ==true)
        {
            logs.info(" user_found_email_in_the_search_table");
            Assert.assertTrue(true);
//            log.info("User should found Email in the Search table - passed");

        }
        else {
            logs.info(" user_not_found_email_in_the_search_table");
//            log.info("User should found Email in the Search table - passed");
            Assert.assertTrue(false);

        }


    }

    @When("Enter customer FirstName")
    public void enter_customer_first_name() {
        searchCustPg.enterFirstName("Victoria");
    }

    @When("Enter customer LastName")
    public void enter_customer_last_name() {
        searchCustPg.enterLastName("Terces");

    }

    @Then("User should found Name in the Search table")
    public void user_should_found_name_in_the_search_table() {
        String expectedName = "Victoria Terces";


        if( searchCustPg.searchCustomerByName(expectedName) ==true)
        {
            Assert.assertTrue(true);
        }
        else
            Assert.assertTrue(false);

    }

    @After
    public void teardown(Scenario sc) throws IOException {
        System.out.println("Tear Down Method Executed");

        if (sc.isFailed()==true)
        {
            String path="E:\\SeleniumAutomation\\my_project\\BDDCumcumberFramework\\Test-Output\\ScreenShot\\Failed.png";

            TakesScreenshot screenshot= (TakesScreenshot)driver;
           File src= screenshot.getScreenshotAs(OutputType.FILE);

           File des= new File(path);
            try {
                FileUtils.copyFile(src,des);
            } catch (IOException e) {
                System.out.println("Scenario failed but failed to take ScreenShot");
                throw new RuntimeException(e);
            }
        }

        driver.quit();
    }

//    @After(order = 2)
//    public void teardown()
//    {
//        System.out.println("Tear Down Method Executed");
//        driver.quit();
//    }

//    @After(order = 1)
//    public void teardown2()
//    {
//        System.out.println("Tear Down Method Executed");
//        driver.quit();
//    }

    @BeforeStep
	public void beforeStepMethodDemo()
	{
		System.out.println("This is before step....");
	}


	@AfterStep
	public void afterStepMethodDemo()
	{
		System.out.println("This is after step....");
	}


}
