package org.l3r8y.repository.clan;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.l3r8y.dto.ClanSaveDTO;
import org.l3r8y.exception.ClanAlreadyExistException;

public class ClanRepository {

  private final static Logger LOGGER =
      Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

  public boolean save(final ClanSaveDTO clan) {
    // TODO: check clan on already existence.
    final String query = String.format(
        "INSERT INTO clan (name) VALUES ('%s');",
        clan.name()
    );
    try (final Connection conn = this.connection()) {
      conn.createStatement().executeUpdate(query);
      return true;
    } catch (final SQLException ex) {
      errorLog(ex);
      throw new ClanAlreadyExistException(clan.name());
    }
  }

  private Connection connection() throws SQLException {
    final Connection connection;
    try {
      Class.forName("com.mysql.jdbc.Driver");
      connection =
          DriverManager.getConnection(
              this.config().getProperty("URL"),
              this.config().getProperty("USER"),
              this.config().getProperty("PASSWORD"));
    } catch (final IOException | ClassNotFoundException ex) {
      errorLog(ex);
      throw new IllegalStateException(ex);
    }
    return connection;
  }

  private Properties config() throws IOException {
    final Properties prop = new Properties();
    prop.load(new FileInputStream("src/main/resources/connection.properties"));
    return prop;
  }

  private static void errorLog(final Exception ex) {
    LOGGER.log(Level.WARNING, ex.getMessage());
  }
}
