/* 
 * Copyright (C) 2019 Kay Wrobel <kay.wrobel@gmx.net>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.example.simpleservicetester;

import com.example.simpleservicetester.servicehandler.PostReader;

/**
 * Run test for connecting to an online web service and retrieve
 * its JSON-formatted data. The data comes from the following source:
 * https://jsonplaceholder.typicode.com
 * 
 * @author Kay Wrobel
 */
public class TestRunner {

    /**
     * Entry point that runs the test scenario
     * 
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        PostReader postReader = new PostReader();
        postReader.displayRandom();
                
    }
        
}
