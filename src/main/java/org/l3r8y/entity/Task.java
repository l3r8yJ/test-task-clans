package org.l3r8y.entity;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Task {
  long id;
  int gold;
  String description;
}
