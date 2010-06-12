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

import static com.thomas.util.Digit.FACTORIAL;

import java.util.Comparator;

/**
 * TODO Type documentation
 * 
 * @author Thomas
 * @since 20.02.2010
 */
public class Permutation {

    public static boolean previousPermutation(char[] a) {

        int _First = 0;
        int  _Last = a.length;
        
        int _Next = _Last;
        if (_First == _Last || _First == --_Next)
            return false;

        for (; ; )
            {   // find rightmost element smaller than successor
            int _Next1 = _Next;
            if (a[--_Next] > a[_Next1])
                {   // swap with rightmost element that's smaller, flip suffix
                int _Mid = _Last;
                for (; !(a[_Next] > a[--_Mid]); ) { /**/ }
                ArrayUtils.swap(a, _Next, _Mid);
                ArrayUtils.reverse(a, _Next1, _Last);
                return true;
                }

            if (_Next == _First)
                {   // pure descending, flip all
                ArrayUtils.reverse(a, _First, _Last);
                return false;
                }
            }
    }

    public static boolean nextPermutation(int[] a) {

        int _First = 0;
        int  _Last = a.length;
        
        int _Next = _Last;
        if (_First == _Last || _First == --_Next)
            return false;

        for (; ; )
            {   // find rightmost element smaller than successor
            int _Next1 = _Next;
            if (a[--_Next] < a[_Next1])
                {   // swap with rightmost element that's smaller, flip suffix
                int _Mid = _Last;
                for (; !(a[_Next] < a[--_Mid]); ) { /**/ }
                ArrayUtils.swap(a, _Next, _Mid);
                ArrayUtils.reverse(a, _Next1, _Last);
                return true;
                }

            if (_Next == _First)
                {   // pure descending, flip all
                ArrayUtils.reverse(a, _First, _Last);
                return false;
                }
            }
    }

    public static boolean previousPermutation(int[] a) {

        int _First = 0;
        int  _Last = a.length;
        
        int _Next = _Last;
        if (_First == _Last || _First == --_Next)
            return false;

        for (; ; )
            {   // find rightmost element smaller than successor
            int _Next1 = _Next;
            if (a[--_Next] > a[_Next1])
                {   // swap with rightmost element that's smaller, flip suffix
                int _Mid = _Last;
                for (; !(a[_Next] > a[--_Mid]); ) { /**/ }
                ArrayUtils.swap(a, _Next, _Mid);
                ArrayUtils.reverse(a, _Next1, _Last);
                return true;
                }

            if (_Next == _First)
                {   // pure descending, flip all
                ArrayUtils.reverse(a, _First, _Last);
                return false;
                }
            }
    }

    public static <T> boolean nextPermutation(T[] a, Comparator<? super T> comp) {

        int _First = 0;
        int  _Last = a.length;
        
        int _Next = _Last;
        if (_First == _Last || _First == --_Next)
            return false;

        for (; ; )
            {   // find rightmost element smaller than successor
            int _Next1 = _Next;
            if (comp.compare(a[--_Next], a[_Next1]) < 0)
                {   // swap with rightmost element that's smaller, flip suffix
                int _Mid = _Last;
                for (; comp.compare(a[_Next], a[--_Mid]) >= 0; ) { /**/ }
                ArrayUtils.swap(a, _Next, _Mid);
                ArrayUtils.reverse(a, _Next1, _Last);
                return true;
                }

            if (_Next == _First)
                {   // pure descending, flip all
                ArrayUtils.reverse(a, _First, _Last);
                return false;
                }
            }
    }

    public static int count(int...c) {
        
        int ret = FACTORIAL[c.length];
        
        int n = 1;
        for (int i = 1, prev = c[0]; i < c.length; prev = c[i++]) {
            if (c[i] == prev) ++n;
            else {
                ret /= FACTORIAL[n];
                n = 1;
            }
        }
        ret /= FACTORIAL[n];
        
        return ret;
    }
    
    public static boolean isPermutation(int a, int b) {
        
        return hash(a) == hash(b);
    }
    
    public static long hash(int n) {
        
        long hash = 1;
        
        while (n >= 10) {
            hash *= Digit.PRIME[n % 10];
            n /= 10;
        }
        
        return hash;
    }
    
    private Permutation() {
        //
    }

}
