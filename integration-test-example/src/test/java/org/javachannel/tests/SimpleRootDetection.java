package org.javachannel.tests;

import org.javachannel.RootFinder;
import org.testng.annotations.Test;

import java.util.Set;

import static org.testng.Assert.assertEquals;

public class SimpleRootDetection {
  @Test
  public void findRoots() {
    DataBuilder builder = new DataBuilder();
    RootFinder rootFinder = new RootFinder();
    Set<Integer> roots = rootFinder.findRoots(DataBuilder.getGraph());
    Set<Integer> validRoots = builder.asSet(DataBuilder.getRoots());
    assertEquals(validRoots, roots);
  }
}
