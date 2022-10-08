package org.l3r8y.traking;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.l3r8y.datasource.DataSource;

public class DatabasedGoldChange extends GoldChange {

  private final String reason;
  private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

  public DatabasedGoldChange(final long clanId, final int before, final int after, final String reason) {
    super(clanId, before, after);
    this.reason = reason;
  }

  public void toDatabase() {
    final String msg = String.format(
        "Gold change in clan %d, before: %d, after: %d, reason: %s",
        this.clanId,
        this.before,
        this.after,
        this.reason
    );
    try (final Connection conn = DataSource.connection()) {
      final String query = String.format(
          "INSERT INTO gold_changes (`clan_id`, `before`, `after`, `msg`) VALUES (%d, %d, %d, '%s');",
          this.clanId, this.before, this.after, msg
      );
      conn.createStatement().executeUpdate(query);
    } catch (final SQLException e) {
      throw new RuntimeException(e);
    }
    LOGGER.log(Level.INFO, msg);
  }
}
