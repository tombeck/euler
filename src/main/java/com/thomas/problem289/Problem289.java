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
package com.thomas.problem289;

import static java.util.Arrays.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;

import com.thomas.util.DisjointSet;
import com.thomas.util.Euler;
import com.thomas.util.Euler.Problem;
import com.thomas.util.matrix.LongColVector;
import com.thomas.util.matrix.LongMatrix;
import com.thomas.util.matrix.LongRowVector;

/**
 * @author thomas (initial creation)
 * @author $Author: $ (last modification)
 * @version $Date: $
 */
public class Problem289 implements Problem {

    static class Group extends DisjointSet<Group> {
        
        private int index = -1;
        
        public void clear() {
            
            super.clear();
            this.index = -1;
        }

        public int index(int min) {
            
            final Group parent = (Group)find();
            
            if (parent.index == -1) return (parent.index = min) + 1;
            
            return min;
        }
        
        public int index() {
            
            return ((Group)find()).index;
        }
    }
    
    private static final Comparator<int[]> PATTERN_COMPARATOR = new Comparator<int[]>() {

        @Override
        public int compare(int[] o1, int[] o2) {

            int diff = 0;
            
            for (int i = 0; diff == 0 && i < o1.length; ++i) {
                diff = o1[i] - o2[i];
            }

            return diff;
        }
        
    };
    
