package org.l3r8y.service.clan;

import org.l3r8y.entity.Clan;
import org.l3r8y.exception.ClanAlreadyExistException;
import org.l3r8y.exception.ClanNotFoundException;
import org.l3r8y.repository.clan.ClanRepository;

public class DefaultClanService implements ClanService {

  final ClanRepository repository;

  public DefaultClanService(final ClanRepository repository) {
    this.repository = repository;
  }

  @Override
  public Clan get(final long clanId) throws ClanNotFoundException {
    return this.repository.clanById(clanId).orElseThrow(ClanNotFoundException::new);
  }

  @Override
  public boolean save(final Clan clan) {
    return this.repository.save(clan);
  }

  @Override
  public void addGoldById(final long clanId, final int gold) throws ClanNotFoundException {
    if (this.repository.clanById(clanId).isEmpty()) {
      throw new ClanNotFoundException();
    }
    this.repository.addGoldToClan(clanId, gold);
  }
}
