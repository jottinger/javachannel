package org.javachannel.examples;

/*
 * #%L
 * servlet-jndi-example
 * $Id:$
 * $HeadURL:$
 * %%
 * Copyright (C) 2014 Freenode ##java
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;

@WebServlet(name = "example", urlPatterns = "/exampleservlet")
public class ExampleServlet extends HttpServlet {
    @Resource(name = "java:comp/env/jdbc/hsqldb")
    DataSource dataSource;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        resp.setContentType("text/plain");
        try (Connection conn = dataSource.getConnection()) {
            DatabaseMetaData dmd = conn.getMetaData();
            out.printf("We hit the servlet.%nCan we get a data source? %s%n   (instance is %s)%n",
                    dataSource == null ? "no" : "yes",
                    dataSource == null ? "null" : dataSource.toString());
            out.printf("Database version: %d.%d%n",
                    dmd.getDatabaseMajorVersion(),
                    dmd.getDatabaseMinorVersion());
        } catch (SQLException e) {
            e.printStackTrace(out);
        }

    }
}
