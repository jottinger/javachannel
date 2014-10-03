package org.javachannel.tests;

import org.javachannel.SimpleGraph;
import org.testng.annotations.Test;

import java.util.Set;

import static org.testng.Assert.assertEquals;

public class SimpleRootDetection {
  @Test
  public void findRoots() {
    DataBuilder builder = new DataBuilder();
    SimpleGraph simpleGraph = new SimpleGraph();
    Set<Integer> roots = simpleGraph.findRoots(DataBuilder.getGraph());
    Set<Integer> validRoots = builder.asSet(DataBuilder.getRoots());
    assertEquals(validRoots, roots);
  }
}
