package org.l3r8y.service.user;

import java.util.concurrent.atomic.AtomicInteger;
import org.l3r8y.entity.Clan;
import org.l3r8y.service.clan.ClanService;

public class UserAddGoldService {

  private final ClanService clans;

  public UserAddGoldService(final ClanService clans) {
    this.clans = clans;
  }

  public void addGoldToClan(long userId, long clanId, int gold) {
    Clan clan = clans.get(clanId);
    AtomicInteger temp = new AtomicInteger(clan.getGold());
    temp.getAndAdd(gold);
    clan.setGold(temp.get());
    clans.save(clan);
  }
}
