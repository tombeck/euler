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
package com.thomas.problem215;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO Type documentation
 * 
 * @author Thomas
 * @since 13.12.2009
 */
public class Main {

    static final class Row {
    
        final int bricks;
        final List<Row> allowed = new ArrayList<Row>();
        final long[] ways = new long[10];
        
        public Row(int bricks) {
            
            this.bricks = bricks;
            this.ways[0] = 1;
        }
        
        public void makeWays(int i) {
            
            for (Row row : this.allowed) {
                this.ways[i] += row.ways[i - 1];
            }
        }
    }
    
    /**
     * TODO Method documentation
     * 806844323190414
     * 
     * @param args
     * @author Thomas
     * @since 13.12.2009
     */
    public static void main(String[] args) {

        List<Row> rows = new ArrayList<Row>();
        
        makeRows(rows, 0);
        
        for (int i = rows.size(); i-- > 0; ) {
            for (int j = i; j-- > 0; ) {
                if ((rows.get(i).bricks & rows.get(j).bricks) == 0) {
                    rows.get(i).allowed.add(rows.get(j));
                    rows.get(j).allowed.add(rows.get(i));
                }
            }
        }
        for (int i = 1; i < 10; ++i) {
            for (Row row : rows) {
                row.makeWays(i);
            }
        }
        long sum = 0;
        for (Row row : rows) {
            sum += row.ways[9];
        }
        System.out.println("> " + sum);
    }

    private static void makeRows(List<Row> rows, int row) {

        switch (Integer.numberOfLeadingZeros(row)) {
        case 2:
            rows.add(new Row((row | 1) << 3));
            break;
        case 3:
            row = (row | 1) << 2;
        case 1:
            rows.add(new Row((row | 1) << 2));
            break;
        default:
            makeRows(rows, (row | 1) << 2);
            makeRows(rows, (row | 1) << 3);
        }
    }
    
}
