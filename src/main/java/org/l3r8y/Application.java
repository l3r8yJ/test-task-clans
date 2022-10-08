package org.l3r8y;

import org.l3r8y.exception.ClanNotFoundException;
import org.l3r8y.repository.clan.ClanRepository;
import org.l3r8y.service.clan.DefaultClanService;

public class Application {
  public static void main(final String[] args) throws ClanNotFoundException {
    final DefaultClanService service = new DefaultClanService(new ClanRepository());
    System.out.println(service.get(10));
    service.addGoldById(10, 100);
    System.out.println(service.get(10));
  }
}
