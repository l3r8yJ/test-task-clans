package org.l3r8y.service.user;

import org.l3r8y.entity.Clan;
import org.l3r8y.exception.ClanNotFoundException;
import org.l3r8y.service.clan.ClanService;

public class UserAddGoldService {

  private final ClanService clans;

  public UserAddGoldService(final ClanService clans) {
    this.clans = clans;
  }

  public void addGoldToClan(final long userId, final long clanId, final int gold)
      throws ClanNotFoundException {
    final Clan clan;
    clan = this.clans.get(10);
    this.clans.save(clan);
  }
}
