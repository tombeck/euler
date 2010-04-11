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

import java.util.AbstractCollection;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * TODO Type documentation
 * 
 * @author Thomas
 * @since 15.11.2009
 */
public class EquivalenceClasses<T> extends AbstractCollection<Collection<T>> {

    final List<T> base;
    final Comparator<? super T> comp;
    
    public EquivalenceClasses(List<T> base, Comparator<? super T> comp) {

        this.base = base;
        this.comp = comp;
    }

    /**
     * TODO Method documentation
     * 
     * @return
     * @see java.util.AbstractCollection#iterator()
     * @author Thomas
     * @since 15.11.2009
     */
    @Override
    public Iterator<Collection<T>> iterator() {

        final ListIterator<T> iter = this.base.listIterator();
        
        return new Iterator<Collection<T>>() {

            private boolean hasNext = iter.hasNext();
            private T next = this.hasNext ? iter.next() : null;

            @Override
            public boolean hasNext() {

                return this.hasNext;
            }

            @Override
            public Collection<T> next() {

                if (!this.hasNext) throw new NoSuchElementException();
                int first = iter.previousIndex();
                
                for (T prev = this.next; iter.hasNext(); prev = this.next) {
                    this.next = iter.next();
                    if (EquivalenceClasses.this.comp.compare(prev, this.next) != 0) {
                        return EquivalenceClasses.this.base.subList(first, iter.previousIndex());
                    }
                }
                this.hasNext = false;

                return EquivalenceClasses.this.base.subList(first, iter.nextIndex());
            }

            @Override
            public void remove() {

                throw new UnsupportedOperationException();
            }
            
        };
    }

    /**
     * TODO Method documentation
     * 
     * @return
     * @see java.util.AbstractCollection#size()
     * @author Thomas
     * @since 15.11.2009
     */
    @Override
    public int size() {

        int size = 0;

        for (Iterator<?> i = iterator(); i.hasNext(); i.next()) ++size;
        
        return size;
    }

    public static void main(String[] args) {
    
        final List<Integer> l = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);
        final Comparator<Integer> comp = new Comparator<Integer>() {

            @Override
            public int compare(Integer o1, Integer o2) {

                return o1.intValue() % 10 - o2.intValue() % 10;
            }
            
        };
        
        Collections.sort(l, comp);
        System.out.println(new EquivalenceClasses<Integer>(l, comp));
    }
    
}
