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
package com.thomas.problem238;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.thomas.util.Euler;
import com.thomas.util.Euler.Problem;
import com.thomas.util.random.BlumBlumShub;
import com.thomas.util.random.IntGenerator;

/**
 * @author Thomas Beckmann
 * @since 17.12.2010
 */
public class Problem238 implements Problem {

    /**
     * {@inheritDoc}
     */
    @Override
    public Long solve() {

        final IntGenerator gen = new BlumBlumShub(14025256, 20300713);
        final List<Integer> w = new ArrayList<Integer>();
        
        int next = gen.next();
        do {
            append(w, next);
        } while ((next = gen.next()) != 14025256);
        
        int max = 0;
        
        for (int b : w) max += b;

        final int[] p = new int[max + 1];
        final int size = w.size();

        w.addAll(w.subList(0, 88));

        for (int z = 89; z >= 1; --z) {
            for (int m = 0, i = z - 1, k = i + size; i < k; ++i) {
                p[m += w.get(i)] = z;
            }
        }
        
        long count = 0;
        
        for (int k = 1; k <= max; ++k) {
            count += ((2000000000000000L - k) / max + 1) * p[k];
        }

        return count; // 9922545104535661
    }

    private void append(Collection<Integer> a, int n) {
        
        if (n >= 10) append(a, n / 10);
        a.add(n % 10);
    }
    
    /**
     * @param args
     */
    public static void main(String[] args) {

        Euler.run(new Problem238());
    }

}
