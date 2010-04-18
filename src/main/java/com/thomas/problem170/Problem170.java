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
package com.thomas.problem170;

import static com.thomas.util.Permutation.previousPermutation;
import static java.lang.Integer.bitCount;

import com.thomas.util.Euler;
import com.thomas.util.Euler.Problem;

/**
 * TODO Type documentation
 * 
 * @author Thomas
 * @since 07.03.2010
 */
public class Problem170 implements Problem {

    /**
     * TODO Method documentation
     *
     * @return
     * @see com.thomas.util.Euler.Problem#solve()
     * @author Thomas
     * @since 07.03.2010
     */
    @Override
    public Long solve() {

        final int[] p = {9, 8, 7, 6, 5, 4, 3, 2, 1, 0};
        
        permutations: do {
            long n = 0;
            for (int i = 0; i < 10; ++i) {
                n = 10 * n + p[i];
                for (long d = 3; d < 50; d += 3) {
                    if (n % d == 0) {
                        int m1 = mask(d);
                        int m2 = mask(n / d);
                        if ((m1 & m2) == 0) {
                            if (find(p, d, i + 1, m1 | m2)) {
                                break permutations;
                            }
                        }
                    }
                }
            }
        } while (previousPermutation(p));
        
        long ret = 0;
        
        for (int i = 0; i < 10; ++i) ret = ret * 10 + p[i];
        
        return ret;
    }

    private boolean find(int[] p, long d, int offset, int mask) {
        
        if (offset == 10) return (bitCount(mask) == 10);
        
        long n = 0;
        for (int i = offset; i < 10; ++i) {
            if ((n = 10 * n + p[i]) % d == 0) {
                int m = mask(n / d);
                if (n > 0 && (m & mask) == 0 && find(p, d, i + 1, mask | m)) {
                    return true;
                }
            }
        }
        
        return false;
    }
    
    private int mask(long n) {
    
        int m = 0;
        
        for (; n > 0; n /= 10) {
            int t = 1 << (n % 10);
            if ((t & m) != 0) return -1;
            m |= 1 << (n % 10);
        }
        
        return m;
    }
    
    /**
     * TODO Method documentation
     * 
     * @param args
     * @author Thomas
     * @since 07.03.2010
     */
    public static void main(String[] args) {

        Euler.run(new Problem170());
    }

}
