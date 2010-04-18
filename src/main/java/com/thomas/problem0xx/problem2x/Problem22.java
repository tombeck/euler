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
package com.thomas.problem0xx.problem2x;

import static com.thomas.util.IOUtils.closeQuietly;
import static java.lang.Character.getNumericValue;
import static java.util.Arrays.sort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.thomas.util.Euler;
import com.thomas.util.Euler.Problem;

/**
 * TODO Type documentation
 *
 * @author Thomas
 * @since 18.10.2009
 */
public class Problem22 implements Problem {

    /**
     * TODO Method documentation
     * 
     * @return
     * @throws IOException
     * @see com.thomas.util.Euler.Problem#solve()
     * @author Thomas
     * @since 31.12.2009
     */
    @Override
    public Integer solve() throws IOException {

        final BufferedReader reader = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream ("names.txt")));

        try {
            
            final String[] names = reader.readLine().replaceAll("\"", "").split(",");

            int sum = 0;

            sort(names);
            for (int i = 0, len = names.length; i < len; ++i) {
                sum += (i + 1) * nameValue(names[i]);
            }
            
            return sum;
            
        } finally {
            closeQuietly(reader);
        }
    }
    
    private static final int A = getNumericValue('A') - 1;
    
    private int nameValue(String name) {
        
        int score = 0;
        
        for (char c : name.toCharArray()) {
            score += getNumericValue(c) - A;
        }
        
        return score;
    }

    /**
     * TODO Method documentation
     * 
     * @param args
     * @author Thomas
     * @throws IOException 
     * @since 18.10.2009
     */
    public static void main(String[] args) throws IOException {

        Euler.run(new Problem22());
    }

}
