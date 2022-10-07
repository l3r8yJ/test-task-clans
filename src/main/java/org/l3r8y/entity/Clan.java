package org.l3r8y.entity;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Clan {
  long id;
  String name;
  int gold;
}
