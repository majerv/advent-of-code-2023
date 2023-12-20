package com.vimacodes.aoc.day20;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.ToString;

@ToString(callSuper = true)
public class Conjunction extends Module {

  Map<String, Boolean> memory;

  public Conjunction(String id, List<String> destinations) {
    super(id, destinations);
    memory = new HashMap<>();
  }

  @Override
  public ModuleConfiguration.ModuleInstruction send(String senderModule, boolean pulse) {
    printFlow(senderModule, pulse, getId());

    memory.put(senderModule, pulse);

    // should remember all
    boolean hasLow = memory.values().stream().anyMatch(v -> !v);
    return new ModuleConfiguration.ModuleInstruction(getId(), hasLow, getDestinations());
  }

  @Override
  public void registerInput(String inputModule) {
    memory.putIfAbsent(inputModule, ModuleConfiguration.LOW_PULSE);
    System.out.printf("Module: %s registering input: %s\n", getId(), inputModule);
  }
}
