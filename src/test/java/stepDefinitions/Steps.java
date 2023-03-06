package stepDefinitions;

import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import pageObjects.AddCustomerPage;
import pageObjects.LoginPage;
import pageObjects.SearchCustomerPage;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Steps extends BaseClass {

    @Before
    public void setup() throws IOException
    {
        //Logging
        logger=Logger.getLogger("nopCommerce");
        PropertyConfigurator.configure("Log4j.properties");
        logger.setLevel(Level.DEBUG);

        //Load properties file
        configProp= new Properties();
        FileInputStream configPropfile = new FileInputStream("config.properties");
        configProp.load(configPropfile);

        String br=configProp.getProperty("browser"); //getting the browser name from config.properties file

        //Launching browser
        if (br.equals("firefox")) {
        driver = new FirefoxDriver();
        }
		else if (br.equals("chrome")) {
        driver = new ChromeDriver();
        }
        else if (br.equals("edge")) {
        driver = new EdgeDriver();
        }
    }

    //Login steps..
    @Given("User Launch Chrome browser")
    public void user_launch_chrome_browser() {

        logger.info("************* Launching Browser *****************");
        lp = new LoginPage(driver);
    }

    @When("User opens URL {string}")
    public void user_opens_url(String url) {
        logger.info("************* Opening URL  *****************");
        driver.get(url);
        driver.manage().window().maximize();
    }

    @And("User enters Email as {string} and Password as {string}")
    public void user_enters_email_as_and_password_as(String email, String password) {
        logger.info("************* Prvding user and password *****************");
        lp.setUserName(email);
        lp.setPassword(password);
    }

    @And("Click on Login")
    public void click_on_login() {
        logger.info("************* click on login *****************");
        lp.clickLogin();
    }

    @Then("Page Title should be {string}")
    public void page_title_should_be(String exptitle) {
        if (driver.getPageSource().contains("Login was unsuccessful.")){
            logger.info("************* Login failed *****************");
            driver.close();
            Assert.assertTrue(false);
        } else {
            logger.info("************* Login Passed *****************");
            Assert.assertEquals(exptitle,driver.getTitle());
        }
    }

    @When("User click on Log out link")
    public void user_click_on_log_out_link() throws InterruptedException {
        logger.info("************* clicking on logout *****************");
        lp.clickLogout();
        Thread.sleep(3000);
    }

    @Then("Close browser")
    public void close_browser() {
        logger.info("************* closing browser *****************");
        driver.quit();
    }

// Customer feature step definitions

    @Then("User can view Dashboard")
    public void userCanViewDashboard() {
        addCust=new AddCustomerPage(driver);
        logger.info("********* Verifying Dashboard page title after login successful **************");
        Assert.assertEquals("Dashboard / nopCommerce administration",addCust.getPageTitle());
    }

    @When("User clicks on customers Menu")
    public void userClicksOnCustomersMenu() throws InterruptedException {
        Thread.sleep(2000);
        logger.info("********* Clicking on customer main menu **************");
        addCust.clickOnCustomersMenu();
    }

    @And("click on customers Menu Item")
    public void clickOnCustomersMenuItem() throws InterruptedException {
        Thread.sleep(2000);
        logger.info("********* Clicking on customer sub menu **************");
        addCust.clickOnCustomersMenuItem();
    }

    @And("click on Add new button")
    public void clickOnAddNewButton() throws InterruptedException {
        addCust.clickOnAddnew();
        Thread.sleep(2000);
    }

    @Then("User can view Add new customer page")
    public void userCanViewAddNewCustomerPage() {
        Assert.assertEquals("Add a new customer / nopCommerce administration",addCust.getPageTitle());
    }

    @When("User enter customer info")
    public void userEnterCustomerInfo() throws InterruptedException {
        logger.info("********* Adding new customer **************");
        logger.info("********* Providing customer details **************");
        String email = randomestring() + "@gmail.com";
        addCust.setEmail(email);
        addCust.setPassword("admin456");
        addCust.setFirstName("Ishant");
        addCust.setLastName("Sharma");
        addCust.setGender("Male");
        addCust.setDob("6/11/1982");
        addCust.setCompanyName("Test Solutions");
        addCust.isTaxExempt();
        addCust.setNewsLetter("Test store 2");
        Thread.sleep(2000);
        addCust.setCustomerRoles("Guests");
        Thread.sleep(2000);
        addCust.setManagerOfVendor("Vendor 1");
        addCust.setAdminComment("We can Test anything");

    }

    @And("click on Save button")
    public void clickOnSaveButton() throws InterruptedException {
        logger.info("********* Saving customer details **************");
        addCust.clickOnSave();
        Thread.sleep(2000);
    }

    @Then("User can view confirmation massage {string}")
    public void userCanViewConfirmationMassage(String arg0) {
        Assert.assertTrue(driver.findElement(By.tagName("body")).getText()
                .contains("The new customer has been added successfully"));
    }

   // steps for searching a customer by using Email ID.

    @And("Enter customer EMail")
    public void enterCustomerEMail() {
        searchCust = new SearchCustomerPage(driver);
        logger.info("********* Searching customer details by Email **************");
        searchCust.setEmail("james_pan@nopCommerce.com");
    }

    @When("Click on search button")
    public void clickOnSearchButton() throws InterruptedException {
        searchCust.clickSearch();
        Thread.sleep(3000);
    }

    @Then("User should found Email in the Search table")
    public void userShouldFoundEmailInTheSearchTable() {
        boolean status = searchCust.searchCustomerByEmail("james_pan@nopCommerce.com");
        Assert.assertEquals(true,status);
    }

    //steps for searching a customer by Name

    @And("Enter customer FirstName")
    public void enterCustomerFirstName() {
        logger.info("********* Searching customer details by Name **************");
        searchCust=new SearchCustomerPage(driver);
        searchCust.setFirstName("James");
    }

    @And("Enter customer LastName")
    public void enterCustomerLastName() {
        searchCust.setLastName("Pan");
    }

    @Then("User should found Name in the Search table")
    public void userShouldFoundNameInTheSearchTable() {
        boolean status = searchCust.searchCustomerByName("James Pan");
        Assert.assertEquals(true,status);
    }
}
