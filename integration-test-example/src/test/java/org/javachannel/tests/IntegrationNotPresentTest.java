package org.javachannel.tests;

import org.testng.annotations.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class IntegrationNotPresentTest {
  @Test(expectedExceptions = SQLException.class)
  public void testConnection() throws SQLException {
    // should fail because the database shouldn't start up for ordinary tests
    Connection connection = DriverManager.getConnection("jdbc:derby://localhost:1527/foo;create=true", "derby", "derby");
  }

}
