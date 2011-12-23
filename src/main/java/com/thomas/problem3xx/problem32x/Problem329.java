/**
 * $Id$
 *
 * Copyright (c) 2011 Thomas Beckmann
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
package com.thomas.problem3xx.problem32x;

import java.util.Set;
import java.util.TreeSet;

import com.thomas.util.Euler;
import com.thomas.util.Euler.Problem;
import com.thomas.util.PrimeUtils;
import com.thomas.util.rational.LongRational;

/**
 * @author Thomas Beckmann
 * @since 19.12.2011
 */
public class Problem329 implements Problem {

    private static final Set<Integer> PRIMES = new TreeSet<Integer>(PrimeUtils.primes(500));
    private static final int CROAKS = Integer.parseInt("101001000110000", 2);
    
    /**
     * 199740353/29386561536000
     * 
     * {@inheritDoc}
     */
    @Override
    public LongRational solve() {

        long prob = 0;
        
        for (int i = 1; i <= 500; ++i) {
            prob += prob(0, 0, i);
        }

        return new LongRational(prob, 500L * 16384L * 14348907L);
    }

    private long prob(int index, int path, int pos) {
        
        if (PRIMES.contains(pos)) {
            path |= 1 << index;
        }
        if (index == 14) {
            return (1 << Integer.bitCount(path^CROAKS));
        }
        
        ++index;
        
        switch(pos) {
        case   1: return prob(index, path, pos + 1) << 1;
        default : return prob(index, path, pos + 1) + prob(index, path, pos - 1);
        case 500: return prob(index, path, pos - 1) << 1;
        }
    }
    
    /**
     * @param args
     */
    public static void main(String[] args) {

        Euler.run(new Problem329());
    }

}
