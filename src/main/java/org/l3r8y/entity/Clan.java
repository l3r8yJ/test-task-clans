package org.l3r8y.entity;

import com.ibm.db2.cmx.annotation.Id;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

@Value
@Builder
public class Clan {
  @Id long id;
  @NonNull String name;
  int gold;
}
