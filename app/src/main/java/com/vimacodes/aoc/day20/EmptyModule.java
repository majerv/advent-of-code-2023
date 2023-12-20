package com.vimacodes.aoc.day20;

import com.vimacodes.aoc.day20.ModuleConfiguration.ModuleInstruction;
import java.util.Collections;
import lombok.ToString;

@ToString(callSuper = true)
class EmptyModule extends Module {

  public EmptyModule(String id) {
    super(id, Collections.emptyList());
  }

  @Override
  public ModuleInstruction send(String senderModule, boolean pulse) {
    printFlow(senderModule, pulse, getId());
    return new ModuleInstruction(getId(), pulse, getDestinations());
  }
}
