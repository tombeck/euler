/**
 * $Id$
 *
 * Copyright (c) 2010 Thomas Beckmann 
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
package com.thomas.problem103;

import static java.lang.Integer.bitCount;
import static java.lang.Math.max;

import java.util.HashSet;
import java.util.Set;

import com.thomas.util.Euler;
import com.thomas.util.Euler.Problem;

/**
 * TODO Type documentation
 * 
 * @author Thomas
 * @since 23.01.2010
 */
public class Problem103 implements Problem {

    /**
     * TODO Method documentation
     * 
     * @return
     * @see com.thomas.util.Euler.Problem#solve()
     * @author Thomas
     * @since 23.01.2010
     */
    @Override
    public String solve() {

        final int[][] masks = createMasks(7);
        
        for (int sum = 28; sum <= 271; ++sum) {
            for (int i1 = 1; i1 * 7 <= sum - 21; ++i1) {
                for (int i2 = i1 + 1; i1 + i2 * 6 <= sum - 15; ++i2) {
                    for (int i3 = i2 + 1; i1 + i2 + i3 * 5 <= sum - 10; ++i3) {
                        for (int i4 = max(i3, sum / 2 - (i1 + i2 + i3)) + 1; i1 + i2 + i3 + i4 * 4 <= sum - 6; ++i4) {
                            for (int i5 = i4 + 1; i1 + i2 + i3 + i4 + i5 * 3 <= sum - 3; ++i5) {
                                for (int i6 = i5 + 1; i1 + i2 + i3 + i4 + i5 + i6 * 2 <= sum - 1; ++i6) {
                                    int i7 = sum - (i1 + i2 + i3 + i4 + i5 + i6);
                                    
                                    if (isSpecialSumSet(masks, i1, i2, i3, i4, i5, i6, i7)) {
                                        return "" + i1 + i2 + i3 + i4 + i5 + i6 + i7;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        
        return null;
    }

    private int[][] createMasks(int n) {
        
        final int[][] masks = new int[n / 2 + 1][];
        
        for (int i = 2; i < masks.length; ++i) {
            masks[i] = new int[factorial(n) / (factorial(i) * factorial(n - i))];
            for (int j = 0, k = 0; j < (1 << n); ++j) {
                if (bitCount(j) == i) masks[i][k++] = j;
            }
        }
        
        return masks;
    }
    
    private boolean isSpecialSumSet(int[][] masks, int... set) {

        final Set<Integer> cache = new HashSet<Integer>();
        
        for (int i = 2; i <= set.length / 2; ++i) {
            cache.clear();
            for (int mask : masks[i]) {
                if (!cache.add(sum(set, mask))) return false;
            }
        }
        
        return true;
    }
    
    private int sum(int[] a, int mask) {
        
        int sum = 0;
        
        for (int i = 0; mask > 0; ++i) {
            if ((mask & 1) != 0) sum += a[i];
            mask >>= 1;
        }
        
        return sum;
    }
    
    private int factorial(int n) {
    
        int factorial = 1;
        
        while (n > 1) factorial *= n--;
        
        return factorial;
    }
    
    /**
     * TODO Method documentation
     * 
     * @param args
     * @author Thomas
     * @since 23.01.2010
     */
    public static void main(String[] args) {

        Euler.run(new Problem103());
    }

}
