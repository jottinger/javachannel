package org.javachannel.timing;

import java.util.Set;
import java.util.function.Function;

public class TimingWrapper {
  long elapsedTime=0;

  public Set<Integer> time(Function<int[][], Set<Integer>> findRoots, int[][] graph) {
    Set<Integer> set;
    long start=System.nanoTime();
    set=findRoots.apply(graph);
    long end=System.nanoTime();
    elapsedTime=end-start;
    return set;
  }

  @Override
  public String toString() {
    return "TimingWrapper{" +
        "elapsedTime=" + elapsedTime +
        '}';
  }
}
