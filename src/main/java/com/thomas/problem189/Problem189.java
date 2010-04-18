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
package com.thomas.problem189;

import java.util.HashSet;
import java.util.Set;

import com.thomas.util.Euler;
import static com.thomas.util.NumberUtils.*;
import com.thomas.util.Euler.Problem;

/**
 * TODO Type documentation
 * 
 * @author Thomas
 * @since 31.03.2010
 */
public class Problem189 implements Problem {

    static class Row {
    
        final int pattern;
        final Set<Row> next = new HashSet<Row>();
        
        long count = 1;
        
        public Row(int pattern) {

            this.pattern = pattern;
        }
        
    }
    
    /**
     * TODO Method documentation
     * 
     * @return
     * @see com.thomas.util.Euler.Problem#solve()
     * @author Thomas
     * @since 31.03.2010
     */
    @Override
    public Object solve() {

        Row[][] rows = new Row[16][];
        
        for (int i = 0; i < 8; ++i) {
            pattern(i, 0,
                    rows[2 * i] = new Row[pow(3, i + 1)],
                    rows[2 * i + 1] = new Row[pow(3, i + 1)], 0);
        }
        
        for (int i = 1; i < 15; ++i) {
            for (int j = 0; j < rows[i - 1].length; ++j) {
                for (int k = 0; k < rows[i].length; ++k) {
                    if (isNext(rows[i - 1][j].pattern, rows[i][k].pattern, (i & 1) != 0)) {
                        rows[i - 1][j].next.add(rows[i][k]);
                    }
                }
            }
        }
        for (int i = 14; i-- > 0; ) {
            for (Row row : rows[i]) {
                row.count = 0;
                for (Row n : row.next) {
                    row.count += n.count;
                }
            }
        }
        
        return rows[0][0].count * 3;
    }

    private boolean isNext(int p1, int p2, boolean inverse) {
        
        
        if (inverse) return (~p2 | p1) == ~p2;
        
        return (~p1 | p2) == ~p1 && (~p1 | (p2 >> 3)) == ~p1;
    }
    
    private int pattern(int s, int pattern, Row[] rows, Row[] irows, int i) {
    
        if (s < 0) {
            rows[i] = new Row(pattern);
            irows[i] = new Row(pattern);
            ++i;
        } else {
            for (int c = 0; c < 3; ++c) {
                i = pattern(s - 1, pattern << 3 | (1 << c), rows, irows, i) ;
            }
        }
        
        return i;
    }
    
    /**
     * TODO Method documentation
     * 
     * @param args
     * @author Thomas
     * @since 31.03.2010
     */
    public static void main(String[] args) {

        Euler.run(new Problem189());
    }

}
