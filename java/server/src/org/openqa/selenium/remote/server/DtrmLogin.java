package org.openqa.selenium.remote.server;

import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.json.Json;
import org.openqa.selenium.remote.http.HttpRequest;
import org.openqa.selenium.remote.http.HttpResponse;

import java.io.IOException;
import java.util.HashMap;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class DtrmLogin implements CommandHandler {
  private final WebDriver wrappedDriver;

  public DtrmLogin(WebDriver webDriver) {
    this.wrappedDriver = webDriver;

    WebDriverRunner.setWebDriver(webDriver);
  }

  @Override
  public void execute(HttpRequest req, HttpResponse resp) throws IOException {
    String content = req.getContentString();
    Json j = new Json();
    HashMap<?, ?> hashMap = j.toType(content, HashMap.class);
    wrappedDriver.get(hashMap.get("url").toString());

    forceLoginAs(hashMap.get("login").toString(), hashMap.get("password").toString());
  }

  public void forceLoginAs(String username, String password) {
    By loginNameInput = By.id("user");
    By passwordInput = By.id("password");
    By loginButton = By.id("form_login");
    By confirmLoginButton = By.id("enter");

    $(loginNameInput).shouldBe(visible).setValue(username);
    $(passwordInput).shouldBe(visible).setValue(password);
    $(loginButton).shouldBe(visible).click();
    $(loginButton).shouldNot(exist);

    if ($(confirmLoginButton).exists())
      $(confirmLoginButton).shouldBe(visible).click();
  }
}
