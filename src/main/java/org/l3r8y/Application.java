package org.l3r8y;

import org.l3r8y.repository.clan.ClanRepository;

public class Application {
  public static void main(final String[] args) {
    final ClanRepository repo = new ClanRepository();
    repo.clanByName("mew").ifPresent(System.out::println);
    repo.addGoldToClan(10, 100);
    repo.clanByName("mew").ifPresent(System.out::println);
  }
}
