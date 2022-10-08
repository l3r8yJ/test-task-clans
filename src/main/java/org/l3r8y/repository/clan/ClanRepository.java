package org.l3r8y.repository.clan;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.l3r8y.entity.Clan;
import org.l3r8y.exception.ClanNotFoundException;

public class ClanRepository {

  private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

  private static void errorLog(final Exception ex) {
    LOGGER.log(Level.WARNING, ex.getMessage());
  }

  public boolean save(final Clan clan) throws SQLException {
    final String query = String.format("INSERT INTO clan (name) VALUES ('%s');", clan.getName());
    try (final Connection conn = this.connection()) {
      conn.createStatement().executeUpdate(query);
      return true;
    }
  }

  public Optional<Clan> clanByName(final String name) throws SQLException {
    return this.clanBy("name", name);
  }

  public Optional<Clan> clanById(final long id) throws SQLException {
    return this.clanBy("id", String.valueOf(id));
  }

  public void addGoldToClan(final long clanId, final int gold)
      throws SQLException, ClanNotFoundException {
    final Clan clan;
    clan = this.clanBy("id", String.valueOf(clanId)).orElseThrow(ClanNotFoundException::new);
    final int updated = clan.getGold() + gold;
    final String query = String.format("UPDATE clan SET gold=%d WHERE id=%d", updated, clanId);
    try (final Connection conn = this.connection()) {
      conn.createStatement().executeUpdate(query);
    }
  }

  private Optional<Clan> clanBy(final String field, final String value) throws SQLException {
    final Clan clan;
    final String query = String.format("SELECT * FROM clan WHERE %s='%s';", field, value);
    try (final Connection conn = this.connection()) {
      final ResultSet set = conn.createStatement().executeQuery(query);
      clan = this.mapSetToClan(set);
    }
    return Optional.ofNullable(clan);
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

  private Clan mapSetToClan(final ResultSet set) throws SQLException {
    Clan clan = null;
    while (set.next()) {
      clan =
          Clan.builder()
              .id(set.getLong("id"))
              .name(set.getString("name"))
              .gold(set.getInt("gold"))
              .build();
    }
    return clan;
  }

  private Properties config() throws IOException {
    final Properties prop = new Properties();
    prop.load(new FileInputStream("src/main/resources/connection.properties"));
    return prop;
  }
}
