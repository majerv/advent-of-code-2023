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

    // create final destinations and register inputs for all modules
    Map<String, Module> modules2nd = new HashMap<>(modules);

    for (var module : modules.values()) {
      for (var dest : module.getDestinations()) {
        Module moduleToUpdate = getModuleOrCreateEmpty(modules2nd, dest);
        moduleToUpdate.registerInput(module.id);
        modules2nd.put(moduleToUpdate.id, moduleToUpdate);
      }
    }

    return new ModuleConfiguration(modules2nd);
  }

  private static Module getModuleOrCreateEmpty(Map<String, Module> modules, String id) {
    return modules.containsKey(id) ? modules.get(id) : Module.emptyModule(id);
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
        Module module = modules.get(id);
        ModuleInstruction newInst = module.send(inst.senderModule, inst.pulse);
        instructions.add(newInst);
      }
    }

    return new Result(lowPulses, highPulses);
  }

  public long getPressesTilHighPulse(String sender, String destination) {
    reset();

    boolean found = false;
    long counter = 0;
    while (!found && counter < MAX_LOOPS) {
      ++counter;
      pushButton(1);
      found = modules.get(destination).hasSent(sender, HIGH_PULSE);
    }

    return found ? counter : 0;
  }

  private void reset() {
    modules.forEach((k, v) -> v.reset());
  }

  public List<SourceDest> findInputs(String destination) {
    return modules.get(destination).getInputs().stream()
        .map(s -> new SourceDest(s, destination))
        .toList();
  }

  public record Result(long lowPulses, long highPulses) {}

  public record SourceDest(String source, String destination) {}

  public record ModuleInstruction(String senderModule, boolean pulse, List<String> destinations) {
    public static final ModuleInstruction EMPTY =
        new ModuleInstruction("", LOW_PULSE, Collections.emptyList());
  }
}
