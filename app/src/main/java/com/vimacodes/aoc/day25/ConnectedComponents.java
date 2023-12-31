package com.vimacodes.aoc.day25;

import com.google.common.base.Preconditions;
import java.util.*;
import java.util.stream.Collectors;
import lombok.Value;
import org.jgrapht.Graph;
import org.jgrapht.alg.StoerWagnerMinimumCut;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

@Value
class ConnectedComponents {

  Graph<String, DefaultEdge> components;

  //  Map<String, Set<String>> nodesToEdges;

  public static ConnectedComponents parse(String text) {
    Map<String, Set<String>> connections =
        text.lines()
            .map(ConnectedComponents::parseNodeToEdges)
            .collect(Collectors.toMap(p -> p.key, p -> p.value));

    Graph<String, DefaultEdge> components = new SimpleGraph<>(DefaultEdge.class);

    for (var entry : connections.entrySet()) {
      String node = entry.getKey();
      components.addVertex(node);

      for (var otherNode : entry.getValue()) {
        components.addVertex(otherNode);
        components.addEdge(node, otherNode);
      }
    }

    return new ConnectedComponents(components);
  }

  private static Pair<String, Set<String>> parseNodeToEdges(String line) {
    String[] parts = line.split(":");
    return new Pair<>(
        parts[0].trim(), Arrays.stream(parts[1].trim().split("\\s")).collect(Collectors.toSet()));
  }

  public long size() {
    return components.vertexSet().size();
  }

  public long cutInto2WithExactCutsThenMultiplyVertexSizes(int expectedCuts) {
    StoerWagnerMinimumCut<String, DefaultEdge> swmc = new StoerWagnerMinimumCut<>(components);
    Preconditions.checkState(swmc.minCutWeight() == expectedCuts);
    int sizeOfOnePart = swmc.minCut().size();
    return sizeOfOnePart * (size() - sizeOfOnePart);
  }

  public record Pair<K, V>(K key, V value) {}
}
