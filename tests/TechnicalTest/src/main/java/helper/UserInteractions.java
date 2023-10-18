package helper;


import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.List;
import org.openqa.selenium.JavascriptExecutor;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
//import org.testng.Assert;

import com.google.common.io.Files;

public class UserInteractions {
	protected WebDriver driver;
	protected WebDriverWait wait;
	private String snapPath="";
	
	public UserInteractions(WebDriver driver) {
		this.driver = driver;
		 this.wait = new WebDriverWait(driver, 10);
	}
	
	public void inputKeys(String input, By by, String elementName, int time)  {
		if(OverallReporter.failureFlag!="Failed") {
		try {
			wait = new WebDriverWait(driver, time);
		WebElement element =wait.until(ExpectedConditions.presenceOfElementLocated(by));
		
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;  
		jsExecutor.executeScript("arguments[0].style.border='2px solid red'", element);   
		
		element.sendKeys(input);
		Reporter.report("Pass","<b>"+input +"</b>"+" should be entered in "+ elementName, input +" entered in "+ elementName, takeSnapShot());	
	
		jsExecutor.executeScript("arguments[0].style.border='0px solid red'", element); 
		}catch (TimeoutException e){
			e.printStackTrace();
			failTest("<b>"+input +"</b>"+" should be entered in "+ elementName,input +" could not be entered in "+ elementName+" due to "+e.getClass(),takeSnapShot());
		}catch (Exception e){
			e.printStackTrace();
			failTest("<b>"+input +"</b>"+" should be entered in "+ elementName,input +" could not be entered in "+ elementName+" due to "+e.getClass(),takeSnapShot());
		}	
		}
		}
	
	private void failTest(String expectedResult, String actualResult,String snap) {
		// TODO Auto-generated method stub
		OverallReporter.TCStatus="Fail";
		OverallReporter.reasonForFailure=actualResult;
		if(!snap.equals(""))
		OverallReporter.failureScreen= snap;
		OverallReporter.failureMap.put(OverallReporter.failureCount+". "+actualResult, snap);
		OverallReporter.failureCount = OverallReporter.failureCount+1;
		Reporter.report("Fail","<b>"+expectedResult, actualResult, snap);
		OverallReporter.failureFlag="Failed";
//		Assert.fail(actualResult);
	
	}
	
	public void failTestInReportAndContinue(String expectedResult, String actualResult,String snap) {
		// TODO Auto-generated method stub
		OverallReporter.TCStatus="Fail";
		OverallReporter.reasonForFailure=actualResult;
		OverallReporter.failureMap.put(OverallReporter.failureCount+". "+actualResult, snap);
		OverallReporter.failureCount = OverallReporter.failureCount+1;
		if(!snap.equals(""))
		OverallReporter.failureScreen= snap;
		Reporter.report("<font color=\"red\">Fail</font>","<b>"+expectedResult, actualResult, snap);	
	}

	public void jsInputKeys(String input, By by, String elementName, int time)  {
		if(OverallReporter.failureFlag!="Failed") {
		try {
			wait = new WebDriverWait(driver, time);
		WebElement element =wait.until(ExpectedConditions.presenceOfElementLocated(by));
		
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver; 
		jsExecutor.executeScript("arguments[0].scrollIntoView()", element); 
		
		Thread.sleep(100);
		driver.switchTo().activeElement().sendKeys(Keys.TAB);
		driver.switchTo().activeElement().sendKeys(input);
		Reporter.report("Pass","<b>"+input +"</b>"+" should be entered in "+ elementName, input +" entered in "+ elementName, takeSnapShot());	
	
		jsExecutor.executeScript("arguments[0].style.border='0px solid red'", element); 
		}catch (TimeoutException e){
			e.printStackTrace();
			failTest("<b>"+input +"</b>"+" should be entered in "+ elementName, input +" could not be entered in "+ elementName+" due to "+e.getClass(), takeSnapShot());
		}catch (Exception e){
			e.printStackTrace();
			failTest("<b>"+input +"</b>"+" should be entered in "+ elementName, input +" could not be entered in "+ elementName+" as "+e.getClass(), takeSnapShot());
		}	
		}
		}
	
