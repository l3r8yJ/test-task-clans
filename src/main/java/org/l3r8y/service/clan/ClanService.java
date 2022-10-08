package org.l3r8y.service.clan;

import org.l3r8y.entity.Clan;
import org.l3r8y.exception.ClanNotFoundException;

public interface ClanService {
  Clan get(long clanId) throws ClanNotFoundException;

  boolean save(Clan clan);

  void addGoldById(long clanId, int gold) throws ClanNotFoundException;
}
