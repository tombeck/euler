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
package com.thomas.problem61;

import java.util.ArrayList;
import java.util.List;

import com.thomas.util.Euler;
import com.thomas.util.Euler.Problem;

/**
 * TODO Type documentation
 * 
 * @author Thomas
 * @since 14.11.2009
 */
class Problem61 implements Problem {

    /**
     * TODO Method documentation
     * 
     * @return
     * @see com.thomas.util.Euler.Problem#solve()
     * @author Thomas
     * @since 21.02.2010
     */
    @Override
    public Integer solve() {

        final List<int[]> numbers = new ArrayList<int[]>();
        
        for (int n = 45, t; (t = (n * (1 * n + 1)) / 2) < 10000; ++n) numbers.add(new int[] {3, t / 100, t % 100});
        for (int n = 32, t; (t = (n * (1 * n + 0)) / 1) < 10000; ++n) numbers.add(new int[] {4, t / 100, t % 100});
        for (int n = 26, t; (t = (n * (3 * n - 1)) / 2) < 10000; ++n) numbers.add(new int[] {5, t / 100, t % 100});
        for (int n = 23, t; (t = (n * (2 * n - 1)) / 1) < 10000; ++n) numbers.add(new int[] {6, t / 100, t % 100});
        for (int n = 21, t; (t = (n * (5 * n - 3)) / 2) < 10000; ++n) numbers.add(new int[] {7, t / 100, t % 100});

        for (int n = 19, t; (t = (n * (3 * n - 2)) / 1) < 10000; ++n) {
            int ret = solve(numbers, t / 100, t % 100);
            if (ret >= 0) return t + ret;
        }
        
        return null;
    }
    
    private int solve(List<int[]> numbers, int head, int tail) {
        
        if (numbers.isEmpty()) return head == tail ? 0 : -1;
        
        for (int[] n : numbers) {
            if (n[1] == tail) {
                
                final List<int[]> rem = new ArrayList<int[]>();
                
                for (int[] number : numbers) {
                    if (n[0] != number[0]) rem.add(number);
                }
                int ret = solve(rem, head, n[2]);
                if (ret >= 0) return n[1] * 100 + n[2] + ret;
            }
        }
        
        return -1;
    }
    
    /**
     * TODO Method documentation
     * 
     * @param args
     * @author Thomas
     * @since 14.11.2009
     */
    public static void main(String[] args) {

        Euler.run(new Problem61());
    }

}
