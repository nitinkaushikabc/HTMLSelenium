package com.rsystems.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.relevantcodes.extentreports.LogStatus;
import com.rsystems.config.ObjectRepository;
import com.rsystems.utils.TestInitization;
import com.rsystems.utils.Unicode;

public class HotKeysNavigation extends TestInitization {

	WebDriver driver;

	public HotKeysNavigation(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(how = How.ID, using = ObjectRepository.DtvChannel.chnlNoIn_Infobar)
	public WebElement chnlNoIn_Infobar;

	@FindBy(how = How.CLASS_NAME, using = ObjectRepository.DtvChannel.programDurationIn_Infobar)
	public WebElement programDurationIn_Infobar;

	@FindBy(how = How.CLASS_NAME, using = ObjectRepository.DtvChannel.programTitle)
	public WebElement programTitle;

	@FindBy(how = How.CLASS_NAME, using = ObjectRepository.DtvChannel.channelLogo)
	public WebElement channelLogo;

	@FindBy(how = How.ID, using = ObjectRepository.DtvChannel.hdIcon)
	public WebElement hdIcon;

	public void navigateToDTvFromShopScreen() throws InterruptedException {
		reports.log(LogStatus.PASS, "Navigate to the Shop Screen");
		TestInitization.sendKeyMultipleTimes("DOWN", 1, 1000);
		TestInitization.sendKeyMultipleTimes("RIGHT", 1, 1000);
		TestInitization.sendKeyMultipleTimes("ENTER", 1, 1000);
		reports.attachScreenshot(captureCurrentScreenshot());
	}

	public void navigateToDtvScreenFromEpgScreen() throws InterruptedException {
		EpgScreen epg = new EpgScreen(driver);
		epg.goToEpgChannelScreen(true);
	}

	public void navigateToRadioScreen() throws InterruptedException {
		reports.log(LogStatus.PASS, "Returning to the Hub Screen");
		TestInitization.setApplicationHubPage(2);
		DTVChannelScreen dtvChannelScreen = new DTVChannelScreen(driver);
		reports.log(LogStatus.PASS, "Navigate to the Radio Screen");
		TestInitization.sendUnicodeMultipleTimes(Unicode.VK_RADIO.toString(), 2, 0);
		reports.attachScreenshot(captureCurrentScreenshot());
		TestInitization.sendUnicodeMultipleTimes(Unicode.VK_INFO.toString(), 2, 0);
		driver.switchTo().frame(getCurrentFrameIndex());
		int radioChannel = Integer.parseInt(dtvChannelScreen.chnlNoIn_Infobar.getText());
		if (radioChannel == Integer.parseInt(getExcelKeyValue("DTVChannel", "RadioChannel", "Values"))) {
			reports.log(LogStatus.PASS, "Radio channel Number : " + radioChannel + " has been reached ");
			reports.attachScreenshot(captureCurrentScreenshot());
		} else {
			FailTestCase("Radio channel Number : " + radioChannel + " has not been reached");

		}
		TestInitization.sendUnicodeMultipleTimes(Unicode.VK_INFO.toString(), 1, 0);
		TestInitization.sendUnicodeMultipleTimes(Unicode.VK_INFO.toString(), 1, 0);
		driver.switchTo().frame(getCurrentFrameIndex());
		TestInitization.sendUnicodeMultipleTimes(Unicode.VK_INFO.toString(), 1, 0);
		isDisplayed(dtvChannelScreen.programTitle, "Program Title");

	}

	public String openRadioButtonAndValidate(int radioButtonPressCount) throws InterruptedException {

		DTVChannelScreen dtvChannelScreen = new DTVChannelScreen(driver);
		
		// first move to TV channel than radion screen
		sendNumaricKeys(1);
		Thread.sleep(5000);
		
		reports.log(LogStatus.PASS, "Navigate to the Radio Screen");
		TestInitization.sendUnicodeMultipleTimes(Unicode.VK_RADIO.toString(), radioButtonPressCount, 0);
		reports.attachScreenshot(captureCurrentScreenshot());
		TestInitization.sendUnicodeMultipleTimes(Unicode.VK_INFO.toString(), 2, 0);
		driver.switchTo().frame(getCurrentFrameIndex());
		int radioChannel = Integer.parseInt(dtvChannelScreen.chnlNoIn_Infobar.getText());
		if (radioChannel == Integer.parseInt(getExcelKeyValue("DTVChannel", "RadioChannel", "Values"))) {
			reports.log(LogStatus.PASS, "Radio channel Number : " + radioChannel + " has been reached ");
			reports.attachScreenshot(captureCurrentScreenshot());
		} else {
			FailTestCase("Radio channel Number : " + radioChannel + " has not been reached");

		}
		TestInitization.sendUnicodeMultipleTimes(Unicode.VK_INFO.toString(), 1, 0);
		return dtvChannelScreen.programDurationIn_Infobar.getText();
	}

	public void pressUnicodeAndValidateChannelNumber(Unicode unicode, String ChannelProgramDurationToBeValidate,
			String buttonName) throws InterruptedException {

		
		reports.log(LogStatus.PASS, "Press " + buttonName);
		sendUnicodeMultipleTimes(unicode.toString(), 1, 2000);
		reports.attachScreenshot(captureCurrentScreenshot());

		TestInitization.sendUnicodeMultipleTimes(Unicode.VK_INFO.toString(), 2, 0);
		driver.switchTo().frame(getCurrentFrameIndex());
		TestInitization.sendUnicodeMultipleTimes(Unicode.VK_INFO.toString(), 2, 0);
		
		String currentChannelProgramDuration = programDurationIn_Infobar.getText();
		if (currentChannelProgramDuration.contentEquals(ChannelProgramDurationToBeValidate)) {
			reports.log(LogStatus.PASS, "Actual proram Duration" + currentChannelProgramDuration + " Expected program duration " + ChannelProgramDurationToBeValidate);
			TestInitization.sendUnicodeMultipleTimes(Unicode.VK_INFO.toString(), 1, 0);
			reports.attachScreenshot(captureCurrentScreenshot());
		} else {
			FailTestCase("Unable to tuned expected channel number " + " Actual Channel program duration  " + currentChannelProgramDuration
					+ " Expected channel program duration " + ChannelProgramDurationToBeValidate);

		}

	}

	public void setRadioChannel() throws InterruptedException{
		// set radio channel to 800 because radio channel is not set on 800 than conflict the testing condtion 
		TestInitization.sendUnicodeMultipleTimes(Unicode.VK_RADIO.toString(), 1, 2000);
		sendNumaricKeys(Integer.parseInt(getExcelKeyValue("DTVChannel", "RadioChannel", "Values")));	
	}
	public String getLastTunedRadioChannelProgramDetails() throws InterruptedException {

		DTVChannelScreen dtvChannelScreen = new DTVChannelScreen(driver);
		TestInitization.sendUnicodeMultipleTimes(Unicode.VK_RADIO.toString(), 1, 0);
		TestInitization.sendUnicodeMultipleTimes(Unicode.VK_INFO.toString(), 2, 0);
		driver.switchTo().frame(getCurrentFrameIndex());
		return dtvChannelScreen.programDurationIn_Infobar.getText();
	}
}
