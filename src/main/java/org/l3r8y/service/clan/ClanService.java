package org.l3r8y.service.clan;

import org.l3r8y.entity.Clan;

public interface ClanService {
  Clan get(long clanId);

  boolean save(Clan clan);

  void addGoldById(long clanId, int gold);
}
