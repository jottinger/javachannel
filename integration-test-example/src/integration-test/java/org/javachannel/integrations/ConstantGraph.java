package org.javachannel.integrations;

import org.javachannel.GraphUtility;

import java.util.Random;

public class ConstantGraph implements GraphUtility {
  final int[][] dataSet;
  final int[] validRoots =
      new int[]{78, 1270, 1646, 1667, 3966, 6843, 7226, 7511, 7538, 8761};

  private ConstantGraph() {
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

  public int[][] getDataSet() {
    return dataSet;
  }

  public int[] getRoots() {
    return validRoots;
  }

  public final static ConstantGraph instance = new ConstantGraph();
}
