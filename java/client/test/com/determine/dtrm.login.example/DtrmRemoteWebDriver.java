package com.determine.dtrm.login.example;

import org.openqa.selenium.remote.CommandInfo;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.HttpCommandExecutor;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;
import java.util.HashMap;

public class DtrmRemoteWebDriver extends RemoteWebDriver {

  public DtrmRemoteWebDriver(URL url, DesiredCapabilities capability, HashMap<String, CommandInfo> additionalCommands) {
    super(new HttpCommandExecutor(additionalCommands, url), capability);
  }

}
