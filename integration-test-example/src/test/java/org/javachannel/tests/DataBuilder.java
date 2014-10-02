package org.javachannel.tests;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class DataBuilder  {
  static int[][] dataSet = new int[0][];

  static {
// DO NOT CHANGE THIS SEED!
Random random = new Random(1029);

dataSet = new int[62000][];
for (int i = 0; i < dataSet.length; i++) {
  int[] datum = new int[2];
  datum[0] = random.nextInt(9000);
  datum[1] = random.nextInt(9000);
  dataSet[i] = datum;
}
  }

  public static int[][] getGraph() {
    return dataSet;
  }

  public static int[] getRoots() {
    return new int[]{78, 1270, 1646, 1667, 3966, 6843, 7226, 7511, 7538, 8761};
  }

  public Set<Integer> asSet(int[] i) {
    Set<Integer> set=new HashSet<>();
    Arrays.stream(i).forEach(set::add);
    return set;
  }
}
