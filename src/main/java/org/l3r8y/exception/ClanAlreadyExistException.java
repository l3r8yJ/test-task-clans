package org.l3r8y.exception;

import org.l3r8y.entity.Clan;

public class ClanAlreadyExistException extends RuntimeException {

  public ClanAlreadyExistException(final String name) {
    super(String.format("Clan %s already exist!", name));
  }

  public ClanAlreadyExistException(final Clan clan) {
    super(String.format("Clan %s already exist!", clan.getName()));
  }
}
