package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class AddCustomerPage {

    public WebDriver ldriver;

    public AddCustomerPage(WebDriver rdriver)
    {
        ldriver=rdriver;
        PageFactory.initElements(ldriver, this);
    }

    @FindBy(xpath = "//a[@href='#']//p[contains(text(),'Customers')]")
    @CacheLookup
    WebElement lnkCustomers_menu;

    @FindBy(xpath = "//a[@href='/Admin/Customer/List']//p[contains(text(),'Customers')]")
    @CacheLookup
    WebElement lnkCustomers_menuitem;

    @FindBy(xpath = "//a[normalize-space()='Add new']")
    @CacheLookup
    WebElement btnAddnew;

    @FindBy(css = "#Email")
    @CacheLookup
    WebElement txtEmail;

    @FindBy(css = "#Password")
    @CacheLookup
    WebElement txtPassword;

    @FindBy(css = "#FirstName")
    @CacheLookup
    WebElement txtFirstName;

    @FindBy(css = "#LastName")
    @CacheLookup
    WebElement txtLastName;

    @FindBy(css = "#Gender_Male")
    @CacheLookup
    WebElement rdMaleGender;

    @FindBy(css = "#Gender_Female")
    @CacheLookup
    WebElement rdFeMaleGender;

    @FindBy(css = "#DateOfBirth")
    @CacheLookup
    WebElement txtDob;

    @FindBy(css = "#Company")
    @CacheLookup
    WebElement txtCompanyName;

    @FindBy(css = "#IsTaxExempt")
    @CacheLookup
    WebElement chkbxTax;

    @FindBy(css = "div[class='input-group-append'] div[role='listbox']")
    @CacheLookup
    WebElement txtNewsletter;

    @FindBy(xpath = "//li[normalize-space()='Your store name']")
    @CacheLookup
    WebElement lstYSN;

    @FindBy(xpath = "//li[normalize-space()='Test store 2']")
    @CacheLookup
    WebElement lstTs2;

    @FindBy(xpath = "(//div[@role='listbox'])[2]")
    @CacheLookup
    WebElement txtcustomerRoles;

    @FindBy(xpath = "//li[normalize-space()='Administrators']")
    @CacheLookup
    WebElement lstitemAdministrators;

    @FindBy(xpath = "//li[@id='6f0a5b75-f3a7-489d-8ff4-0120f904da1a']")
    @CacheLookup
    WebElement lstitemForumModerators;

    @FindBy(xpath = "//li[normalize-space()='Guests']")
    @CacheLookup
    WebElement lstitemGuests;

    @FindBy(xpath = "//li[normalize-space()='Registered']")
    @CacheLookup
    WebElement lstitemRegistered;

    @FindBy(xpath = "//li[contains(text(),'Vendors')]")
    @CacheLookup
    WebElement lstitemVendors;

    @FindBy(xpath = "//select[@id='VendorId']")
    @CacheLookup
    WebElement drpmgrOfVendor;

    @FindBy(css = "#AdminComment")
    @CacheLookup
    WebElement txtAdminComment;

    @FindBy(css = "button[name='save']")
    @CacheLookup
    WebElement btnSave;

// Action Methods

    public String getPageTitle()
    {
        return ldriver.getTitle();
    }

    public void clickOnCustomersMenu() {
        lnkCustomers_menu.click();
    }

    public void clickOnCustomersMenuItem() {
        lnkCustomers_menuitem.click();
    }

    public void clickOnAddnew() {
        btnAddnew.click();
    }

    public void setEmail(String email){
        txtEmail.sendKeys(email);
    }

    public void setPassword(String password){
        txtPassword.sendKeys(password);
    }

    public void setFirstName(String fname) {
        txtFirstName.sendKeys(fname);
    }

    public void setLastName(String lname) {
        txtLastName.sendKeys(lname);
    }

    public void setGender(String gender)
    {
        if(gender.equals("Male"))
        {
            rdMaleGender.click();
        }
        else if(gender.equals("Female"))
        {
            rdFeMaleGender.click();
        }
        else
        {
            rdMaleGender.click();//Default
        }
    }

    public void setDob(String dob) {
        txtDob.sendKeys(dob);
    }

    public void setCompanyName(String comname) {
        txtCompanyName.sendKeys(comname);
    }

    public void isTaxExempt() {
        chkbxTax.click();
    }

    public void setNewsLetter(String newsLetter) throws InterruptedException {
        txtNewsletter.click();
        Thread.sleep(2000);

        if(newsLetter.equals("Your store name"))
        {
            lstYSN.click();
        }
        else if(newsLetter.equals("Test store 2"))
        {
            lstTs2.click();
        }else
        {
            lstTs2.click();
        }
        Thread.sleep(3000);
    }

    public void setCustomerRoles(String role) throws InterruptedException
    {
        if(!role.equals("Vendors")) //If role is vendors should not delete Register as per req.
        {
            ldriver.findElement(By.xpath("//*[@id='SelectedCustomerRoleIds_taglist']/li/span[2]")).click();
        }

        txtcustomerRoles.click();

        Thread.sleep(3000);

        if(role.equals("Administrators"))
        {
            lstitemAdministrators.click();
        }
        else if(role.equals("Forum Moderators"))
        {
            lstitemForumModerators.click();
        }
        else if(role.equals("Guests"))
        {
            lstitemGuests.click();
        }
        else if(role.equals("Registered"))
        {
            lstitemRegistered.click();
        }
        else if(role.equals("Vendors"))
        {
            lstitemVendors.click();
        }
        else
        {
            lstitemGuests.click();
        }

        Thread.sleep(3000);

//        JavascriptExecutor js = (JavascriptExecutor)ldriver;
//        js.executeScript("arguments[0].click();", listitem);

    }

    public void setManagerOfVendor(String value)
    {
        Select drp=new Select(drpmgrOfVendor);
        drp.selectByVisibleText(value);
    }

    public void setAdminComment(String comment)
    {
        txtAdminComment.sendKeys(comment);
    }

    public void clickOnSave()
    {
        btnSave.click();
    }


}
