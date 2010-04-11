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
package com.thomas.util;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * TODO Type documentation
 * 
 * @author Thomas
 * @since 05.12.2009
 */
public class PrimeSieve implements Iterable<Integer>{

    final int size;
    final long[] arr;
    final long[] masks = new long[Long.SIZE];
    
    public PrimeSieve(int size) {

        assert (Long.SIZE == 64);
        
        this.size = size;
        this.arr = new long[size / Long.SIZE + 1];
        long mask = 1;
        for (int i = 0; i < Long.SIZE; ++i) {
            this.masks[i] = mask << i;
        }
        for (int i = 2; i < size; ++i) {
            if ((this.arr[i >> 6] & this.masks[i & 0x3f]) == 0) {
                for (int j = i << 1; j < size; j += i) this.arr[j >> 6] |= this.masks[j & 0x3f];
            }
        }
        for (int i = size, l = this.arr.length * Long.SIZE - 1; i < l; ++i) {
            this.arr[i >> 6] |= this.masks[i & 0x3f];
        }
    }

    /**
     * TODO Method documentation
     * 
     * @param n
     * @return
     * @author Thomas
     * @since 05.12.2009
     */
    public boolean isPrime(int n) {

        return (this.arr[n >> 6] & this.masks[n & 0x3f]) == 0; 
    }

    /**
     * TODO Method documentation
     * 
     * @return
     * @see java.lang.Iterable#iterator()
     * @author Thomas
     * @since 05.12.2009
     */
    @Override
    public Iterator<Integer> iterator() {

        return new Iterator<Integer>() {

            private int i = 2;
            private boolean hasNext = true;
            
            @Override
            public boolean hasNext() {

                while(!this.hasNext && this.i < PrimeSieve.this.size) {
                    this.hasNext = isPrime(++this.i);
                }

                return this.hasNext;
            }

            @Override
            public Integer next() {

                try {
                    while(!this.hasNext) {
                        this.hasNext = isPrime(++this.i);
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new NoSuchElementException();
                }
                this.hasNext = false;
                
                return this.i;
            }

            @Override
            public void remove() {

                throw new UnsupportedOperationException();
            }
            
        };
    }
    
    
}