	public void activeInputKeys(String input)  {
		if(OverallReporter.failureFlag!="Failed") {
		try {
		driver.switchTo().activeElement().sendKeys(input);
		Reporter.report("Pass","<b>"+input +"</b>"+" should be entered in active element", input +" entered in active element", takeSnapShot());	
		
		}catch (TimeoutException e){
			e.printStackTrace();
			failTest("<b>"+input +"</b>"+" should be entered in active element", input +" entered in active element", takeSnapShot());	
			}catch (Exception e){
			e.printStackTrace();
			failTest("<b>"+input +"</b>"+" should be entered in active element", input +" entered in active element", takeSnapShot());	
				}
		}
	}
	
	public void inputKeys(Keys input, By by) {
		if(OverallReporter.failureFlag!="Failed") {
		try {
			wait = new WebDriverWait(driver, 10);
		WebElement element =wait.until(ExpectedConditions.presenceOfElementLocated(by));
		
		element.sendKeys(input);
		}catch (Exception e){
			e.printStackTrace();
		}	
		}
		}
	
	public void dragElement(int range, By by, String elementName) {
		if(OverallReporter.failureFlag!="Failed") {
		try {
			wait = new WebDriverWait(driver, 10);
			WebElement element =wait.until(ExpectedConditions.presenceOfElementLocated(by));

			int value = Integer.parseInt(element.getAttribute("value"));
			if(value!=range) {
				if(value>range) {
					for(int i=0;i<(value-range);i++)
						inputKeys(Keys.ARROW_LEFT,by);
				}else {
					for(int i=0;i<(range-value);i++)
						inputKeys(Keys.ARROW_RIGHT,by);
				}
			}
			Reporter.report("Pass","Range value should be selected as "+ "<b>"+range +"</b>", "Range value selected as "+ range, takeSnapShot());	
		}catch (TimeoutException e){
			e.printStackTrace();
			failTest("Range value should be selected as "+ "<b>"+range +"</b>", "Range value could not be selected as "+ range, takeSnapShot());
		}catch (Exception e){
			e.printStackTrace();
			failTest("Range value should be selected as "+ "<b>"+range +"</b>", "Range value could not be selected as "+ range, takeSnapShot());
		}
		}
	}
		
	
	public void click(By by, String elementName, int time)  {
		if(OverallReporter.failureFlag!="Failed") {
		wait = new WebDriverWait(driver, time);
		try {
			WebElement element =wait.until(ExpectedConditions.presenceOfElementLocated(by));

			JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;  
			jsExecutor.executeScript("arguments[0].style.border='2px solid red'", element);   
			String snap = takeSnapShot();
			jsExecutor.executeScript("arguments[0].style.border='0px solid red'", element); 
			element.click();	
			Reporter.report("Pass",elementName +" should be clicked", elementName +" clicked",snap);	

		}catch(TimeoutException e) {
			System.out.println(e.getClass());
			failTest(elementName +" should be clicked", elementName +" could not be clicked as element not present with : "+by+" not present", takeSnapShot());	
			
		
			}catch (StaleElementReferenceException e){
			e.printStackTrace();
			failTest(elementName +" should be clicked", elementName +" could not be clicked due to "+e.getClass(), takeSnapShot());	
		}
		}
		
	}
	
