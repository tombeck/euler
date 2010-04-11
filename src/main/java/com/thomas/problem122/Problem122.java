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
package com.thomas.problem122;

import static java.lang.Math.min;

import com.thomas.util.Euler;
import com.thomas.util.Euler.Problem;

/**
 * TODO Type documentation
 * 
 * @author Thomas
 * @since 09.01.2010
 */
class Problem122 implements Problem {

    static class Node {
    
        public final int head;
        public final Node tail;
        public final int size;
        
        public Node(int head) {

            this.head = head;
            this.tail = null;
            this.size = 1;
        }
        
        public Node(int head, Node tail) {

            this.head = head;
            this.tail = tail;
            this.size = 1 + tail.size;
        }
        
    }
    
    /**
     * TODO Method documentation
     * 
     * @return
     * @see com.thomas.util.Euler.Problem#solve()
     * @author Thomas
     * @since 09.01.2010
     */
    @Override
    public Object solve() {

        final int[] min = new int[200 + 1];
        final Node init = new Node(1);
        
        int sum = 0;

        for (int i = 2; i <= 200; ++i) {
            if (min[i] == 0) {
                for (int m = m(i, init, i - 1), j = i; j <= 200; j *= 2) {
                    sum += m;
                    min[j] = m++;
                }
            }
        }
        
        return sum;
    }

    private int m(int n, Node found, int min) {
        
        if (found.size >= min) return min;
        
        for (Node node = found; node != null; node = node.tail) {
            
            final int sum = found.head + node.head;
            
            if (sum > n) continue;
            if (sum == n) return found.size;
            min = min(min, m(n, new Node(sum, found), min));
        }

        return min;
    }
    
    /**
     * TODO Method documentation
     * 
     * @param args
     * @author Thomas
     * @since 09.01.2010
     */
    public static void main(String[] args) {

        Euler.run(new Problem122());
    }

}
