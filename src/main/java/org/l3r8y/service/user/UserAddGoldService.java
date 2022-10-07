package org.l3r8y.service.user;

import org.l3r8y.entity.Clan;
import org.l3r8y.service.clan.ClanService;

public class UserAddGoldService {

  private final ClanService clans;

  public UserAddGoldService(final ClanService clans) {
    this.clans = clans;
  }

  public void addGoldToClan(final long userId, final long clanId, final int gold) {
    final Clan clan = this.clans.get(clanId);
    // TODO: increase clan gold
    this.clans.save(clan);
  }
}
