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
package com.thomas.problem0xx.problem3x;

import static com.thomas.util.Permutation.nextPermutation;

import java.util.HashSet;
import java.util.Set;

import com.thomas.util.Euler;
import com.thomas.util.Euler.Problem;

/**
 * TODO Type documentation
 * 
 * @author Thomas
 * @since 26.10.2009
 */
class Problem32 implements Problem {

    /**
     * TODO Method documentation
     * 
     * @return
     * @see com.thomas.util.Euler.Problem#solve()
     * @author Thomas
     * @since 12.01.2010
     */
    @Override
    public Integer solve() {

        final Set<Integer> products = new HashSet<Integer>();
        
        int sum = 0;
        
        for (int[] p = {1, 2, 3, 4, 5, 6, 7, 8, 9}; nextPermutation(p); ) {
            for (int i = 1; i < 3; ++i) {

                final int product  = toInt(p, 5, 9);
                
                if (toInt(p, 0, i) * toInt(p, i, 5) == product) {
                    if (products.add(product)) sum += product;
                }
            }
        }
        
        return sum;
    }
    
    private int toInt(int[] p, int first, int last) {
        
        int n = 0;
        
        for (int i = first; i < last; ++i) n = n * 10 + p[i];
        
        return n;
    }

   /**
     * TODO Method documentation
     * 
     * @param args
     * @author Thomas
     * @since 26.10.2009
     */
    public static void main(String[] args) {

        Euler.run(new Problem32());
    }

}
