package org.l3r8y;

import org.l3r8y.exception.ClanNotFoundException;
import org.l3r8y.exception.TaskNotFoundException;
import org.l3r8y.repository.clan.ClanRepository;
import org.l3r8y.repository.task.TaskRepository;
import org.l3r8y.service.clan.DefaultClanService;
import org.l3r8y.service.task.TaskService;

public class Application {
  public static void main(final String[] args) throws ClanNotFoundException, TaskNotFoundException {
    final DefaultClanService service = new DefaultClanService(new ClanRepository());
    final TaskService taskService = new TaskService(service, new TaskRepository());
    taskService.completeTask(1, 1);
  }
}
