/**
 * $Id$
 * 
 * Copyright (c) 2014 Thomas Beckmann
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

package com.thomas.problem3xx.problem33x;

import static com.thomas.util.ArrayUtils.reverse;
import static java.util.Arrays.copyOf;

import java.util.Comparator;
import java.util.SortedSet;
import java.util.TreeSet;

import com.thomas.util.Euler;
import com.thomas.util.Euler.Problem;

/**
 * 
 * @author <a href="mailto:thomas.beckmann.mail@gmail.com">Thomas Beckmann</a>
 * @since 02.02.2014
 */
public class Problem336 implements Problem {

    Comparator<char[]> COMP = new Comparator<char[]>() {

        @Override
        public int compare(char[] c0, char[] c1) {

            int diff = 0;
            
            for (int i = 0; i < c0.length && diff == 0; ++i) {
                diff = Character.compare(c0[i], c1[i]);
            }
            
            return diff;
        }
        
    };
    
    char[][] EMPTY = new char[0][];
    
    /**
     * {@inheritDoc}
     */
    @Override
    public String solve() {

        SortedSet<char[]> s = new TreeSet<char[]>(COMP);
        
        s.add(new char[] {'K', 'J'});
        
        s = insert('I', s);
        s = insert('H', s);
        s = insert('G', s);
        s = insert('F', s);
        s = insert('E', s);
        s = insert('D', s);
        s = insert('C', s);
        s = insert('B', s);
        s = insert('A', s);
        
        return new String(s.toArray(EMPTY)[2011-1]);
    }

    SortedSet<char[]> insert(char c, SortedSet<char[]> trains) {
        
        final char[][] prev = trains.toArray(EMPTY);
        
        trains.clear();
        
        for (char[] t : prev) {
            char[] n = copyOf(t, t.length + 1);
            reverse(n, 0, t.length);
            n[t.length] = c;
            for (int i = 1; i < t.length; ++i) {
                char[] m = n.clone();
                reverse(m, i, n.length);
                trains.add(m);
            }
        }
        
        return trains;
    }

    /**
     * @param args
     */
    public static void main(String[] args) {

        Euler.run(new Problem336());
    }

}
