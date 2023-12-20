package com.vimacodes.aoc.day20;

import com.vimacodes.aoc.day20.ModuleConfiguration.ModuleInstruction;
import java.util.List;
import lombok.ToString;

@ToString(callSuper = true)
public class FlipFlop extends Module {

  boolean isOn = false;

  public FlipFlop(String id, List<String> destinations) {
    super(id, destinations);
  }

  @Override
  public ModuleInstruction send(String senderModule, boolean pulse) {
    printFlow(senderModule, pulse, getId());

    if (pulse == ModuleConfiguration.HIGH_PULSE) {
      return ModuleInstruction.EMPTY;
    }

    isOn = !isOn;
    return new ModuleInstruction(getId(), isOn, getDestinations());
  }
}
