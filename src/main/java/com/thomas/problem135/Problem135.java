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
package com.thomas.problem135;

import static com.thomas.util.PrimeUtils.getPrimeFactors;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import com.thomas.util.Euler;
import com.thomas.util.Euler.Problem;

/**
 * TODO Type documentation
 * 
 * @author Thomas
 * @since 10.01.2010
 */
class Problem135 implements Problem {

    /**
     * TODO Method documentation
     * 
     * @return
     * @see com.thomas.util.Euler.Problem#solve()
     * @author Thomas
     * @since 10.01.2010
     */
    @Override
    public Integer solve() {

        int sum = 0;
        
        for (int n = 1155; n < 1000000; ++n) {
            if (solutions(n).size() == 10) ++sum;
        }
        
        return sum;
    }

    private Set<Long> solutions(int n) {
        
        final List<Integer> factors = getPrimeFactors(n);
        final Set<Long> solutions = new TreeSet<Long>();
        
        for (int i = 1 << factors.size(); i-- > 1 && solutions.size() <= 11; ) {
            
            final long y = y(factors, i);
            
            if (y * y > n / 3 && ((n / y) + y) % 4 == 0) solutions.add(y);
        }
        
        return solutions;
    }
    
    private long y(List<Integer> factors, int mask) {
    
        long y = 1;
        
        for (int i = 0; i < factors.size(); ++i) {
            if ((1 << i & mask) != 0) y *= factors.get(i);  
        }
        
        return y;
    }
    
    /**
     * TODO Method documentation
     * 
     * @param args
     * @author Thomas
     * @since 10.01.2010
     */
    public static void main(String[] args) {

        Euler.run(new Problem135());
    }

}
