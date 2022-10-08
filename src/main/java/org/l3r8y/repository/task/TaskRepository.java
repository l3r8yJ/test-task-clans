package org.l3r8y.repository.task;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.l3r8y.datasource.DataSource;
import org.l3r8y.entity.Task;

public class TaskRepository {
  private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

  private static void errorLog(final Exception ex) {
    LOGGER.log(Level.WARNING, ex.getMessage());
  }

  public boolean save(final Task task) {
    final String query =
        String.format(
            "INSERT INTO task (name, gold) VALUES ('%d', '%s');",
            task.getGold(), task.getDescription());
    try (final Connection conn = this.connection()) {
      conn.createStatement().executeUpdate(query);
      return true;
    } catch (final SQLException ex) {
      errorLog(ex);
      return false;
    }
  }

  public Optional<Task> taskById(final long id) {
    return this.taskBy("id", String.valueOf(id));
  }

  private Optional<Task> taskBy(final String field, final String value) {
    final Task task;
    final String query = String.format("SELECT * FROM task WHERE %s='%s';", field, value);
    try (final Connection conn = this.connection()) {
      final ResultSet set = conn.createStatement().executeQuery(query);
      task = this.mapSetToTask(set);
    } catch (final SQLException ex) {
      errorLog(ex);
      return Optional.empty();
    }
    return Optional.ofNullable(task);
  }

  private Task mapSetToTask(final ResultSet set) throws SQLException {
    Task task = null;
    while (set.next()) {
      task =
          Task.builder()
              .id(set.getLong("id"))
              .gold(set.getInt("gold"))
              .description(set.getString("description"))
              .build();
    }
    return task;
  }

  private Connection connection() throws SQLException {
    return DataSource.connection();
  }
}
