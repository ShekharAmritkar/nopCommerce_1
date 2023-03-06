package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.WaitHelper;

import java.time.Duration;
import java.util.List;

public class SearchCustomerPage {

    public WebDriver ldriver;
    public WaitHelper waitHelper;

    public SearchCustomerPage(WebDriver rdriver) {
        ldriver = rdriver;
        PageFactory.initElements(ldriver, this);
        waitHelper = new WaitHelper(ldriver);
    }

    @FindBy(css = "#SearchEmail")
    @CacheLookup
    WebElement txtEmail;

    @FindBy(css = "#SearchFirstName")
    @CacheLookup
    WebElement txtFirstName;

    @FindBy(css = "#SearchLastName")
    @CacheLookup
    WebElement txtLastName;

    @FindBy(css = "#search-customers")
    @CacheLookup
    WebElement btnsearch;

    @FindBy(xpath = "//table[@id='customers-grid']")
    @CacheLookup
    WebElement table;

    @FindBy(xpath = "//table[@id='customers-grid']//tbody/tr")
    @CacheLookup
    List<WebElement> tableRows;

    @FindBy(xpath = "//table[@id='customers-grid']//tbody/tr/td")
    @CacheLookup
    List<WebElement> tableColumns;

    // Action Methods
    public void setEmail(String email) {
        waitHelper.waitForElement(txtEmail, Duration.ofSeconds(30));
        txtEmail.clear();
        txtEmail.sendKeys(email);
    }

    public void setFirstName(String fname) {
        waitHelper.waitForElement(txtFirstName, Duration.ofSeconds(30));
        txtFirstName.clear();
        txtFirstName.sendKeys(fname);
    }

    public void setLastName(String lname) {
        waitHelper.waitForElement(txtLastName, Duration.ofSeconds(30));
        txtLastName.clear();
        txtLastName.sendKeys(lname);
    }

    public void clickSearch() {
        btnsearch.click();
        waitHelper.waitForElement(btnsearch, Duration.ofSeconds(30));
    }

    public int getNoOfRows() {
        return (tableRows.size());
    }

    public int getNoOfColumns() {
        return (tableColumns.size());
    }

    public boolean searchCustomerByEmail(String email) {
        boolean flag = false;

        for (int i = 1; i <= getNoOfRows(); i++) {
            String emailid = table.findElement(By.xpath("//table[@id='customers-grid']/tbody/tr[" + i + "]/td[2]"))
                    .getText();

            if (emailid.equals(email)) {
                flag = true;
                break;
            }
        }
        return flag;
    }

    public boolean searchCustomerByName(String Name) {
        boolean flag = false;

        for (int i = 1; i <= getNoOfRows(); i++) {
            String name = table.findElement(By.xpath("//table[@id='customers-grid']/tbody/tr[" + i + "]/td[3]"))
                    .getText();
            String names[] = name.split(" "); //separating fname & lname

            if (names[0].equals("James") && names[1].equals("Pan")) {
                flag = true;
                break;
            }
        }
        return flag;
    }

}
