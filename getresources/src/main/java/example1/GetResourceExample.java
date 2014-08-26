/*
 * This code licensed under the Apache Source License 2.0.
 *
 * See http://www.apache.org/licenses/LICENSE-2.0 for the contents of the license.
 */
package example1;

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

import javax.swing.*;
import java.net.URL;

public class GetResourceExample {
    public static JLabel createPrintIcon() {
        URL printIconUrl =
                GetResourceExample.class.getResource("icons/printer63.png");
        ImageIcon printIcon =
                new ImageIcon(printIconUrl, "Print document");
        return new JLabel(printIcon);
    }

    public static void main(String... args) {
        JFrame frame = new JFrame("GetResourceExample");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.add(createPrintIcon());
        frame.pack();
        frame.setVisible(true);
    }
}
