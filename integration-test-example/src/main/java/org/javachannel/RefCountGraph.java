package org.javachannel;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

public class RefCountGraph implements GraphUtility {
  @Override
  public Set<Integer> findRoots(int[][] graph) {
    Set<Integer> roots = new HashSet<>();
    Map<Integer, Integer> references = new HashMap<>();
    for (int[] data : graph) {
      Integer d1 = data[0];
      Integer d2 = data[1];
      references.computeIfAbsent(d1, d -> 0);
      references.merge(d2, 1, (k, v) -> 1);
    }
    references.entrySet().stream()
        .filter(e -> (e.getValue() == 0)).forEach(e -> roots.add(e.getKey()));
    return roots;
  }
}
