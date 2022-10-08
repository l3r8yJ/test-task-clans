package org.l3r8y.traking;

public class GoldChange {
  protected long id;
  protected final long clanId;
  protected final int before;
  protected final int after;

  public GoldChange(final long clanId, final int before, final int after) {
    this.clanId = clanId;
    this.before = before;
    this.after = after;
  }
}
