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
package com.thomas.problem67;

import static com.thomas.util.IOUtils.closeQuietly;
import static com.thomas.util.NumberUtils.parseInt;
import static java.lang.Math.max;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.thomas.util.Euler;
import com.thomas.util.Euler.Problem;

/**
 * TODO Type documentation
 * 
 * @author Thomas
 * @since 14.10.2009
 */
class Problem67 implements Problem {

    /**
     * {@inheritDoc}
     * @throws IOException 
     */
    @Override
    public Integer solve() throws IOException {

        final BufferedReader reader = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream ("triangle.txt")));

        try {
            
            return maxPathSum(reader)[0];
            
        } finally {
            closeQuietly(reader);
        }
    }
    
    private int[] maxPathSum(BufferedReader reader) throws IOException {
        
        final String line = reader.readLine();
        
        return line == null ? null : joinRows(parseInt(line.split(" ")), maxPathSum(reader));
    }
    
    private int[] joinRows(int[] row, int[] next) {
        
        if (next != null) {
            for (int i = 0; i < row.length; ++i) {
                row[i] += max(next[i], next[i + 1]);
            }
        }
        
        return row;
    }
    
    /**
     * TODO Method documentation
     * 
     * @param args
     * @author Thomas
     * @since 14.10.2009
     */
    public static void main(String[] args) {

        Euler.run(new Problem67());
    }

}
