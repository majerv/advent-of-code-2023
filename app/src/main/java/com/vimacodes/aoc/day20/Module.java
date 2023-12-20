package com.vimacodes.aoc.day20;

import com.vimacodes.aoc.day20.ModuleConfiguration.ModuleInstruction;
import java.util.Arrays;
import java.util.List;
import lombok.Value;
import lombok.experimental.NonFinal;

@Value
@NonFinal
abstract class Module {

  public static final String BROADCASTER_NAME = "broadcaster";
  public static final String BUTTON_NAME = "button";

  String id;
  List<String> destinations;

  public static Module parse(String line) {
    String[] parts = line.split("->");
    String id = parts[0].trim().replaceAll("[%&]", "");

    char type = parts[0].charAt(0);

    List<String> destinations =
        Arrays.stream(parts[1].trim().split(",")).map(String::trim).toList();

    if (id.equals(BROADCASTER_NAME)) return new BroadCaster(id, destinations);
    else if (type == '%') return new FlipFlop(id, destinations);
    else if (type == '&') return new Conjunction(id, destinations);

    throw new IllegalArgumentException("Unknown module type: " + type);
  }

  public static Module emptyModule(String id) {
    return new EmptyModule(id);
  }

  public abstract ModuleInstruction send(String senderModule, boolean pulse);

  public void registerInput(String inputModule) {
    // do nothing in most of the cases
  }

  public static void printFlow(String senderModule, boolean pulse, String id) {
    //    System.out.printf("%s -%s-> %s\n", senderModule, pulse ? "high" : "low", id);
  }
}
