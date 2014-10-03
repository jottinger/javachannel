package org.javachannel;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Reference counting to detect root nodes.
 * Algorithm by Greyson Ottinger, age 12.
 */
public class RefCountGraph implements GraphUtility {
  @Override
  public Set<Integer> findRoots(Integer[][] graph) {
    Set<Integer> roots = new HashSet<>();
    Map<Integer, Integer> references = new ConcurrentHashMap<>();

/*    BinaryOperator<Integer[]> op = (ignored, datum) -> {
      references.computeIfAbsent(datum[0], d -> 0);
      references.put(datum[1], 1);

      return datum;
    };
*/
    Arrays.parallelPrefix(graph, (ignored, datum) -> {
      references.computeIfAbsent(datum[0], d -> 0);
      references.put(datum[1], 1);

      return datum;
    });

    references.entrySet().stream()
        .filter(e -> (e.getValue() == 0)).forEach(e -> roots.add(e.getKey()));
    return roots;
  }
}
