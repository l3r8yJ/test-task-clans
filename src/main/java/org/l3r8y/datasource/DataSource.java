package org.l3r8y.datasource;

import java.sql.Connection;
import java.sql.SQLException;
import org.apache.commons.dbcp.BasicDataSource;

public class DataSource {
  private static final BasicDataSource src = new BasicDataSource();

  static {
    try {
      Class.forName("com.mysql.jdbc.Driver");
    } catch (final ClassNotFoundException ex) {
      throw new IllegalStateException(ex);
    }
    src.setUrl(DBConfig.config().getProperty("URL"));
    src.setUsername(DBConfig.config().getProperty("USER"));
    src.setPassword(DBConfig.config().getProperty("PASSWORD"));
    src.setMinIdle(5);
    src.setMaxIdle(10);
    src.setMaxOpenPreparedStatements(100);
  }

  public static Connection connection() throws SQLException {
    return src.getConnection();
  }

  private DataSource(){ }
}
