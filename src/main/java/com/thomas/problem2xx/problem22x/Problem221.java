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
package com.thomas.problem2xx.problem22x;

import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;

import com.thomas.util.Euler;
import com.thomas.util.Euler.Problem;
import com.thomas.util.NumberUtils;

/**
 * @author thomas (initial creation)
 * @author $Author: $ (last modification)
 * @version $Date: $
 */
public class Problem221 implements Problem {

    /**
     * r(p + q) + pq = 1 <=> (1 - pq)/(p + q) = r
     * 
     * p, q, r != 0
     * p + q != 0
     * 1 - pq != 0 => 1 != pq
     * 
     * |r| < |p| < |q|
     * r > 0, p < 0, q < 0
     * r, p, q sind teilerfremd
     * 
     * A = (pq * (pq - 1)) / (p + q), p > q > 0
     * 
     * {@inheritDoc}
     */
    @Override
    public Long solve() {

        final Set<Long> ai = new TreeSet<Long>();

        for (long r = 1, rr = 2; r <= 77790; rr += 2 * (r++) + 1) {
            long[] d = NumberUtils.toLongArray(NumberUtils.divisors(rr));
            for (int i = d.length / 2 - 1; i >= 0; --i) {
                long a = d[i];
                long b = d[d.length-(i+1)];
                if (2*r+a+b > (Long.MAX_VALUE/r-1)/r) {
                    break;
                }
                ai.add(r * (r * (2*r + a + b) + 1));
            }
        }

        return new ArrayList<Long>(ai).get(150000 - 1);
    }

    /**
     * @param args
     */
    public static void main(String[] args) {

        Euler.run(new Problem221());
    }

}
