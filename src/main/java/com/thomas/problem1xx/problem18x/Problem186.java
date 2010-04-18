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
package com.thomas.problem1xx.problem18x;

import com.thomas.util.Euler;
import com.thomas.util.Euler.Problem;
import com.thomas.util.random.IntGenerator;
import com.thomas.util.random.LaggedFibonacciGenerator;

/**
 * TODO Type documentation
 * 
 * @author Thomas
 * @since 30.03.2010
 */
public class Problem186 implements Problem {

    final static class Group {
    
        private int size = 1;
        private Group parent;
        
        private Group find() {
            
            if (this.parent == null) return this;
            
            return this.parent = this.parent.find();
        }
        
        public void union(Group other) {

            final Group thisParent = this.find();
            final Group otherParent = other.find();
            
            if (thisParent.size < otherParent.size) {
                thisParent.parent = otherParent;
                otherParent.size += thisParent.size;
            } else if (thisParent.size > otherParent.size) {
                otherParent.parent = thisParent;
                thisParent.size += otherParent.size;
            } else if (thisParent != otherParent) {
                otherParent.parent = thisParent;
                thisParent.size += otherParent.size;
            }
        }
        
        public int size() {
            
            return find().size;
        }
        
    }
    
    /**
     * TODO Method documentation
     * 
     * @return
     * @see com.thomas.util.Euler.Problem#solve()
     * @author Thomas
     * @since 30.03.2010
     */
    @Override
    public Integer solve() {

        final Group[] friends = new Group[1000000];
        
        for (int i = 0; i < 1000000; ++i) friends[i] = new Group();

        final IntGenerator gen = new LaggedFibonacciGenerator();

        int n = 0;
        
        while (friends[524287].size() < 990000) {
            
            final int caller = gen.next();
            final int called = gen.next();
            
            if (caller != called) {
                friends[caller].union(friends[called]);
                ++n;
            }
        }

        return n;
    }

    /**
     * TODO Method documentation
     * 
     * @param args
     * @author Thomas
     * @since 30.03.2010
     */
    public static void main(String[] args) {

        Euler.run(new Problem186());
    }

}
