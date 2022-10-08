package org.l3r8y;

import org.l3r8y.dto.ClanSaveDTO;
import org.l3r8y.repository.clan.ClanRepository;

public class Application {
  public static void main(final String[] args) {
    final ClanSaveDTO c = new ClanSaveDTO("");
    if (new ClanRepository().save(c)) {
      System.out.println("Saved!");
    } else {
      System.out.println("Not saved!");
    }
  }
}
