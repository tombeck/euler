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
package com.thomas.problem164;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.thomas.util.Euler;
import com.thomas.util.Euler.Problem;

/**
 * TODO Type documentation
 * 
 * @author Thomas
 * @since 22.11.2009
 */
public class Problem164 implements Problem {

    static class Node {
    
        private final Set<Node> successors = new HashSet<Node>();
        private final int[] digits;
        private final long[] count = {1, 1};
        
        public Node(int... digits) {

            this.digits = digits;
        }
        
        public boolean isStart() {
            
            return this.digits[0] > 0;
        }
        
        public void connect(Node other) {
        
            if (this.digits[1] == other.digits[0] && this.digits[2] == other.digits[1]) {
                this.successors.add(other);
            }
        }
        
        public void count(int index) {
            
            this.count[index] = 0;
            for (Node n : this.successors) {
                this.count[index] += n.count[1 - index];
            }
        }
        
        public long getCount(int index) {
            
            return this.count[index];
        }

    }
    
    /**
     * TODO Method documentation
     * 
     * @return
     * @see com.thomas.util.Euler.Problem#solve()
     * @author Thomas
     * @since 24.12.2009
     */
    @Override
    public Long solve() {

        final List<Node> all = new ArrayList<Node>();
        
        for (int i1 = 10; i1-- > 0; ) {
            for (int i2 = 10 - i1; i2-- > 0; ) {
                for (int i3 = 10 - (i1 + i2); i3-- > 0; ) {
                    all.add(new Node(i1, i2, i3));
                }
            }
        }
        for (int i = 0; i < all.size(); ++i) {
            all.get(i).connect(all.get(i));
            for (int j = i + 1; j < all.size(); ++j) {
                all.get(i).connect(all.get(j));
                all.get(j).connect(all.get(i));
            }
        }
        for (int i = 0; i < 17; ++i) {
            for (Node n : all) {
                n.count(i % 2);
            }
        }

        long sum = 0;
        for (Node n : all) {
            if (n.isStart()) sum += n.getCount(0);
        }
        
        return sum;
    }

    /**
     * TODO Method documentation
     * 
     * @param args
     * @author Thomas
     * @since 22.11.2009
     */
    public static void main(String[] args) {

        Euler.run(new Problem164());
    }

}
