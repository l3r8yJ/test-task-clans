package org.l3r8y.datasource;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;
import org.apache.commons.dbcp.BasicDataSource;

public class DBCPDataSource {
  private static final BasicDataSource ds = new BasicDataSource();

  static {
    try {
      Class.forName("com.mysql.jdbc.Driver");
    } catch (final ClassNotFoundException ex) {
      throw new IllegalStateException(ex);
    }
    ds.setUrl(config().getProperty("URL"));
    ds.setUsername(config().getProperty("USER"));
    ds.setPassword(config().getProperty("PASSWORD"));
    ds.setMinIdle(5);
    ds.setMaxIdle(10);
    ds.setMaxOpenPreparedStatements(100);
  }

  public static Connection connection() throws SQLException {
    return ds.getConnection();
  }

  private DBCPDataSource(){ }

  private static Properties config() {
    final Properties prop = new Properties();
    try {
      prop.load(new FileInputStream("src/main/resources/connection.properties"));
    } catch (final IOException ex) {
      throw new IllegalStateException(ex);
    }
    return prop;
  }
}
