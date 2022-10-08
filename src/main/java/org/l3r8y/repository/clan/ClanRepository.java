package org.l3r8y.repository.clan;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.l3r8y.datasource.DataSource;
import org.l3r8y.entity.Clan;
import org.l3r8y.exception.ClanNotFoundException;
import org.l3r8y.traking.DatabasedGoldChange;

public class ClanRepository {

  private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

  private static void errorLog(final Exception ex) {
    LOGGER.log(Level.WARNING, ex.getMessage());
  }

  public boolean save(final Clan clan) {
    final String query =
        String.format(
            "INSERT INTO clan (name, gold) VALUES ('%s', '%d');", clan.getName(), clan.getGold()
        );
    try (final Connection conn = this.connection()) {
      conn.createStatement().executeUpdate(query);
      return true;
    } catch (final SQLException ex) {
      errorLog(ex);
      return false;
    }
  }

  public Optional<Clan> clanByName(final String name) {
    return this.clanBy("name", name);
  }

  public Optional<Clan> clanById(final long id) {
    return this.clanBy("id", String.valueOf(id));
  }

  public void addGoldToClan(final long clanId, final int gold) throws ClanNotFoundException {
    final Clan clan =
        this.clanById(clanId).orElseThrow(ClanNotFoundException::new);
    final int before = clan.getGold();
    final int after = before + gold;
    new DatabasedGoldChange(clanId, before, after, "Just increasing").toDatabase();
    final String query = String.format("UPDATE clan SET gold=%d WHERE id=%d", after, clanId);
    try (final Connection conn = this.connection()) {
      conn.createStatement().executeUpdate(query);
    } catch (final SQLException ex) {
      LOGGER.log(Level.WARNING, "Gold doesn't added:".concat(ex.getMessage()));
    }
  }

  private Optional<Clan> clanBy(final String field, final String value) {
    final Clan clan;
    final String query = String.format("SELECT * FROM clan WHERE %s='%s';", field, value);
    try (final Connection conn = this.connection()) {
      final ResultSet set = conn.createStatement().executeQuery(query);
      clan = this.mapSetToClan(set);
    } catch (final SQLException ex) {
      errorLog(ex);
      return Optional.empty();
    }
    return Optional.ofNullable(clan);
  }

  private Connection connection() throws SQLException {
    return DataSource.connection();
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
}
