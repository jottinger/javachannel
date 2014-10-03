package org.javachannel;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

public class RefCountGraph implements GraphUtility {
  @Override
  public Set<Integer> findRoots(Integer[][] graph) {
    Set<Integer> roots = new HashSet<>();
    Map<Integer, Integer> references = new HashMap<>();
    for (Integer[] data : graph) {
      references.computeIfAbsent(data[0], d -> 0);
      references.merge(data[1], 1, (k, v) -> 1);
    }
    references.entrySet().stream()
        .filter(e -> (e.getValue() == 0)).forEach(e -> roots.add(e.getKey()));
    return roots;
  }
}
