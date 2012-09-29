/**
 * $Id$
 *
 * Copyright (c) 2012 Thomas Beckmann
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
package com.thomas.problem3xx.problem39x;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.thomas.util.Euler;
import com.thomas.util.Euler.Problem;


/**
 * @author Thomas Beckmann
 * @since 22.09.2012
 */
public class Problem393 implements Problem {

    static enum TopDirection {
        
        BOTTOM, DONT_CARE
    }
    
    static enum PerRowDirection {
    
        TOP(TopDirection.DONT_CARE),
        BOTTOM(TopDirection.BOTTOM),
        DONT_CARE(TopDirection.DONT_CARE);
        
        final TopDirection top;

        private PerRowDirection(TopDirection top) {

            this.top = top;
        }
        
    }
    
    static enum Direction {
        
        TOP(PerRowDirection.TOP),
        RIGHT(PerRowDirection.DONT_CARE),
        BOTTOM(PerRowDirection.BOTTOM),
        LEFT(PerRowDirection.DONT_CARE),
        DONT_CARE(PerRowDirection.DONT_CARE);

        final PerRowDirection perRow;

        private Direction(PerRowDirection perRow) {

            this.perRow = perRow;
        }
        
    };
    
    static class SquareState {
    
        boolean occupied;
        Direction leftTo;
        
        @Override
        public String toString() {

            return "[" + (this.occupied ? "1" : "0") + ", leftTo=" + this.leftTo + "]";
        }

        @Override
        public int hashCode() {

            final int prime = 31;
            int result = 1;
            result = prime * result
                    + ((this.leftTo == null) ? 0 : this.leftTo.hashCode());
            result = prime * result + (this.occupied ? 1231 : 1237);
            return result;
        }

        @Override
        public boolean equals(Object obj) {

            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            SquareState other = (SquareState) obj;
            if (this.leftTo != other.leftTo)
                return false;
            if (this.occupied != other.occupied)
                return false;
            return true;
        }

    }
    
    static class PerRowSquareState {
        
        boolean occupied;
        PerRowDirection leftTo;
        
        PerRowSquareState(boolean occupied, PerRowDirection leftTo) {

            this.occupied = occupied;
            this.leftTo = leftTo;
        }

        PerRowSquareState(SquareState source) {
        
            this(source.occupied, source.leftTo.perRow);
            
        }
        
        @Override
        public int hashCode() {

            final int prime = 31;
            int result = 1;
            result = prime * result
                    + ((this.leftTo == null) ? 0 : this.leftTo.hashCode());
            result = prime * result + (this.occupied ? 1231 : 1237);
            return result;
        }

        @Override
        public boolean equals(Object obj) {

            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            PerRowSquareState other = (PerRowSquareState) obj;
            if (this.leftTo != other.leftTo)
                return false;
            if (this.occupied != other.occupied)
                return false;
            return true;
        }

        @Override
        public String toString() {

            return "[" + (this.occupied ? "1" : "0") + ", leftTo=" + this.leftTo + "]";
        }

    }
    
    static class TopSquareState {
        
        boolean occupied;
        TopDirection leftTo;
        
        TopSquareState(boolean occupied, TopDirection leftTo) {
            
            if (TopDirection.BOTTOM == leftTo && !occupied) throw new IllegalArgumentException();
            
            this.occupied = occupied;
            this.leftTo = leftTo;
            
        }
        
        TopSquareState(PerRowSquareState source) {
        
            this(source.occupied, source.leftTo.top);
            
        }
        
        @Override
        public int hashCode() {

            final int prime = 31;
            int result = 1;
            result = prime * result
                    + ((this.leftTo == null) ? 0 : this.leftTo.hashCode());
            result = prime * result + (this.occupied ? 1231 : 1237);
            return result;
        }

        @Override
        public boolean equals(Object obj) {

            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            TopSquareState other = (TopSquareState) obj;
            if (this.leftTo != other.leftTo)
                return false;
            if (this.occupied != other.occupied)
                return false;
            return true;
        }

        @Override
        public String toString() {

            return "[" + (this.occupied ? "1" : "0") + ", leftTo=" + this.leftTo + "]";
        }

        public TopSquareState combine(PerRowSquareState other) {
            
            switch(this.leftTo) {
            case BOTTOM: {
                if (other.occupied || PerRowDirection.TOP == other.leftTo) return null;
                return new TopSquareState(true, other.leftTo.top);
            }
            default: case DONT_CARE: {
                switch (other.leftTo) {
                case TOP: {
                    if (this.occupied) return null;
                    return new TopSquareState(other.occupied, other.leftTo.top);
                }
                case BOTTOM: {
                    if (!this.occupied || !other.occupied) return null;
                    return new TopSquareState(other.occupied, other.leftTo.top);
                }
                default: case DONT_CARE: {
                    if (!this.occupied) return null;
                    return new TopSquareState(other.occupied, other.leftTo.top);
                }
                }
            }
            }
        }
    }
    
    static class Row {
    
        private final PerRowSquareState[] squares;
        
        Row(PerRowSquareState[] squares) {

            super();
            this.squares = squares;
        }

        Row(SquareState[] row) {
            
            this.squares = new PerRowSquareState[row.length - 2];
            
            for (int i = 0; i < this.squares.length; ++i) {
                this.squares[i] = new PerRowSquareState(row[i + 1]);
            }
        }

        @Override
        public int hashCode() {

            return 31 + Arrays.hashCode(this.squares);
        }

        @Override
        public boolean equals(Object obj) {

            if (this == obj) return true;
            if (!(obj instanceof Row)) return false;
            
            return Arrays.equals(this.squares, ((Row)obj).squares);
        }

