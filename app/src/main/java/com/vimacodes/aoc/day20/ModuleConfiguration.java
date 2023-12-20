package com.vimacodes.aoc.day20;

import static com.vimacodes.aoc.day20.Module.BUTTON_NAME;

import java.util.*;
import java.util.stream.Collectors;
import lombok.Value;

@Value
class ModuleConfiguration {

  public static final boolean LOW_PULSE = false;
  public static final boolean HIGH_PULSE = true;

  private static final double MAX_LOOPS = 10_000;

  private static final ModuleInstruction START_INSTRUCTION =
      new ModuleInstruction(BUTTON_NAME, LOW_PULSE, List.of(Module.BROADCASTER_NAME));

  Map<String, Module> modules;

  public static ModuleConfiguration parse(String text) {
    Map<String, Module> modules =
        text.lines().map(Module::parse).collect(Collectors.toMap(Module::getId, m -> m));

    // register inputs for conjunctions
    Set<String> conjunctions =
        modules.values().stream()
            .filter(m -> m instanceof Conjunction)
            .map(Module::getId)
            .collect(Collectors.toSet());

    for (var module : modules.values()) {
      for (var dest : module.getDestinations()) {
        if (conjunctions.contains(dest)) {
          modules.get(dest).registerInput(module.getId());
        }
      }
    }

    return new ModuleConfiguration(modules);
  }

  public Result pushButton(int times) {
    long lowPulses = 0, highPulses = 0;

    for (int i = 0; i < times; i++) {
      var result = pushButton();
      lowPulses += result.lowPulses;
      highPulses += result.highPulses;
    }

    return new Result(lowPulses, highPulses);
  }

  private Result pushButton() {
    long lowPulses = 0, highPulses = 0;

    Deque<ModuleInstruction> instructions = new LinkedList<>();
    instructions.add(START_INSTRUCTION);

    while (!instructions.isEmpty()) {
      ModuleInstruction inst = instructions.removeFirst();

      if (inst.pulse) highPulses += inst.destinations.size();
      else lowPulses += inst.destinations.size();

      for (var id : inst.destinations) {
        Module module = getModuleOrEmpty(id);
        ModuleInstruction newInst = module.send(inst.senderModule, inst.pulse);
        instructions.add(newInst);
      }
    }

    return new Result(lowPulses, highPulses);
  }

  private Module getModuleOrEmpty(String id) {
    modules.putIfAbsent(id, Module.emptyModule(id));
    return modules.get(id);
  }

  public long getPressesTilHighPulse(String sender, String destination) {
    reset();

    boolean found = false;
    long counter = 0;
    while (!found && counter < MAX_LOOPS) {
      ++counter;
      pushButton(1);
      found = getModuleOrEmpty(destination).hasSent(sender, HIGH_PULSE);
    }

    return found ? counter : -1;
  }

  private void reset() {
    modules.forEach((k, v) -> v.reset());
  }

  public record Result(long lowPulses, long highPulses) {}

  public record ModuleInstruction(String senderModule, boolean pulse, List<String> destinations) {
    public static final ModuleInstruction EMPTY =
        new ModuleInstruction("", LOW_PULSE, Collections.emptyList());
  }
}
