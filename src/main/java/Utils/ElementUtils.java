package Utils;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.io.Files;

public class ElementUtils {

	//Declaring variables
	static int count = 0;
	static String strDate = "nullDate";
	static int ScreenshotCount = 1;

	//Declaration to set Screenshot path
	static String screenShotPath = SystemInfo.getUserDir() + "/ExecutionTestNG/Screenshots/";

	//Wait till element is clickable and then perform click action on element
	public static void clickON(WebElement locator, WebDriver driver , int timeout) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.ignoring(StaleElementReferenceException.class).until(ExpectedConditions.elementToBeClickable(locator));
		locator.click();
	}
	
	public static By nearBy(WebElement locator1, WebElement locator2) {		
		return RelativeLocator.with((By) locator1).below((By) locator2);
	}

	//Wait till element is visible for simple element
	public static void isVisible(WebElement locator, WebDriver driver , int timeout) {
		//		WebDriverWait wait = new WebDriverWait(driver,30);
		//		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[contains(text(),'Search results')]")));
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.ignoring(StaleElementReferenceException.class).until(ExpectedConditions.visibilityOf(locator));
		locator.click();
	}


	//Wait till element is visible for List
	public static void isVisibleinList(List<WebElement> locator, WebDriver driver , int timeout) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.ignoring(StaleElementReferenceException.class).until(ExpectedConditions.visibilityOfAllElements(locator));
	}


	//Function to verify the element visibility on the page. Otherwise print message.
	public static boolean isElementDisplayed(WebElement element, String elementName)
	{
		return element.isDisplayed();
	}

	//Wait till element is clears and then perform clear action on element
	public void clearElement(WebElement locator, WebDriver driver , int timeout) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.ignoring(StaleElementReferenceException.class).until(ExpectedConditions.elementToBeClickable(locator));
		locator.clear();
	}


	//Function to scroll the page until element visible on the page. Otherwise print message.
	public static void scrollToTheElement(WebElement element, WebDriver driver)
	{
		try {
			JavascriptExecutor js = (JavascriptExecutor)driver;
			js.executeScript("arguments[0].scrollIntoView();", element);
			Thread.sleep(500);
			Log.info("Scroll to the " + element + " was successful");
		}
		catch (Exception e) {
			Log.info("FAILED STEP: Web page cannot be scrolled to " + element);
		}
	}

	public static void scrollUp(WebDriver driver)
	{
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollBy(0,-250)", "");
			Thread.sleep(500);
		}
		catch (Exception e) {
		}
	}

	//Scroll page to the top
	public static void scrollToTop(WebDriver driver) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0,0)", "");
	}

	//Scroll page to the bottom
	public static void scrollPageToBottom(WebDriver driver) {
		try {
			((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 1000);");
			long lastHeight = (long) ((JavascriptExecutor) driver).executeScript("return document.body.scrollHeight");

			while (true) {
				((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 1000);");
				((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight);");
				ElementUtils.sleep();
				ElementUtils.sleep();

				long newHeight = (long) ((JavascriptExecutor) driver).executeScript("return document.body.scrollHeight");
				if (newHeight == lastHeight) {
					break;
				}
				lastHeight = newHeight;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	//Function to enter text into the input fields on the page. Otherwise print message.
	public static void enterText(WebElement element, String text)
	{
		try {
			element.clear();
			element.sendKeys(text);
			Log.info("Text was successfully entered in " + element);		}
		catch (Exception e) {
			Log.info("FAILED STEP: Text could not be typed in " + element);
		}
	}


	//Function to take screenshot of the page. Otherwise print message.
	public static void getScreenshot(WebDriver driver)
	{
		if(count == 0) { 
			SimpleDateFormat sdfDate = new SimpleDateFormat("dd MMMM yyyy HH:mm:ss");
			/* dd/MM/yyyy */ 
			Date now = new Date();
			strDate = sdfDate.format(now); 
			strDate=strDate.replace(":", "");
			System.out.println("directoy date" + strDate);
		}

		try { 
			File dir = new File("screenshots/Screenshot for " + strDate); 
			if (!dir.exists()) {			
				if(dir.mkdir()) { 
					System.out.println("Directory is created!"); 
				} 
				else {
					System.out.println("Failed to create directory!");
				}
			} 

			File file =((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE); 
			Files.copy(file, new File(dir + "/Screenshot" + ScreenshotCount + ".jpg"));
			ScreenshotCount++;
		} 
		catch
		(Exception e) { 
			Log.info("FAILED STEP: Screenshot could not be taken");
		}
		count++;
	}


	//Function to fetch the text of any element on the page. Otherwise print message.
	public static String getText(WebElement element)
	{
		try {
			return element.getText();
		}
		catch (Exception e) {
			Log.info("FAILED STEP: Could not extract text from " + element);
			return null;
		}
	}


	//Function to fetch the text of any element on the page. Otherwise print message.
	public static String getAttribute(WebElement element, String text)
	{
		try {
			return element.getAttribute(text);
		}
		catch (Exception e) {
			Log.info("FAILED STEP: Could not extract text from " + element);
			return null;
		}
	}

	//Function to clear text into the input fields on the page. Otherwise print message.
	public static void clearText(WebElement element)
	{
		try {
			element.clear();
			Log.info("Text was successfully cleared in " + element);		}
		catch (Exception e) {
			Log.info("FAILED STEP: Text could not be cleared in " + element);
		}

	}


	//Function to verify the element visibility on the page. Otherwise print message.
	public static boolean isElementDisplayed(WebElement element)
	{
		try {
			return element.isDisplayed();
		}
		catch (Exception e) {
			Log.info("FAILED STEP: Could not find " + element);
			return false;
		}
	}


	//Function to verify Is element highlighted on the page. Otherwise print message.
	public static boolean isHomeElementsHighlighted(WebElement element)
	{
		try {
			if(element.getClass().toString().contains("active"))
				return true;
			else 
				return false;
		}
		catch (Exception e) {
			Log.info("FAILED STEP: Could not find " + element);
			return false;
		}
	}


	//Function to wait for the element after/before some action for the defined time in milliseconds. Otherwise print message.
	public static void sleep()
	{
		try	{
			Thread.sleep(2000);
		}
		catch (Exception e) {
			Log.info("FAILED STEP: Sleep method failed");
		}
	}


	//Function to show the alert on some event on the page. Otherwise print message.
	public static boolean switchToAlert(WebDriver driver)
	{
		try {
			driver.switchTo().alert();
			Log.info("Switch to Alert was successful");
			return true;
		}
		catch (Exception e) {
			Log.info("FAILED STEP: Cannot switch to alert");	
			return false;
		}
	}


	//Function to select a value from dropdown on the page. Otherwise print message.
	public static void selectChannel(WebElement element, String DropDownOption)
	{
		try {
			element.click();
			Select Dropdown=new Select(element);
			Dropdown.selectByVisibleText(DropDownOption);
			Thread.sleep(2000);
			Log.info("Select of " + element + " was successful");
		}
		catch (Exception e) {
			Log.info("FAILED STEP: Select of " + element + " was not successful");	
		}
	}


	/**
	 * Auto Suggestion allows the browser to predict the value. When a user starts to type in a field, 
	 * the browser should display options to fill in the field, based on earlier typed values. 
	 */
	public static void autosuggest(List<WebElement> WebelementList, String InputText)
	{
		System.out.println("list size = " + WebelementList.size());
		for(int i = 0; i < WebelementList.size(); i++)
		{
			if(WebelementList.get(i).getText().toString().equalsIgnoreCase(InputText))
			{
				System.out.println(WebelementList.get(i).getText().toString());
				WebelementList.get(i).click();
				break;
			}
		}
	}


	/**
	 * sendkeys() is a method in Selenium that allows QAs to type 
	 * content automatically into an editable field while executing any tests for forms.
	 * 
	 *  keys.Down - In case, key is released.
	 *  keys.Up - In case, key is pressed
	 *  keys.Enter - When enter button is pressed
	 *  keys.tab - When tab button is pressed
	 *  keys.delete - When delete button is pressed
	 *  keys.control - When user press the ctrl button
	 */
	public static void KeyboardDown(WebElement element)
	{
		try {
			element.sendKeys(Keys.DOWN);
		}
		catch (Exception e) {
			Log.info("FAILED STEP: Down key didn't work");		}
	}


	public static void KeyboardEnter(WebElement element)
	{
		element.sendKeys(Keys.ENTER);
	}

	public static void KeyboardUp(WebElement element)
	{
		element.sendKeys(Keys.UP);
	}

	public static void KeyboardTab(WebElement element)
	{
		element.sendKeys(Keys.TAB);
	}

	public static void KeyboardDelete(WebElement element)
	{
		element.sendKeys(Keys.DELETE);
	}

	public static void KeyboardControl(WebElement element)
	{
		element.sendKeys(Keys.CONTROL);
	}


	//Function used to select the date from the calender.
	public static void selectCalendarDate(String Date, WebElement Calendar, WebElement CalendarSwitch, WebElement CalendarPrev, WebElement CalendarNext, List<WebElement> DateTable, List<WebElement> MonthTable,WebDriver driver) throws InterruptedException
	{
		LocalDate InputDate = null;
		LocalDate CurrentDate = null;
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(Calendar));

		try {
			DateTimeFormatter formatter=DateTimeFormatter.ofPattern("dd/MMM/yyyy");
			InputDate=LocalDate.parse(Date, formatter);
			CurrentDate=java.time.LocalDate.now();
		}
		catch (Exception e) {
			Log.info("FAILED STEP: Cannot covert string to date");		
		}

		Calendar.click();		

		//driver.switchTo().frame("universal_pixel_1y8sd6v");
		Thread.sleep(1000);
		CalendarSwitch.click();

		int i=(InputDate.getYear())-(CurrentDate.getYear());
		if(i>0)
		{
			for(int j = 1; j <= i; j++)
			{
				CalendarNext.click();
			}
		}
		else
		{
			for(int j = i; j < 0; j++)
			{
				CalendarPrev.click();
			}
		}

		String month=(InputDate.getMonth().toString()).substring(0, 3);
		for(int j = 0; j < MonthTable.size(); j++)
		{
			if((MonthTable.get(j).getText().toString()).equalsIgnoreCase(month)) 
			{ MonthTable.get(j).click();
			break;}
		}

		for(int j = 0; j < DateTable.size(); j++)
		{
			if((DateTable.get(j).getText().toString()).equalsIgnoreCase(Integer.toString(InputDate.getDayOfMonth())))
			{
				DateTable.get(j).click();
				break;
			}
		}
	}


	//Function to get current date or future date
	public static String getDate(int timeval, String beforeAfter) {
		try {
			DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MMM/yyyy");
			LocalDateTime now = LocalDateTime.now();	
			LocalDateTime Date;
			if("after".equalsIgnoreCase(beforeAfter)) {
				Date=now.plusDays(timeval);
			}else if("before".equalsIgnoreCase(beforeAfter)) {
				Date=now.minusDays(timeval);
			}else {
				Date = now;
			}
			return Date.format(format);
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}


	//Function to get start and end time
	public static String getTime(int timeval, String beforeAfter) {
		LocalDateTime now = LocalDateTime.now();	
		DateTimeFormatter format = DateTimeFormatter.ofPattern("HH");
		LocalDateTime Time = "after".equalsIgnoreCase(beforeAfter) ? Time = now.plusHours(timeval) : now;

		return Integer.valueOf(now.format(format)) + timeval <=24 ? Time.format(format) + ":00" : "Incorrect Time";
	}


	public static boolean checkEnddate(String endDate) {

		String currentDate = ElementUtils.getDate(0, "");

		try {
			Date current = new SimpleDateFormat("yyyy/MM/dd").parse(currentDate);
			Date end = new SimpleDateFormat("yyyy/MM/dd").parse(endDate);

			System.out.println(currentDate);
			System.out.println(end);

			if (end.compareTo(current) >= 0) {
				System.out.println("start is after end");
				return true;
			} else {
				System.out.println("Something weird happened...");
				return false;
			}

		} catch (ParseException e) {
			e.printStackTrace();
		}
		return false;
	}

	//This method deletes the file into the particular directory i.e., a Single file
	public static void deleteDirectory(final String file) {

		FilenameFilter textFilter = new FilenameFilter() {
			public boolean accept(File dir, String name) {
				return name.equalsIgnoreCase(file);
			}
		};

		File dir = new File(System.getProperty("user.dir"));
		File[] filesList = dir.listFiles(textFilter);

		for (File file1 : filesList) {
			recursiveDelete(file1);
		}
		Log.info("File Deleted SuccessFully");
	}


	//This function delete the files in an recursive way i.e. all the files in the folder
	public static void recursiveDelete(File file) {
		// to end the recursive loop

		if (!file.exists())
			return;

		if (file.isDirectory()) {
			for (File f : file.listFiles()) {
				// call recursively
				recursiveDelete(f);
			}
		}
		// call delete to delete files and empty directory
		file.delete();
		sleep();
		sleep();
		Log.info("Deleted file/folder: " + file.getAbsolutePath());
	}

	//This method used to get screenshot and save it in the folder. The screenshot saved as Path/Name20201231125959.png
	public static String getScreenShot(WebDriver driver, String screenshotName) throws IOException {
		String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		System.out.println("Driver \t" + driver);
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		String destination = screenShotPath + screenshotName + dateName + ".png";
		File finalDestination = new File(destination);
		FileUtils.copyFile(source, finalDestination);
		return destination;
	}
}