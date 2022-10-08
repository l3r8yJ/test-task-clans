package org.l3r8y.exception;

public class ClanAlreadyExistException extends RuntimeException {

  public ClanAlreadyExistException(final String name) {
    super(String.format("Clan %s already exist!", name));
  }
}
