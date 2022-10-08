package org.l3r8y.service.task;

import java.util.Random;
import org.l3r8y.entity.Task;
import org.l3r8y.exception.ClanNotFoundException;
import org.l3r8y.exception.TaskNotFoundException;
import org.l3r8y.repository.task.TaskRepository;
import org.l3r8y.service.clan.ClanService;
import org.l3r8y.traking.GoldChange;

public class TaskService {

  private final ClanService clans;
  private final TaskRepository tasks;

  public TaskService(final ClanService clans, final TaskRepository tasks) {
    this.clans = clans;
    this.tasks = tasks;
  }

  public void completeTask(final long clanId, final long taskId)
      throws TaskNotFoundException, ClanNotFoundException {
    final Task task = this.tasks.taskById(taskId).orElseThrow(TaskNotFoundException::new);
    // Emu of completion.
    final boolean success = new Random().nextBoolean();
    if (success) {
      this.clans.addGoldById(clanId, task.getGold());
      final int before = this.clans.get(clanId).getGold();
      GoldChange
          .builder()
          .taskId(taskId)
          .clanId(clanId)
          .before(before)
          .after(before + task.getGold())
          .reason("Task completion.")
          .build()
          .toDatabase();
    }
  }
}
