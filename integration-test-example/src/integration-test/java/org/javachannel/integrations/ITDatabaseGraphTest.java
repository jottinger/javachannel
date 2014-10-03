package org.javachannel.integrations;

import org.javachannel.timing.TimingWrapper;
import org.testng.annotations.Test;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;

import static org.testng.Assert.assertEquals;

public class ITDatabaseGraphTest {
  ConstantGraph graph = ConstantGraph.instance;

  @Test(invocationCount = 2)
  public void testAgainstDatabaseSimple() throws SQLException {
    System.out.println("Simple query test----------------------");
    for (DatabaseInterface database : DatabaseInterface.values()) {
      System.out.println("Testing against "+database.url);
      populateDatabase(database);
      try (Connection conn = DriverManager.getConnection(database.url, "sa", "sa")) {
        try (PreparedStatement ps = conn.prepareStatement(database.dbRowSelection)) {
          Set<Integer> roots=new HashSet<>();
          Set<Integer> leaves=new HashSet<>();

          long start = System.nanoTime();
          ResultSet rs=ps.executeQuery();
          while(rs.next()) {
            roots.add(rs.getInt(1));
            leaves.add(rs.getInt(2));
          }
          roots.removeAll(leaves);
          assertEquals(roots, graph.arrayToSet(graph.getRoots()));
          long end = System.nanoTime();
          System.out.println((end-start)+" nanoseconds ("+(end-start)/1000000000.0+" ms)");
        }
      }
    }
  }

  @Test(invocationCount = 2)
  public void testAgainstDatabaseComplex() throws SQLException {
    System.out.println("Complex query test---------------------");
    for (DatabaseInterface database : DatabaseInterface.values()) {
      System.out.println("Testing against "+database.url);
      populateDatabase(database);
      try (Connection conn = DriverManager.getConnection(database.url, "sa", "sa")) {
        try (PreparedStatement ps = conn.prepareStatement(database.dbRootSelection)) {
          Set<Integer> roots=new HashSet<>();

          long start = System.nanoTime();
          ResultSet rs=ps.executeQuery();
          while(rs.next()) {
            roots.add(rs.getInt(1));
          }
          assertEquals(roots, graph.arrayToSet(graph.getRoots()));
          long end = System.nanoTime();
          System.out.println((end-start)+" nanoseconds ("+(end-start)/1000000000.0+" ms)");
        }
      }
    }
  }

  private void populateDatabase(DatabaseInterface database) throws SQLException {
    try (Connection conn = DriverManager.getConnection(database.url, "sa", "sa")) {
      conn.setAutoCommit(false);
      for (String ddl : database.ddl) {
        try (PreparedStatement ps = conn.prepareStatement(ddl)) {
          ps.executeUpdate();
        } catch (SQLException ignored) {
          System.out.println(ignored.getMessage());
        }
      }
      int counter=0;
      try (PreparedStatement ps = conn.prepareStatement(database.insert)) {
        for (int[] data : graph.getDataSet()) {
          ps.setInt(1, data[0]);
          ps.setInt(2, data[1]);
          try {
            ps.executeUpdate();
            counter++;
            if(counter%250==0) {
              conn.commit();
            }
          } catch(SQLException ignored) {
            // we know we have 27 duplicates.
          }
        }
      } catch (SQLException ignored) {
        System.out.println(ignored.getMessage());
      }
      conn.setAutoCommit(true);
    }
  }
}
