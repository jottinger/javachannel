package org.javachannel;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public interface GraphUtility {
  public default Set<Integer> arrayToSet(int[] data) {
    Set<Integer> set = new HashSet<>();
    Arrays.stream(data).forEach(set::add);
    return set;
  }

  public default Set<Integer> findRoots(Integer[][] graph) {
    int[][] primitiveGraph=new int[graph.length][];
    for(int i=0;i<graph.length;i++) {
      primitiveGraph[i]=new int[]{graph[i][0], graph[i][1]};
    }
    return findRoots(primitiveGraph);
  }

  public default Set<Integer> findRoots(int[][] graph) {
    Set<Integer> roots = new HashSet<>();
    Set<Integer> leaves = new HashSet<>();
    Arrays.stream(graph).forEach(d -> {
      roots.add(d[0]);
      leaves.add(d[1]);
    });
    roots.removeAll(leaves);
    return roots;
  }
}