    /**
     * {@inheritDoc}
     * 
     * 1933356766
     * 
     */
    @Override
    public Object solve() {

        long x = 999999980000000001L;
        final int size = 6 + 1;
        final int[][] patterns = patterns(0, 1, new int[size], new ArrayList<int[]>()).toArray(new int[0][]);
        
        sort(patterns, PATTERN_COMPARATOR);
        
        final long[] start = new long[patterns.length];
        final long[][] matrix = new long[patterns.length][patterns.length];
        final long[] end = new long[patterns.length];
        
        for (int a0 : new int[] {0, 13}) {
            for (int a1 : new int[] {0, 13}) {
                for (int a2 : new int[] {0, 13}) {
                    for (int a3 : new int[] {0, 13}) {
                        for (int a4 : new int[] {0, 13}) {
                            final Group[] from = {new Group(), new Group(), new Group(), new Group(), new Group(), new Group(), new Group()};
                            final Group[] mid = new Group[size + 1];
                            final Group[] to = new Group[size];
                            mid[0] = new Group();
                            if (next(from, mid, to, 0, 0) && next(from, mid, to, 1, a0) && next(from, mid, to, 2, a1) && next(from, mid, to, 3, a2) && next(from, mid, to, 4, a3) && next(from, mid, to, 5, a4) && next(from, mid, to, 6, 0)) {
            
                                final int[] pattern = toIndexArray(to);
                            
                                int j = Arrays.binarySearch(patterns, pattern, PATTERN_COMPARATOR);
                                ++start[j];
                                if (start[j] >= 10000000000L) start[j] -= 10000000000L;
                            }
                        }
                    }
                }
            }
        }
        for (int i = 0; i < patterns.length; ++i) {
            System.out.println(i);
            for (int a0 : new int[] {0, 4}) {
                for (int a1 = 0; a1 < 14; ++a1) {
                    for (int a2 = 0; a2 < 14; ++a2) {
                        for (int a3 = 0; a3 < 14; ++a3) {
                            for (int a4 = 0; a4 < 14; ++a4) {
                                for (int a5 = 0; a5 < 14; ++a5) {
                                    outer: for (int a6 : new int[] {0, 4}) {
                                        final Group[] from = from(patterns[i], makeGroupArray(size));
                                        final Group[] mid = new Group[size + 1];
                                        final Group[] to = new Group[size];
                                        mid[0] = new Group();
                                        if (next(from, mid, to, 0, a0) && next(from, mid, to, 1, a1) && next(from, mid, to, 2, a2) && next(from, mid, to, 3, a3) && next(from, mid, to, 4, a4) && next(from, mid, to, 5, a5) && next(from, mid, to, 6, a6)) {
                                            
                                            inner1: for (Group g : from) {
                                                for (Group f : to) {
                                                    if (g.equals(f)) continue inner1;
                                                }
                                                continue outer;
                                            }
                                            inner2: for (int j = 1; j < mid.length - 1; ++j) {
                                                final Group g = mid[j];
                                                for (Group f : to) {
                                                    if (g.equals(f)) continue inner2;
                                                }
                                                continue outer;
                                            }
                                            final int[] pattern = toIndexArray(to);
                                            
                                            int j = Arrays.binarySearch(patterns, pattern, PATTERN_COMPARATOR);
                                            ++matrix[i][j];
                                            if (matrix[i][j] >= 10000000000L) matrix[i][j] -= 10000000000L;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            for (int a0 : new int[] {0, 12}) {
                for (int a1 : new int[] {0, 12}) {
                    for (int a2 : new int[] {0, 12}) {
                        for (int a3 : new int[] {0, 12}) {
                            outer: for (int a4 : new int[] {0, 12}) {
                                final Group[] from = from(patterns[i], makeGroupArray(size));
                                final Group[] mid = new Group[size + 1];
                                final Group[] to = new Group[size];
                                mid[1] = from[0];
                                mid[size] = from[size - 1];
                                if (next(from, mid, to, 1, a0) && next(from, mid, to, 2, a1) && next(from, mid, to, 3, a2) && next(from, mid, to, 4, a3) && next(from, mid, to, 5, a4)) {
                                    
                                    inner1: for (Group g : from) {
                                        for (Group f : to) {
                                            if (g.equals(f)) continue inner1;
                                        }
                                        continue outer;
                                    }
                                    ++end[i];
                                    if (end[i] >= 10000000000L) end[i] -= 10000000000L;
                                }
                            }
                        }
                    }
                }
            }
        }

        return new LongRowVector(start)
            .modMultiply(new LongMatrix(patterns.length, matrix).modPow(9, 10000000000L), 10000000000L)
            .modMultiply(new LongColVector(end), 10000000000L)
            .at(0, 0);
    }

    private boolean next(Group[] from, Group[] mid, Group[] to, int i, int a) {
        
        switch(a) {
        case 0: {
            if (from[i].equals(mid[i])) return false;
            from[i].union(mid[i]);
            mid[i+1] = mid[i];
            to[i] = from[i];
            break;
        }
        case 1: {
            if (from[i].equals(mid[i])) return false;
            from[i].union(mid[i]);
            mid[i+1] = to[i] = new Group();
            break;
        }
        case 2: {
            mid[i+1] = from[i];
            to[i] = mid[i];
            break;
        }
        case 3: {
            mid[i+1] = mid[i];
            to[i] = new Group();
            break;
        }
        case 4: {
            mid[i+1] = new Group();
            to[i] = from[i];
            break;
        }
        case 5: {
            mid[i+1] = from[i];
            to[i] = new Group();
            break;
        }
        case 6: {
            mid[i+1] = to[i] = new Group();
            break;
        }
        case 7: {
            mid[i+1] = new Group();
            to[i] = mid[i];
            break;
        }
        case 8: {
            if (from[i].equals(mid[i])) return false;
            from[i].union(mid[i]);
            mid[i+1] = new Group();
            to[i] = new Group();
            break;
        }
        case 9: {
            mid[i+1] = new Group();
            to[i] = new Group();
            break;
        }
        case 10: {
            if (from[i].equals(mid[i])) return false;
            from[i].union(mid[i]);
            mid[i+1] = new Group();
            to[i] = from[i];
            break;
        }
        case 11: {
            to[i] = mid[i+1] = from[i];
            break;
        }
        case 12: {
            to[i] = mid[i+1] = mid[i];
            break;
        }
        case 13: {
            if (from[i].equals(mid[i])) return false;
            from[i].union(mid[i]);
            mid[i+1] = mid[i];
            to[i] = new Group();
            break;
        }
        }
        
        return true;
    }
    
    private Group[] from(int[] from, Group[] groups) {
    
        final Group[] ret = new Group[from.length];
        
        for (int i = 0; i < from.length; ++i) ret[i] = groups[from[i]];
        
        return ret;
    }
    
    private Group[] makeGroupArray(int size) {
    
        final Group[] groups = new Group[size];
        
        for (int i = 0; i < size; ++i) groups[i] = new Group();
        
        return groups;
    }
    
    private int[] toIndexArray(Group[] groups) {
        
        final int[] a = new int[groups.length];

        for (int i = 0, index = 0; i < groups.length; ++i) {
            index = groups[i].index(index);
            a[i] = groups[i].index();
        }
        
        return a;
    }
    
    private Collection<int[]> patterns(int i, int groups, int[] pattern, Collection<int[]> patterns) {
    
        if (++i == pattern.length - 1) {
//            System.out.println(Arrays.toString(pattern));
            patterns.add(pattern.clone());
        } else {
            
            pattern[i] = 0;
            for (int g = 0; g < groups; ) {
                patterns(i, groups, pattern, patterns);
                pattern[i] = ++g;
            }
            patterns(i, groups + 1, pattern, patterns);
        }
        
        return patterns;
    }
    
    /**
     * @param args
     */
    public static void main(String[] args) {

        Euler.run(new Problem289());
    }

}
