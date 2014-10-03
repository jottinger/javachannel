package org.javachannel.integrations;

public enum DatabaseInterface {
  DERBYEMBEDDED("jdbc:derby:foo;create=true",
      new String[]{
          "drop table graph",
          "create table graph (" +
              "root integer not null, leaf integer not null, primary key (root, leaf))",
          "create index leaf_idx on graph(leaf)",
          "create index root_idx on graph(root)"},
      "insert into graph (root, leaf) values (?, ?)",
      "select distinct root from graph g where root not in (select " +
          "distinct leaf from graph)", "select root, leaf from graph"),
  DERBY("jdbc:derby://localhost:1527/foo;create=true", new String[]{
      "drop table graph",
      "create table graph (" +
          "root integer not null, leaf integer not null, primary key (root, leaf))",
      "create index leaf_idx on graph(leaf)",
      "create index root_idx on graph(root)"},
      "insert into graph (root, leaf) values (?, ?)",
      "select distinct root from graph g where root not in (select " +
          "distinct leaf from graph)", "select root, leaf from graph"),
  H2("jdbc:h2:./foo",
      new String[]{"drop table if exists graph",
          "create table graph (" +
              "root integer not null, leaf integer not null, primary key (root, leaf))",
          "create index leaf_idx on graph(leaf)",
          "create index root_idx on graph(root)"},
      "insert into graph (root, leaf) values (?, ?)",
      "select distinct root from graph g where root not in (select " +
          "distinct leaf from graph)", "select root, leaf from graph");

  final String url;
  final String[] ddl;
  final String insert;
  final String dbRootSelection;
  final String dbRowSelection;

  DatabaseInterface(String url,
                    String[] prepareDatabase,
                    String insert,
                    String dbRootSelection,
                    String dbRowSelection) {
    this.url = url;
    this.ddl = prepareDatabase;
    this.insert = insert;
    this.dbRootSelection = dbRootSelection;
    this.dbRowSelection = dbRowSelection;
  }

}
