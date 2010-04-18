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
package com.thomas.problem105;

import static com.thomas.util.IOUtils.closeQuietly;
import static com.thomas.util.NumberUtils.parseInt;
import static java.util.Arrays.sort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

import com.thomas.util.Euler;
import com.thomas.util.Euler.Problem;

/**
 * TODO Type documentation
 * 
 * @author Thomas
 * @since 26.12.2009
 */
public class Problem105 implements Problem {

    /**
     * TODO Method documentation
     * 
     * @return
     * @see com.thomas.util.Euler.Problem#solve()
     * @author Thomas
     * @throws IOException 
     * @since 26.12.2009
     */
    @Override
    public Integer solve() throws IOException {

        final BufferedReader reader = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream ("sets.txt")));
        
        try {
            
            int sum = 0;
            
            for (String line; (line = reader.readLine()) != null; ) {

                final int[] set = parseInt(line.split(","));

                if (isSpecialSumSet(set)) sum += sum(set);
            }

            return sum;

        } finally {
            closeQuietly(reader);
        }
    }

    private boolean isSpecialSumSet(int[] set) {
    
        sort(set);
        
        for (int i = 1, j, l = set[0], h = 0; i < (j = set.length - i); ++i) {
            if ((l += set[i]) <= (h += set[j])) return false;
        }

        final Set<Integer> sums = new HashSet<Integer>();
        
        for (int i = 1; i < (1 << set.length); ++i) {
            if (!sums.add(sum(set, i))) return false;
        }

        return true;
    }
    
    private int sum(int[] a) {
    
        int sum = 0;
        
        for (int i : a) sum += i;
        
        return sum;
    }
    
    private int sum(int[] a, int mask) {
        
        int sum = 0;
        
        for (int i = 0; mask > 0; ++i) {
            if ((mask & 1) != 0) sum += a[i];
            mask >>= 1;
        }
        
        return sum;
    }
    
    /**
     * TODO Method documentation
     * 
     * @param args
     * @author Thomas
     * @since 26.12.2009
     */
    public static void main(String[] args) {

        Euler.run(new Problem105());
    }

}
