package org.javachannel;

import java.util.HashSet;
import java.util.Set;

public class RootFinder {
public Set<Integer> findRoots(int[][] graph) {
  Set<Integer> roots = new HashSet<>();
  Set<Integer> leaves = new HashSet<>();
  for (int[] datum : graph) {
    roots.add(datum[0]);
    leaves.add(datum[1]);
  }
  roots.removeAll(leaves);
  return roots;
}
}
