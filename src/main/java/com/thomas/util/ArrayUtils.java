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

import static java.util.Arrays.copyOf;

/**
 * TODO Type documentation
 * 
 * @author Thomas
 * @since 06.11.2009
 */
public class ArrayUtils {

    public static void swap(char[] a, int i, int j) {
        
        final char tmp = a[i];
        
        a[i] = a[j];
        a[j] = tmp;
    }
    
    public static void swap(int[] a, int i, int j) {
        
        final int tmp = a[i];
        
        a[i] = a[j];
        a[j] = tmp;
    }
    
    public static void swap(Object[] a, int i, int j) {
        
        final Object tmp = a[i];
        
        a[i] = a[j];
        a[j] = tmp;
    }
    
    public static void reverse(char[] a, int first, int last) {
        
        for (int i = (last - first) / 2; i-- > 0; ) {
            swap(a, first + i, last - (i + 1));
        }
    }
    
    public static void reverse(int[] a, int first, int last) {
        
        for (int i = (last - first) / 2; i-- > 0; ) {
            swap(a, first + i, last - (i + 1));
        }
    }
    
    public static void reverse(Object[] a, int first, int last) {
        
        for (int i = (last - first) / 2; i-- > 0; ) {
            swap(a, first + i, last - (i + 1));
        }
    }
    
    public static void rotate(char[] c) {
        
        final char tmp = c[0];
        final int length = c.length - 1;
        System.arraycopy(c, 1, c, 0, c.length - 1);
        c[length] = tmp;
    }
    
    public static void rotate(int[] c) {
        
        final int tmp = c[0];
        final int length = c.length - 1;
        System.arraycopy(c, 1, c, 0, c.length - 1);
        c[length] = tmp;
    }
    
    public static int[] append(int[] head, int tail) {
        
        final int[] next = copyOf(head, head.length + 1);
        
        next[head.length] = tail;
        
        return next;
    }
    
    public static long[] append(long[] head, long tail) {
        
        final long[] next = copyOf(head, head.length + 1);
        
        next[head.length] = tail;
        
        return next;
    }
    
    public static int lexicographicalCompare(int[] l, int[] r) {
        
        int il = 0;
        int ir = 0;
        
        while (il < l.length) {
            
            if (ir >= r.length) return 1;

            int c = l[il++] - r[ir++];

            if (c != 0) return c;
        }

        return ir < r.length ? -1 : 0;
    }
    
    private ArrayUtils() {
        //
    }
    
}
