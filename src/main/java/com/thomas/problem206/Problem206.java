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
package com.thomas.problem206;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import com.thomas.util.Euler;
import com.thomas.util.Euler.Problem;
import com.thomas.util.function.Function2;

/**
 * TODO Type documentation
 * 
 * @author Thomas
 * @since 19.11.2009
 */
public class Problem206 implements Problem {

    private static boolean isRepeating(List<Long> list) {
    
        if (list.isEmpty()) return false;
        
        final int size = list.size();
        
        if ((size & 1) == 1) return false;
        
        final int mid = size / 2;
        
        return list.subList(0, mid).equals(list.subList(mid, list.size()));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long solve() {

        long n = 1010101010L;
        List<Long> steps = Arrays.asList(10L);
        long pos = 100;
        for (int digit = 9; digit >= 2; --digit) {
            List<Long> next = new ArrayList<Long>();
            Collection<Long> dist = new AdjacentPairCollection<Long, Long>(new Function2<Long, Long, Long> () {

                @Override
                public Long call(Long arg1, Long arg2) { return arg2 - arg1; }
                
            }, next);
            loop: for(;;) {
                for (long l : steps) {
                    if (((n * n) / pos) % 10 == digit) {
                        next.add(n);
                        List<Long> copy = new ArrayList<Long>(dist);
                        if (isRepeating(copy)) {
                            steps = copy.subList(0, copy.size() / 2);
                            n = next.get(0);
                            break loop;
                        }
                    }
                    n += l;
                    if (n > 1389026623L) {
                        List<Long> copy = new ArrayList<Long>(dist);
                        steps = copy;
                        n = next.get(0);
                        break loop;
                    }
                }
            }

            pos *= 100;
        }
        
        return n;
    }
    
    /**
     * TODO Method documentation
     * 
     * @param args
     * @author Thomas
     * @since 19.11.2009
     */
    public static void main(String[] args) {

        Euler.run(new Problem206());
    }

}
