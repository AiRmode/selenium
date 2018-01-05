package org.openqa.selenium.remote.server.dtrm;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class DtrmTest {
  public static void execute() throws MalformedURLException {
    final DesiredCapabilities capability = DesiredCapabilities.chrome();
    WebDriver driver = new RemoteWebDriver(
      new URL("http://localhost:4444/wd/hub"), capability
    );
    driver.get("http://www.javacodegeeks.com/");
    WebElement element = driver.findElement(By.name("s"));
    element.sendKeys("selenuim");
    element.submit();
    assertThat(
      driver.getTitle(),
      is("You searched for selenuim | Java Code Geeks")
    );
    driver.quit();
  }
}
