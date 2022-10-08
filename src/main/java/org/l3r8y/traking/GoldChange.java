package org.l3r8y.traking;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import lombok.Builder;
import lombok.Value;
import org.l3r8y.datasource.DataSource;

@Builder
@Value
public class GoldChange {

  private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
  long id;
  long clanId;
  long taskId;
  int before;
  int after;
  String reason;

  public void toDatabase() {
    try (final Connection conn = DataSource.connection()) {
      final String query =
          String.format(
              "INSERT INTO gold_changes (`clan_id`, `task_id`, `before`, `after`, `msg`) VALUES (%d, %d, %d, %d, '%s');",
              this.clanId, this.taskId, this.before, this.after, this.reason);
      conn.createStatement().executeUpdate(query);
    } catch (final SQLException ex) {
      throw new IllegalStateException(ex);
    }
    LOGGER.log(
        Level.INFO,
        String.format(
            "Gold change in clan %d, before: %d, after: %d, reason: %s",
            this.clanId, this.before, this.after, this.reason)
    );
  }
}
