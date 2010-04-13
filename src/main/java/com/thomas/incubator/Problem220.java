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
package com.thomas.incubator;

import com.thomas.util.Euler;
import com.thomas.util.Euler.Problem;

/**
 * TODO Type documentation
 * 
 * @author Thomas
 * @since 06.12.2009
 */
public class Problem220 implements Problem {

    static class Chain {
    
        private static Chain A = new Chain("aRbFR");
        private static Chain B = new Chain("LFaLb");

        private char head;
        private Chain tail;
        
        public Chain(char head, Chain tail) {

            this.head = head;
            this.tail = tail;
        }
        
        public Chain(char c) {

            this(c, null);
        }
        
        public Chain(char[] a, int offset) {
            
            this(a[offset], offset + 1 >= a.length ? null : new Chain(a, offset + 1));
        }
        
        public Chain(String s) {
            
            this(s.toCharArray(), 0);
        }
        
        public Chain copy() {
        
            return new Chain(this.head, this.tail == null ? null : this.tail.copy());
        }
        
        public Chain append(Chain c) {
        
            if (this.tail == null) this.tail = c;
            else this.tail.append(c);
            
            return this;
        }
        
        public Chain replace() {
        
            if (this.head == 'a') {
                return this.tail == null ? A.copy() : A.copy().append(this.tail.replace());
            }
            if (this.head == 'b') {
                return this.tail == null ? B.copy() : B.copy().append(this.tail.replace());
            }
            if (this.tail != null) this.tail = this.tail.replace();
            return this;
        }
        
        public Chain clean() {
        
            if (this.tail == null) return this;
            if (this.head == 'R' && this.tail.head == 'L') return this.tail.tail == null ? this.tail.tail : this.tail.tail.clean();
            if (this.head == 'L' && this.tail.head == 'R') return this.tail.tail == null ? this.tail.tail : this.tail.tail.clean();
            
            this.tail = this.tail.clean();
            
            return this;
        }
        
        public void move(Cursor cursor, int steps) {
        
            if (steps == 0) return;
            
            if (this.tail == null) throw new IllegalArgumentException("chain is too small");

            this.tail.move(cursor, cursor.move(this.head) ? steps - 1 : steps);
        }
        
        @Override
        public String toString() {
        
            final StringBuilder builder = new StringBuilder();
            
            appendTo(builder);
            
            return builder.toString();
        }
        
        private void appendTo(StringBuilder buffer) {
            
            if (this.head != 'a' && this.head != 'b') buffer.append(this.head);
            if (this.tail != null) this.tail.appendTo(buffer);
        }
        
    }
    
    static class Cursor {
    
        private int x = 0;
        private int y = 0;
        private int direction = 0;
        
        public boolean move(char c) {
         
            if (c == 'F') {
                switch (this.direction) {
                case 0: ++this.y; break;
                case 1: ++this.x; break;
                case 2: --this.y; break;
                case 3: --this.x; break;
                }
                return true;
            }
            if (c == 'R') {
                this.direction = (this.direction + 1) & 0x3;
            } else if (c == 'L') {
                this.direction = (this.direction + 3) & 0x3;
            }
            return false;
        }
        
        @Override
        public String toString() {
        
            return "(" + this.x + "," + this.y + ")";
        }
        

    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public Object solve() throws Exception {

        Chain init = new Chain("Fa");

        for (int i = 0; i < 9; ++i) {
            init = init.replace();
            init = init.clean();
        }
        final Cursor cursor = new Cursor();
        
        init.move(cursor, 500);
        System.out.println(cursor);
        
        return 0;
    }

    /**
     * TODO Method documentation
     * 
     * FRF R
     * 
     * FRF R
     * FLF R
     * 
     * FRF R
     * FLF R
     * FRF L
     * FLF R
     * 
     * FRFRFLF R
     * FRFLFLF R
     * FRFRFLF L
     * FRFLFLF R
     * 
     * @param args
     * @author Thomas
     * @since 06.12.2009
     */
    public static void main(String[] args) {

        Euler.run(new Problem220());
    }

}
