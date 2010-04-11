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
package com.thomas.problem0xx.problem4x;

import static com.thomas.util.IOUtils.closeQuietly;
import static java.lang.Character.getNumericValue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.thomas.util.Euler;
import com.thomas.util.Euler.Problem;

/**
 * TODO Type documentation
 * 
 * @author Thomas
 * @since 20.10.2009
 */
class Problem42 implements Problem {

    /**
     * TODO Method documentation
     * 
     * @return
     * @see com.thomas.util.Euler.Problem#solve()
     * @author Thomas
     * @throws IOException
     * @since 19.01.2010
     */
    @Override
    public Integer solve() throws IOException {

        final BufferedReader reader = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream ("words.txt")));

        try {
            
            final String[] words = reader.readLine().replaceAll("\"", "").split(",");

            int count = 0;
            
            for (int i = 0, len = words.length; i < len; ++i) {
                if (isTriangleNumber(getWordValue(words[i]))) ++count;
            }
            
            return count;
            
        } finally {
            closeQuietly(reader);
        }
    }
    
    private final int NVA = getNumericValue('A') - 1;
    
    private int getWordValue(String name) {
        
        int value = 0;
        
        for (char c : name.toCharArray()) {
            value += getNumericValue(c) - this.NVA;
        }
        
        return value;
    }

    private boolean isTriangleNumber(int n) {
        
        final int two = 2 * n;
        final int sqrt = (int)Math.sqrt(two);
        
        return sqrt * (sqrt + 1) == two;
    }
    
    /**
     * TODO Method documentation
     * 
     * @param args
     * @author Thomas
     * @since 20.10.2009
     */
    public static void main(String[] args) {

        Euler.run(new Problem42());
    }

}
