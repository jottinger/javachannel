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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PlainJavaStates {
    private static final List<String> states;

    static {
        List<String> out = new ArrayList<>();
        try (InputStream in =
                     PlainJavaStates.class.getResourceAsStream("states.txt")) {
            BufferedReader br =
                    new BufferedReader(new InputStreamReader(in, "UTF-8"));
            for (String line = br.readLine();
                 line != null;
                 line = br.readLine()) {
                if (line.isEmpty() || line.startsWith("#")) continue;
                out.add(line);
            }
        } catch (IOException e) {
            // RuntimeException is fine; the 'states'
            // file not existing is as likely as your States.class
            // file not existing; your app can crash
            // in the face of corrupt executables, which is what's happened
            // if states.txt isn't here.
            throw new RuntimeException("states.txt cannot be loaded.", e);
        }
        states = Collections.unmodifiableList(out);
    }

    public static List<String> getStates() {
        return states;
    }

    public static void main(String[] args) {
        System.out.printf("Number of states: %d%n ", getStates().size());
    }
}
