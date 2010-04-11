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
package com.thomas.problem95;

import static com.thomas.util.NumberUtils.sumsOfProperDivisors;

import java.util.Set;
import java.util.TreeSet;

import com.thomas.util.Euler;
import com.thomas.util.Euler.Problem;

/**
 * TODO Type documentation
 * 
 * @author Thomas
 * @since 30.12.2009
 */
class Problem95 implements Problem {

    /**
     * TODO Method documentation
     * 
     * @return
     * @see com.thomas.util.Euler.Problem#solve()
     * @author Thomas
     * @since 30.12.2009
     */
    @Override
    public Integer solve() {

        final int max = 1000000;
        final int[] sumOfProperDivisors = sumsOfProperDivisors(max + 1);

        for (int n = 1; n <= max; ++n) {
            if (sumOfProperDivisors[n] > max) sumOfProperDivisors[n] = 0;
        }
        
        int first = 0;
        int len = 1;
        
        final Set<Integer> chain = new TreeSet<Integer>();
        
        for (int n = 1, x; (x = n) <= max; ++n) {
            chain.clear();
            while (x != 0 && chain.add(x)) { x = sumOfProperDivisors[x]; }
            if (x == n) {
                if (chain.size() > len) {
                    len = chain.size();
                    first = n;
                }
                for (int i : chain) sumOfProperDivisors[i] = 0;
            } else if (x == 0){
                for (int i : chain) sumOfProperDivisors[i] = 0;
            }
        }
        
        return first;
    }

    /**
     * TODO Method documentation
     * 
     * @param args
     * @author Thomas
     * @since 30.12.2009
     */
    public static void main(String[] args) {

        Euler.run(new Problem95());
    }

}
