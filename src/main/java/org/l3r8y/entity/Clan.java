package org.l3r8y.entity;

import com.ibm.db2.cmx.annotation.Id;
import java.io.Serializable;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

@Value
@Builder
public class Clan implements Serializable {
  @Id long id;
  @NonNull String name;
  int gold;
}
