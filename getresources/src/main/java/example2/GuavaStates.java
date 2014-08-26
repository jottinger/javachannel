/*
 * This code licensed under the Apache Source License 2.0.
 *
 * See http://www.apache.org/licenses/LICENSE-2.0 for the contents of the license.
 */
package example2;

/*
 * #%L
 * getresources
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

import com.google.common.base.Charsets;
import com.google.common.collect.ImmutableList;
import com.google.common.io.Resources;

import java.io.IOException;

public class GuavaStates {
    private static final ImmutableList<String> states;

    static {
        try {
            states = ImmutableList.copyOf(
                    Resources.readLines(
                            GuavaStates.class.getResource("states.txt"),
                            Charsets.UTF_8));
        } catch (IOException e) {
            // RuntimeException is fine; the 'states'
            // file not existing is as likely as your States.class
            // file not existing; your app can crash
            // in the face of corrupt executables, which is what's happened
            // if states.txt isn't here.
            throw new RuntimeException("states.txt cannot be loaded.", e);
        }
    }

    public static ImmutableList<String> getStates() {
        return states;
    }

    public static void main(String[] args) {
        System.out.printf("Number of states: %d%n ", getStates().size());
    }
}
