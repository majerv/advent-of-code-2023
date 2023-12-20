package com.vimacodes.aoc.day20;

import java.util.*;
import lombok.ToString;

@ToString(callSuper = true)
public class Conjunction extends Module {

  Map<String, Boolean> memory;
  Set<String> highs;

  public Conjunction(String id, List<String> destinations) {
    super(id, destinations);
    memory = new HashMap<>();
    highs = new HashSet<>();
  }

  @Override
  public ModuleConfiguration.ModuleInstruction send(String senderModule, boolean pulse) {
    //    printFlow(senderModule, pulse, getId());

    memory.put(senderModule, pulse);
    if (pulse) highs.add(senderModule);

    // should remember all
    boolean hasLow = memory.values().stream().anyMatch(v -> !v);
    return new ModuleConfiguration.ModuleInstruction(getId(), hasLow, getDestinations());
  }

  @Override
  public void registerInput(String inputModule) {
    memory.putIfAbsent(inputModule, ModuleConfiguration.LOW_PULSE);
    //    System.out.printf("Module: %s registering input: %s\n", getId(), inputModule);
  }

  @Override
  public void reset() {
    memory.forEach((k, v) -> memory.put(k, false));
    highs.clear();
  }

  @Override
  public boolean hasSent(String sender, boolean highPulse) {
    return highs.contains(sender);
  }
}
