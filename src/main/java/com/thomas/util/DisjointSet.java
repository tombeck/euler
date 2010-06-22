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
package com.thomas.util;

/**
 * @author thomas (initial creation)
 * @author $Author: $ (last modification)
 * @version $Date: $
 */
public class DisjointSet<T> {

    private DisjointSet<T> parent;
    private int size = 1;

    public DisjointSet() {
        
        clear();
    }
    
    protected void clear() {
        
        this.parent = null;
        this.size = 1;
    }
    
    protected DisjointSet<T> find() {

        if (this.parent == null) return this;

        return this.parent = this.parent.find();
    }

    public void union(DisjointSet<T> other) {

        final DisjointSet<T> thisParent = this.find();
        final DisjointSet<T> otherParent = other.find();

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

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {

        return find().superHashCode();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object obj) {

        if (this == obj) return true;
        if (!(obj instanceof DisjointSet<?>)) return false;
        
        final DisjointSet<?> other = (DisjointSet<?>)obj;
        
        return find() == other.find();
    }

    private int superHashCode() {
        
        return super.hashCode();
    }
    
}
