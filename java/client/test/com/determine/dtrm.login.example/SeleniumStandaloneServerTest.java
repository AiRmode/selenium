package com.determine.dtrm.login.example;

import org.junit.Test;
import org.openqa.selenium.remote.Command;
import org.openqa.selenium.remote.CommandInfo;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.http.HttpMethod;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * Tests for selenium standalone server.
 *
 * @author parsentev
 * @since 19.11.2015
 */
public class SeleniumStandaloneServerTest {

	/*@Test
	public void executeFirefoxDriver() throws MalformedURLException {
		this.execute(DesiredCapabilities.firefox());
	}*/

  @Test
  public void executeChrome() throws MalformedURLException {
    this.execute(DesiredCapabilities.chrome());
  }

  private void execute(final DesiredCapabilities capability) throws MalformedURLException {
    Map<String, CommandInfo> additionalCommands = new HashMap<>();
    additionalCommands.put("dtrmLogin", new CommandInfo("/session/:sessionId/dtrmLogin", HttpMethod.POST));

    DtrmRemoteWebDriver driver = new DtrmRemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capability, additionalCommands);
    driver.get("http://www.javacodegeeks.com/");

    Map<String, String> loginAttributes = new HashMap<String, String>();
    loginAttributes.put("url", "https://framework.determine.com/d/");
    loginAttributes.put("login", "abondarenko@determine.com");
    loginAttributes.put("password", "Test906090");
    Command command = new Command(driver.getSessionId(), "dtrmLogin", loginAttributes);
    try {
      driver.getCommandExecutor().execute(command);
    } catch (IOException e) {
      e.printStackTrace();
    }
    sleep2Seconds();
    driver.get("https://google.com.ua");
    sleep2Seconds();
    driver.quit();
  }

  private void sleep2Seconds() {
    try {
      Thread.sleep(2500);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