	public void jsClick(By by, String elementDescription, int i)  {
		if(OverallReporter.failureFlag!="Failed") {
		wait = new WebDriverWait(driver, 10);
		
		try {
			WebElement element =wait.until(ExpectedConditions.elementToBeClickable(by));
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].scrollIntoView()", element);
			js.executeScript("arguments[0].style.border='2px solid red'", element); 
			String snap = takeSnapShot();
			js.executeScript("arguments[0].click();", element);
			Reporter.report("Pass",elementDescription +" should be clicked", elementDescription +" clicked",snap);	

		}catch(TimeoutException e) {
			System.out.println(e.getClass());
			failTest(elementDescription +" should be clicked", elementDescription +" could not be clicked as element not present with : "+by+" not present", takeSnapShot());	
			
		
			}catch(Exception e) {
				System.out.println(e.getClass());
				failTest(elementDescription +" should be clicked", elementDescription +" could not be clicked due to "+e.getClass(), takeSnapShot());	
				
			}
		}
	}
	
	public void click(By by, String elementName)  {
		if(OverallReporter.failureFlag!="Failed") {
		wait = new WebDriverWait(driver, 10);
		try {
		WebElement element =wait.until(ExpectedConditions.presenceOfElementLocated(by));
			element.click();	
	}catch(TimeoutException e) {
		System.out.println(e.getClass());
		failTest(elementName +" should be clicked", elementName +" could not be clicked as element not present with : "+by+" not present", takeSnapShot());	
		
	
		}catch (Exception e){
		e.printStackTrace();
		failTest(elementName +" should be clicked", elementName +" could not be clicked", takeSnapShot());	
		}
		}
	}
	
	public void checkElement(By by, String elementName)  {
		if(OverallReporter.failureFlag!="Failed") {
		wait = new WebDriverWait(driver, 10);
		try {
			WebElement element =wait.until(ExpectedConditions.presenceOfElementLocated(by));
			
			JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;  
			jsExecutor.executeScript("arguments[0].style.border='2px solid red'", element);   		
			Reporter.report("Pass",elementName +" should be present", elementName +" was present", takeSnapShot());		
			jsExecutor.executeScript("arguments[0].style.border='0px solid red'", element);  
			
	}catch (TimeoutException e){
		e.printStackTrace();
		failTest(elementName +" should be present", elementName +" was not present", takeSnapShot());	
		}catch (Exception e){
		e.printStackTrace();
		failTest(elementName +" should be present", elementName +" was not present", takeSnapShot());	
		
		}
		}
	}
	
	public WebElement getElement(By by, String elementName)  {
		if(OverallReporter.failureFlag!="Failed") {
		wait = new WebDriverWait(driver, 10);
		try {
			WebElement element =wait.until(ExpectedConditions.presenceOfElementLocated(by));
			
			JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;  
			jsExecutor.executeScript("arguments[0].scrollIntoView()", element);
			jsExecutor.executeScript("arguments[0].style.border='2px solid red'", element);
			takeSnapShot();
//			Reporter.report("Pass",elementName +" should be present", elementName +" was present", takeSnapShot());		
			jsExecutor.executeScript("arguments[0].style.border='0px solid red'", element);  
			return element;
			
	}catch (TimeoutException e){
		e.printStackTrace();
		failTest(elementName +" should be present", elementName +" was not present", takeSnapShot());	
		}catch (Exception e){
		e.printStackTrace();
		failTest(elementName +" should be present", elementName +" was not present", takeSnapShot());	
		
		}
		}
		return null;
		
	}
	
	
	public WebElement getElement(By by, String elementName, int time)  {
		if(OverallReporter.failureFlag!="Failed") {
		wait = new WebDriverWait(driver, time);
		try {
			WebElement element =wait.until(ExpectedConditions.presenceOfElementLocated(by));
			
			JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;  
			jsExecutor.executeScript("arguments[0].scrollIntoView()", element);
			jsExecutor.executeScript("arguments[0].style.border='2px solid red'", element);   		
			Reporter.report("Pass",elementName +" should be present", elementName +" was present", takeSnapShot());		
			jsExecutor.executeScript("arguments[0].style.border='0px solid red'", element);  
			return element;
			
	}catch (TimeoutException e){
		e.printStackTrace();
		failTest(elementName +" should be present", elementName +" was not present", takeSnapShot());	
		}catch (Exception e){
		e.printStackTrace();
		failTest(elementName +" should be present", elementName +" was not present", takeSnapShot());	
		
		}
		}
		return null;
		
	}

	public void checkBoxSelection(boolean state, By by, String elementName, int time)  {
		if(OverallReporter.failureFlag!="Failed") {
		wait = new WebDriverWait(driver, time);
		try {
		WebElement element =wait.until(ExpectedConditions.presenceOfElementLocated(by));
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;  
		jsExecutor.executeScript("arguments[0].style.border='2px solid red'", element);   
		
		boolean status = element.isSelected();
		if(state!=status) {
			element.click();	
		}
		Reporter.report("Pass","<b>"+state +"</b>"+" should be selected for "+ elementName, state +" selected for "+ elementName, takeSnapShot());	

		jsExecutor.executeScript("arguments[0].style.border='0px solid red'", element);   
	}catch (TimeoutException e){
		e.printStackTrace();
		failTest("<b>"+state +"</b>"+" should be selected for "+ elementName, state +" could not be selected for "+ elementName+" as "+e.getClass(), takeSnapShot());	
		}catch (Exception e){
		e.printStackTrace();
		failTest("<b>"+state +"</b>"+" should be selected for "+ elementName, state +" could not be selected for "+ elementName+" as "+e.getClass(), takeSnapShot());	
		
		}
		}
	}
	
	
	public void dropDownSelection(String option,String type, By by, String elementName, int time)  {
		if(OverallReporter.failureFlag!="Failed") {
		wait = new WebDriverWait(driver, time);
		try {
		WebElement element =wait.until(ExpectedConditions.presenceOfElementLocated(by));
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;  
		jsExecutor.executeScript("arguments[0].style.border='2px solid red'", element);   
		Select objSelect = new Select(element);
		if(type.equals("value"))
			objSelect.selectByValue(option);
		else if(type.equals("text"))
			objSelect.deselectByVisibleText(option);
		
		Reporter.report("Pass","<b>"+option +"</b>"+" should be selected for "+ elementName, option +" selected for "+ elementName, takeSnapShot());	
		
		jsExecutor.executeScript("arguments[0].style.border='0px solid red'", element);   
		}catch (TimeoutException e){
		e.printStackTrace();
		failTest("<b>"+option +"</b>"+" should be selected for "+ elementName, option +" could not be selected for "+ elementName+" as "+e.getClass(), takeSnapShot());	
		}catch (Exception e){
		e.printStackTrace();
		failTest("<b>"+option +"</b>"+" should be selected for "+ elementName, option +" could not be selected for "+ elementName+" as "+e.getClass(), takeSnapShot());	
		
		}
		}	
	}
	
	public void clickRadio(By by, String elementName, int time)  {
		if(OverallReporter.failureFlag!="Failed") {
		wait = new WebDriverWait(driver, time);
		try {
		WebElement element =wait.until(ExpectedConditions.presenceOfElementLocated(by));
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;  
		jsExecutor.executeScript("arguments[0].style.border='2px solid red'", element);   
		
		boolean bol = element.isSelected();		
			if(bol!=true) {
				element.click();
			}
		Reporter.report("Pass","Radio Button "+elementName+" should be clicked", "Radio Button "+elementName+" clicked", takeSnapShot());	 
		jsExecutor.executeScript("arguments[0].style.border='0px solid red'", element);   
		}catch (TimeoutException e){
		e.printStackTrace();
		failTest("Radio Button "+elementName+" should be clicked", "Radio Button "+elementName+" Ccould not be clicked as "+e.getClass(), takeSnapShot());	
		}catch (Exception e){
		e.printStackTrace();
		failTest("Radio Button "+elementName+" should be clicked", "Radio Button "+elementName+" Ccould not be clicked as "+e.getClass(),takeSnapShot());	
		}
		}
		
	}
	
	public void pressKey(int key) {
		if(OverallReporter.failureFlag!="Failed") {
		Robot robo;
		try {
			robo = new Robot();
			robo.keyPress(key);
			robo.keyRelease(key);
			Thread.sleep(10);
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	}
	
	public void pressNumber(int i)  {
		if(OverallReporter.failureFlag!="Failed") {
		String numString = Integer.toString(i);
		String[] numArray = numString.split("");
		
		Robot robo;
		try {
			robo = new Robot();
			for(int j=0;j<numArray.length;j++) {
				int k = toKeyEvent(numArray[j]);
			robo.keyPress(k);
			robo.keyRelease(k);
			Thread.sleep(10);
			}
			Reporter.report("Pass","<b>"+i +"</b>"+" should be entered in active Element", i+" entered in Active Element", takeSnapShot());	

		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			failTest("<b>"+i +"</b>"+" should be entered in active Element", i+" could not be entered in Active Element", takeSnapShot());	
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			failTest("<b>"+i +"</b>"+" should be entered in active Element", i+" could not be entered in Active Element", takeSnapShot());	
		}
		}
	}
	
	public void ReportComparision(String status, String expected, String actual) {
		if(OverallReporter.failureFlag!="Failed") {
			Reporter.report(status,expected, actual, takeSnapShot());	
		}
		
}
	public void addReportComparision(String status, String expected, String actual)  {
		if(OverallReporter.failureFlag!="Failed") {
		
		Reporter.report(status,expected, actual, snapPath);		
		}
		
}
	
	
	
	private int toKeyEvent(String string) {
		int k = Integer.parseInt(string);
		switch (k){
		case 0:
			return KeyEvent.VK_0;
		case 1:
			return KeyEvent.VK_1;
		case 2:
			return KeyEvent.VK_2;
		case 3:
			return KeyEvent.VK_3;
		case 4:
			return KeyEvent.VK_4;
		case 5:
			return KeyEvent.VK_5;
		case 6:
			return KeyEvent.VK_6;
		case 7:
			return KeyEvent.VK_7;
		case 8:
			return KeyEvent.VK_8;
		case 9:
			return KeyEvent.VK_9;
		}
	return 0;
	}

	public String takeSnapShot() {
		String fileWithPath=Reporter.screenPath();
		TakesScreenshot scrShot =(TakesScreenshot)driver;
		File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);
		try {
		FileUtils.copyFile(SrcFile, new File(fileWithPath));
		}catch(Exception e) {
			
		}
		File DestFile=new File(fileWithPath);
		try {
			Files.copy(SrcFile, DestFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		snapPath = fileWithPath.replace(Reporter.path, ".");
		return snapPath;
	}
	
	public List<WebElement> getElements(By by)  {
		wait = new WebDriverWait(driver, 10);
		try {
		List<WebElement> elements =wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;  
		jsExecutor.executeScript("arguments[0].scrollIntoView()", elements.get(0));
		jsExecutor.executeScript("arguments[0].style.border='2px solid red'", elements.get(0));   		
//		Reporter.report("Pass",elements +" should be present", elementName +" was present", takeSnapShot());		
		jsExecutor.executeScript("arguments[0].style.border='0px solid red'", elements.get(0));  
		return elements;
		}catch (TimeoutException e){
			failTest("<b> elements with by "+by +"</b>"+" should be present", "<b> elements with by "+by +"</b>"+" was not present", takeSnapShot());			
		return null;
		}		
	}
	
	public List<WebElement> getElements(By by, int time)  {
		wait = new WebDriverWait(driver, time);
		try {
		List<WebElement> elements =wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;  
		jsExecutor.executeScript("arguments[0].scrollIntoView()", elements.get(0));
		jsExecutor.executeScript("arguments[0].style.border='2px solid red'", elements.get(0));   		
//		Reporter.report("Pass",elements +" should be present", elementName +" was present", takeSnapShot());		
		jsExecutor.executeScript("arguments[0].style.border='0px solid red'", elements.get(0));  
		return elements;
		}catch (TimeoutException e){
			failTestInReportAndContinue("<b> elements with by "+by +"</b>"+" should be present", "<b> elements with by "+by +"</b>"+" was not present", takeSnapShot());			
		return null;
		}		
	}
	
	public void CompareString(String actual,String expected,String comment)  {
		System.out.println(" Validation: "+comment+" Actual: "+actual+" Expected: "+expected);
		if(actual.equals(expected)) {
			addReportComparision("Pass",comment+" should be <b>\""+ expected+"</b>\"",comment+" was <b>\""+ actual+"\"</b>");
		}else {
			failTestInReportAndContinue(comment+" should be <b>\""+ expected+"</b>\"",comment+" should have been "+expected+" but was <b>\""+ actual+"\"</b>", snapPath);
		}	
	}
	
	public void CompareStringInArray(String actual,String expected,String comment)  {
		System.out.println(" Validation: "+comment+" Actual: "+actual+" Expected: "+expected);
		if(actual.equals(expected)) {
			ReportComparision("Pass",comment+" should be <b>\""+ expected+"</b>\"",comment+" was <b>\""+ actual+"\"</b>");
		}else {
//			ReportComparision("Fail",comment+" should be <b>\""+ expected+"\"",comment+" was \""+ actual+"\"");
			failTestInReportAndContinue(comment+" should be <b>\""+ expected+"</b>\"",comment+" should have been "+expected+" but was <b>\""+ actual+"\"</b>", takeSnapShot());
		}	
	}
		
	}


