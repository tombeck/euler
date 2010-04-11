/**
 * $Id$
 *
 * Copyright (c) 2009 Thomas Beckmann 
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.thomas.problem59;

import static com.thomas.util.IOUtils.closeQuietly;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * TODO Type documentation
 * 
 * @author Thomas
 * @since 09.11.2009
 */
public class Main {

    /**
     * TODO Method documentation
     * 
     * -> god
     * 
     * @param args
     * @author Thomas
     * @throws IOException 
     * @since 09.11.2009
     */
    public static void main(String[] args) throws IOException {

        int sum = 0;
        
        final BufferedReader reader = new BufferedReader(new InputStreamReader(Main.class.getResourceAsStream ("cipher1.txt")));

        try {
            
            final String[] characters = reader.readLine().split(",");

            for (int i = 0; i < characters.length; ++i) {
                sum += decrypt(characters, i);
            }
        } finally {
            closeQuietly(reader);
        }
        
        System.out.print(sum);
    }

    private static int decrypt(String[] encrypted, int index) {
        
        switch (index % 3) {
        case 0: return Integer.parseInt(encrypted[index]) ^ 103;
        case 1: return Integer.parseInt(encrypted[index]) ^ 111;
        case 2: return Integer.parseInt(encrypted[index]) ^ 100;
        }
        return 0;
    }
}
