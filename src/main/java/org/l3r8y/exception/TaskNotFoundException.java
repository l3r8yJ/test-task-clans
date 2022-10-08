package org.l3r8y.exception;

public class TaskNotFoundException extends Exception{

  public TaskNotFoundException() {
    super("Task not found.");
  }
}
