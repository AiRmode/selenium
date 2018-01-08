package org.openqa.selenium.remote.server;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.TemporaryFilesystem;
import org.openqa.selenium.remote.Dialect;
import org.openqa.selenium.remote.SessionId;
import org.openqa.selenium.remote.http.HttpRequest;
import org.openqa.selenium.remote.http.HttpResponse;
import org.openqa.selenium.remote.service.DriverService;

import java.io.IOException;
import java.util.Map;

public class DtrmServicedSession implements ActiveSession {
  private final DriverService service;
  private final Dialect downstreamDialect;
  private final Dialect upstreamDialect;
  private final SessionCodec codec;
  private final SessionId id;
  private final Map<String, Object> capabilities;
  private final ServicedSession servicedSession;
  private final TemporaryFilesystem filesystem;

  public DtrmServicedSession(ServicedSession servicedSession) {
    this.servicedSession = servicedSession;
    this.service = servicedSession.getService();
    this.downstreamDialect = servicedSession.getDownstreamDialect();
    this.upstreamDialect = servicedSession.getUpstreamDialect();
    this.codec = servicedSession.getCodec();
    this.id = servicedSession.getId();
    this.capabilities = servicedSession.getCapabilities();
    this.filesystem = servicedSession.getFileSystem();
  }

  @Override
  public void execute(HttpRequest req, HttpResponse resp) throws IOException {
    new DtrmLogin(servicedSession.getWrappedDriver()).execute(req,resp);
  }

  @Override
  public SessionId getId() {
    return id;
  }

  @Override
  public Dialect getUpstreamDialect() {
    return upstreamDialect;
  }

  @Override
  public Dialect getDownstreamDialect() {
    return downstreamDialect;
  }

  @Override
  public Map<String, Object> getCapabilities() {
    return capabilities;
  }

  @Override
  public TemporaryFilesystem getFileSystem() {
    return filesystem;
  }

  @Override
  public void stop() {
    servicedSession.stop();
  }

  @Override
  public WebDriver getWrappedDriver() {
    return servicedSession.getWrappedDriver();
  }

}
