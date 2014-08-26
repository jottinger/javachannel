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

import javax.ejb.Singleton;
import java.util.ArrayList;
import java.util.List;

@Singleton
public class EmployeeRepository {

    private final List<Employee> list;

    public EmployeeRepository() {
        list = new ArrayList<>();
    }

    public void addEmployee(Employee e) {
        list.add(e);
    }

    public void deleteEmployee(String name) {
        Employee p = findEmployeeByName(name);
        if (p != null)
            list.remove(p);
    }

    private Employee findEmployeeByName(String name) {
        for (Employee e : list) {
            if (name.equals(e.getName()))
                return e;
        }
        return null;
    }

    public List<Employee> getEmployees() {
        return list;
    }
}
