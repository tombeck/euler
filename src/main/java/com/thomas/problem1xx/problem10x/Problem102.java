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
package com.thomas.problem1xx.problem10x;

import static com.thomas.util.IOUtils.closeQuietly;
import static com.thomas.util.NumberUtils.parseInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.thomas.util.Euler;
import com.thomas.util.Euler.Problem;

/**
 * TODO Type documentation
 * 
 * @author Thomas
 * @since 18.11.2009
 */
class Problem102 implements Problem {

    /**
     * TODO Method documentation
     * 
     * @return
     * @see com.thomas.util.Euler.Problem#solve()
     * @author Thomas
     * @throws IOException 
     * @since 28.03.2010
     */
    @Override
    public Integer solve() throws IOException {

        final BufferedReader reader = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream ("triangles.txt")));

        try {
            
            int count = 0;
            
            for (String line; (line = reader.readLine()) != null; ) {
                
                final int[] s = parseInt(line.split(","));
                
                final int[] A = {s[0], s[1]};
                final int[] B = {s[2], s[3]};
                final int[] C = {s[4], s[5]};
                
                if (containsOrigin(A, vector(A, B), vector(A, C))) ++count;
            }
            
            return count;

        } finally {
            closeQuietly(reader);
        }
    }
    
    private boolean containsOrigin(int[] o, int[] u, int[] v) {
        
        final int detvo = det(v, o);
        final int detou = det(o, u);
        final int detuv = det(u, v);
        
        if (detuv == 0) return false;
        
        return detuv > 0 ?
                detvo > 0 && detou > 0 && detvo + detou < detuv :
                detvo < 0 && detou < 0 && detvo + detou > detuv;
    }
    
    private int[] vector(int[] u, int[] v) {
    
        return new int[] {v[0] - u[0], v[1] - u[1]};
    }
    
    private int det(int[] u, int[] v) {
    
        return u[0] * v[1] - u[1] * v[0];
    }

    /**
     * TODO Method documentation
     * 
     * @param args
     * @author Thomas
     * @since 18.11.2009
     */
    public static void main(String[] args) {

        Euler.run(new Problem102());
    }

}
