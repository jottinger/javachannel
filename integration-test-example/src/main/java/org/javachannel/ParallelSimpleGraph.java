package org.javachannel;

import java.util.Arrays;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;

/**
 * Incredibly slow algorithm, largely thanks to the set access,
 * I <em>think</em> - I need to run a profiler to check where
 * the time goes, because this is incredibly slow compared to the
 * non-parallel version.
 */
public class ParallelSimpleGraph implements GraphUtility {
  public Set<Integer> findRoots(Integer[][] graph) {
    Set<Integer> roots = new ConcurrentSkipListSet<>();
    Set<Integer> leaves = new ConcurrentSkipListSet<>();
    Arrays.parallelPrefix(graph, (c, d) -> {
      roots.add(d[0]);
      leaves.add(d[1]);
      return d;
    });
    roots.removeAll(leaves);
    return roots;
  }
}
