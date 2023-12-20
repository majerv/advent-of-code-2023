package com.vimacodes.aoc.day20;

import com.vimacodes.aoc.day20.ModuleConfiguration.ModuleInstruction;
import java.util.List;
import lombok.ToString;

@ToString(callSuper = true)
public class BroadCaster extends Module {
  public BroadCaster(String id, List<String> destinations) {
    super(id, destinations, null);
  }

  @Override
  public ModuleInstruction send(String senderModule, boolean pulse) {
    lastPulse = pulse;
    //    printFlow(senderModule, pulse, getId());
    return new ModuleInstruction(getId(), pulse, getDestinations());
  }

  @Override
  public void reset() {
    lastPulse = null;
  }
}
