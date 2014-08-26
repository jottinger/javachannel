package org.javachannel.examples;

/*
 * #%L
 * jsp-li-div
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

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("employee")
@RequestScoped
public class EmployeeResource {

    // Ideally this state should be stored in a database
    @EJB
    EmployeeRepository bean;

    @GET
    @Produces({"application/xml", "application/json"})
    public Employee[] getList() {
        return bean.getEmployees().toArray(new Employee[0]);
    }

    @GET
    @Produces({"application/json", "application/xml"})
    @Path("{id}")
    public Employee get(@PathParam("id") int id) {
        if (id < bean.getEmployees().size())
            return bean.getEmployees().get(id);
        else
            return null;
    }

    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public void addToList(@FormParam("name") String name,
                          @FormParam("age") int age) {
        System.out.println("Creating a new item: " + name);
        bean.addEmployee(new Employee(name, age));
    }

    @PUT
    public void putToList(@FormParam("name") String name,
                          @FormParam("age") int age) {
        addToList(name, age);
    }

    @DELETE
    @Path("{name}")
    public void deleteFromList(@PathParam("name") String name) {
        bean.deleteEmployee(name);
    }
}
