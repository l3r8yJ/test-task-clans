package org.l3r8y.service.task;

import org.l3r8y.service.clan.ClanService;

public class TaskService {

  private final ClanService clans;

  public TaskService(final ClanService clans) {
    this.clans = clans;
  }

  void completeTask(long clanId, long taskId) {
    // TODO: if success add gold and etc.
  }
}
