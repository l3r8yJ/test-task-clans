package org.l3r8y.datasource;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class DBConfig {
  public static Properties config() {
    final Properties prop = new Properties();
    try {
      prop.load(new FileInputStream("src/main/resources/db.properties"));
    } catch (final IOException ex) {
      throw new IllegalStateException(ex);
    }
    return prop;
  }
}
