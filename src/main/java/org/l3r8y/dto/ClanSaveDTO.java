package org.l3r8y.dto;


public record ClanSaveDTO(String name) {

  public ClanSaveDTO {
    if (null == name || name.isEmpty()) {
      throw new IllegalStateException("Clan name can't be empty!");
    }
  }
}
