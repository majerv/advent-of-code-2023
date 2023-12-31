package com.vimacodes.aoc.day25;

/**
 * Ideas:
 *
 * <p>1) Sounds like union-find until we have 2 groups, but we must find <tt>exactly</tt> 3 edges to
 * remove from the original graph.*
 *
 * <p>2) Could also be: Girvan–Newman algorithm
 * (https://en.wikipedia.org/wiki/Girvan%E2%80%93Newman_algorithm) found on SO
 * (https://stackoverflow.com/questions/1566967/checking-if-removing-an-edge-in-a-graph-will-result-in-the-graph-splitting)
 *
 * <p>3) or even better: Ford-Fulkerson Algorithm: - https://www.baeldung.com/cs/minimum-cut-graphs
 * - https://www.geeksforgeeks.org/minimum-cut-in-a-directed-graph/
 *
 * <p>4) Using minimal cut algorithm from a graphing library (e.g. JGraphT)
 *
 * <p>5) Chosen: Stoer-Wagner algorithm, complexity: O(n*m*logm) where n=#v m=#e
 */
class Day25Exercise1 {
  public long solve(final String text) {
    ConnectedComponents components = ConnectedComponents.parse(text);
    System.out.printf(
        "vertices: %d edges: %d\n",
        components.getComponents().vertexSet().size(), components.getComponents().edgeSet().size());

    return components.cutInto2WithExactCutsThenMultiplyVertexSizes(3);
  }
}