        @Override
        public String toString() {

            return Arrays.toString(this.squares);
        }
        
    }
    
    static class TopRow {
        
        private final TopSquareState[] squares;
        
        TopRow(TopSquareState[] squares) {
            
            this.squares = squares;
        }

        TopRow(Row row) {
            
            this.squares = new TopSquareState[row.squares.length];
            
            for (int i = 0; i < this.squares.length; ++i) {
                this.squares[i] = new TopSquareState(row.squares[i]);
            }
        }

        @Override
        public int hashCode() {

            return 31 + Arrays.hashCode(this.squares);
        }

        @Override
        public boolean equals(Object obj) {

            if (this == obj) return true;
            if (!(obj instanceof TopRow)) return false;
            
            return Arrays.equals(this.squares, ((TopRow)obj).squares);
        }

        @Override
        public String toString() {

            return Arrays.toString(this.squares);
        }
        
        public TopRow combine(Row other) {
            
            final TopSquareState[] squares = new TopSquareState[this.squares.length];
            
            for (int i = 0; i < squares.length; ++i) {
                if ((squares[i] = this.squares[i].combine(other.squares[i])) == null) return null;
            }

            return new TopRow(squares);
        }
        
    }
    
    /**
     * {@inheritDoc}
     * 
     * 112398351350823112
     * 
     */
    @Override
    public Long solve() {

        final int n = 10;
        final SquareState[] row = new SquareState[n+2];
        final Map<Row, Long> count = new HashMap<Row, Long>();
        
        for (int i = 0; i < row.length; ++i) {
            row[i] = new SquareState();
        }
        row[0].occupied = row[row.length - 1].occupied = true;
        
        findRow(row, row.length - 2, count);
        
        final TopSquareState[] squares = new TopSquareState[n];
        final PerRowSquareState[] bottomSquares = new PerRowSquareState[n];
        
        Arrays.fill(squares, new TopSquareState(true, TopDirection.DONT_CARE));
        Arrays.fill(bottomSquares, new PerRowSquareState(true, PerRowDirection.DONT_CARE));
        TopRow topRow = new TopRow(squares);
        Row bottomRow = new Row(bottomSquares);
        Map<TopRow, Long> m1 = new HashMap<TopRow, Long>();
        
        m1.put(topRow, 1L);

        for (int i = 0; i < n; ++i) {
            Map<TopRow, Long> m2 = new HashMap<TopRow, Long>();
            for (Entry<TopRow, Long> entry : m1.entrySet()) {
                for (Row r : count.keySet()) {
                    TopRow tr = entry.getKey().combine(r);
                    if (tr != null) {
                        add(m2, tr, entry.getValue().longValue());
                    }
                }
                
            }
            m1 = m2;
            System.out.println(m1.size());
        }
        Map<TopRow, Long> m2 = new HashMap<TopRow, Long>();
        for (Entry<TopRow, Long> entry : m1.entrySet()) {
            TopRow tr = entry.getKey().combine(bottomRow);
            if (tr != null) {
                add(m2, tr, entry.getValue().longValue());
            }
        }
        m1 = m2;

        return m1.get(topRow);
    }

    Map<TopRow, Integer> makeTopRowCounts(Map<Row, Integer> rowCounts) {
        
        final Map<TopRow, Integer> counts = new HashMap<TopRow, Integer>();
        
        for (Entry<Row, Integer> entry : rowCounts.entrySet()) {
            try {
                final TopRow row = new TopRow(entry.getKey());
                final Integer count = counts.get(row);
                
                if (count == null) {
                    counts.put(row, 1);
                } else {
                    counts.put(row, count.intValue() + 1);
                }
            } catch (IllegalArgumentException e) {
                
            }
        }
        
        return counts;
    }
    
    void findRow(SquareState[] row, int i, final Map<Row, Long> count) {
        
        if (i == 0) {
            add(count, new Row(row), 1L);
            
            return;
        }
        
        {
            row[i].leftTo = Direction.TOP;
            findRow(row, i - 1, count);
        }
        if (!row[i+1].occupied && row[i+1].leftTo != Direction.LEFT) {
            row[i].leftTo = Direction.RIGHT;
            row[i+1].occupied = true;
            findRow(row, i - 1, count);
            row[i+1].occupied = false;
        }
        {
            row[i].leftTo = Direction.BOTTOM;
            findRow(row, i - 1, count);
        }
        if (!row[i-1].occupied && row[i-1].leftTo != Direction.RIGHT) {
            row[i].leftTo = Direction.LEFT;
            row[i-1].occupied = true;
            findRow(row, i - 1, count);
            row[i-1].occupied = false;
        }
    }
    
    <T> void add(final Map<T, Long> count, T row, long n) {
    
        Long c = count.get(row);
        
        if (c == null) {
            count.put(row, n);
        } else {
            count.put(row, c.longValue() + n);
        }
    }
    
    long find(int n, boolean[][] grid, int x, int y, int remaining) {
    
        if (x == 1 && y == 0) return remaining == 0 ? 1 : 0;
        if (x < 0 || x >= n) return 0;
        if (y < 0 || y >= n) return 0;
        if (grid[x][y]) return 0;
        
        long count = 0;
        
        grid[x][y] = true;
        count += find(n, grid, x + 1, y, remaining - 1);
        count += find(n, grid, x - 1, y, remaining - 1);
        count += find(n, grid, x, y + 1, remaining - 1);
        count += find(n, grid, x, y - 1, remaining - 1);
        grid[x][y] = false;

        return count;
    }
    
    /**
     * @param args
     */
    public static void main(String[] args) {

        Euler.run(new Problem393());
    }

}
