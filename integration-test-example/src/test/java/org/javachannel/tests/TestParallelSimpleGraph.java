package org.javachannel.tests;

import org.javachannel.GraphUtility;
import org.javachannel.ParallelSimpleGraph;
import org.javachannel.timing.TimingWrapper;
import org.testng.annotations.Test;

import java.util.Random;
import java.util.Set;

import static org.testng.Assert.assertEquals;

public class TestParallelSimpleGraph {
  GraphUtility util = new ParallelSimpleGraph();
  Random r = new Random();
  long sum = 0;

  @Test
  public void firstTest() {
    TimingWrapper wrapper = new TimingWrapper();

    int[][] graph = {{0, 1}, {0, 2}, {1, 2}, {3, 1}, {4, 3}};
    Set<Integer> knownRoots = util.arrayToSet(new int[]{0, 4});
    Set<Integer> roots = wrapper.time(util::findRoots, graph);
    assertEquals(roots, knownRoots);
    System.out.println(wrapper);
  }

  @Test(invocationCount = 100)
  public void secondTest() {
    TimingWrapper wrapper = new TimingWrapper();
    Integer[][] graph = new Integer[620000][];
    for (Integer i = 0; i < 620000; i++) {
      graph[i] = new Integer[]{r.nextInt(7000), r.nextInt(7000)};
    }
    //Set<Integer> knownRoots = util.arrayToSet(new int[]{0, 4});
    Set<Integer> roots = wrapper.time(util::findRoots, graph);
    sum += roots.size();
    System.out.println(wrapper);
  }
}
