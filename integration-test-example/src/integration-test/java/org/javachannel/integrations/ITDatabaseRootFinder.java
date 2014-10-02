package org.javachannel.integrations;

import org.javachannel.tests.DataBuilder;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

import static org.testng.Assert.assertEquals;

public class ITDatabaseRootFinder {
  static final String[] sqlStatements = new String[]{
      "drop table graph",
      "create table graph (" +
          "root integer not null, leaf integer not null, primary key (root, leaf))",
      "create index leaf_idx on graph(leaf)"
  };
  static final String jdbcUrl = "jdbc:derby://localhost:1527/foo;create=true";
  static final String insertSQL = "insert into graph (root, leaf) values (?, ?)";
  static final String findRootsSQL = "select distinct root from graph g where root not in (select distinct leaf from graph)";

  @BeforeTest
  public void testConnection() throws SQLException {
    try (Connection connection = DriverManager.getConnection(jdbcUrl, "derby", "derby")) {
      connection.setAutoCommit(false);
      for (String sql : sqlStatements) {
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
          ps.execute();
        } catch (SQLException e) {
          // we shouldn't get any exceptions, but...
          // in general they're okay here.
          System.out.println(e.getMessage());
        }
      }
      try (PreparedStatement ps = connection.prepareStatement(insertSQL)) {
        int counter = 0;
        for (int[] datum : DataBuilder.getGraph()) {
          ps.setInt(1, datum[0]);
          ps.setInt(2, datum[1]);
          try {
            ps.executeUpdate();
          } catch (SQLException ignored) {
            // it's okay if we don't write everything, because duplicates can occur.
          }
          counter++;
          if (counter % 500 == 0) {
            connection.commit();
          }
        }
      }
      connection.commit();
    }
  }

  @Test
  public void findRootsInDatabase() throws SQLException {
    Set<Integer> roots = new HashSet<>();
    try (Connection connection = DriverManager.getConnection(jdbcUrl, "derby", "derby")) {
      try (PreparedStatement ps = connection.prepareStatement(findRootsSQL)) {
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
          roots.add(rs.getInt(1));
        }
      }
    }
    Set<Integer> validRoots = new HashSet<Integer>();
    for (int i : DataBuilder.getRoots()) {
      validRoots.add(i);
    }
    assertEquals(validRoots, roots);
  }

  @Test
  public void findRootsInMemory() throws SQLException {
    Set<Integer> roots = new HashSet<>();
    Set<Integer> leaves = new HashSet<>();
    try (Connection connection = DriverManager.getConnection(jdbcUrl, "derby", "derby")) {
      try (PreparedStatement ps = connection.prepareStatement("select root, leaf from graph")) {
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
          roots.add(rs.getInt(1));
          leaves.add(rs.getInt(2));
        }
      }
    }
    roots.removeAll(leaves);
    Set<Integer> validRoots = new HashSet<Integer>();
    for (int i : DataBuilder.getRoots()) {
      validRoots.add(i);
    }
    assertEquals(validRoots, roots);
  }
}
