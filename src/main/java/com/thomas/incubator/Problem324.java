/**
 * $Id$
 *
 * Copyright (c) 2011 Thomas Beckmann
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

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import com.thomas.util.Euler;
import com.thomas.util.Euler.Problem;
import com.thomas.util.matrix.IntColVector;
import com.thomas.util.matrix.IntMatrix;
import com.thomas.util.matrix.IntRowVector;

/**
 * @author Thomas Beckmann
 * @since 13.02.2011
 */
public class Problem324 implements Problem {

    /**
     * {@inheritDoc}
     */
    @Override
    public Object solve() {

        final Map<int[][], Integer> profiles = new TreeMap<int[][], Integer>(new Comparator<int[][]>() {

            @Override
            public int compare(int[][] o1, int[][] o2) {

                int diff = 0;
                
                for (int y = 0; y < 3; ++y) {
                    for (int x = 0; x < 3; ++x) {
                        if ((diff = o1[x][y] - o2[x][y]) != 0) return diff;
                    }
                }
                
                return 0;
            }
            
        });
        
        System.out.println(fill(new int[3][3], 0, profiles));
        System.out.println(profiles.size());
        List<Entry<int[][], Integer>> a = new ArrayList<Map.Entry<int[][],Integer>>(profiles.entrySet());
        
        final int[][] matrix = new int[a.size()][a.size()];
        final int[] start = new int[a.size()];
        final int[] end = new int[a.size()];
        
        for (int i = 0; i < a.size(); ++i) {
            if (isBottom(a.get(i).getKey())) {
                start[i] = a.get(i).getValue();
            }
            if (isTop(a.get(i).getKey())) {
                end[i] = 1;
            }
            for (int j = 0; j < a.size(); ++j) {
                if (fitsBelow(a.get(i).getKey(), a.get(j).getKey())) {
                    matrix[i][j] = a.get(j).getValue();
                }
            }
        }

        IntRowVector s = new IntRowVector(start);
        IntMatrix m = new IntMatrix(matrix);
        IntColVector e = new IntColVector(end);
        
        return s.modMultiply(m.modPow(9, 100000007), 100000007).modMultiply(e, 100000007).at(0, 0);
    }

    private boolean isBottom(int[][] board) {
        
        for (int y = 0; y < 3; ++y) {
            for (int x = 0; x < 3; ++x) {
                if (board[x][y] == 0) return false;
            }
        }
        
        return true;
    }
    
    private boolean isTop(int[][] board) {
        
        for (int y = 0; y < 3; ++y) {
            for (int x = 0; x < 3; ++x) {
                if (board[x][y] == 2) return false;
            }
        }
        
        return true;
    }
    
    private boolean fitsBelow(int[][] bottom, int[][] top) {
    
        for (int y = 0; y < 3; ++y) {
            for (int x = 0; x < 3; ++x) {
                if ((bottom[x][y] < 2 && top[x][y] == 0) || (bottom[x][y] == 2 && top[x][y] > 0)) return false;
            }
        }
        
        return true;
    }
    
    private long fill(int[][] board, int i, Map<int[][], Integer> profiles) {
        
        if (i == 9) {
            
            final int[][] profile = makeProfile(board);
            
            Integer count = profiles.get(profile);
            
            if (count == null) {
                profiles.put(profile, 1);
            } else {
                profiles.put(profile, count + 1);
            }
            return 1;
        }
        
        long count = 0;
        
        int x = i % 3;
        int y = i / 3;
        
        if ((y > 0 && board[x][y - 1] == 4) && (x > 0 && board[x - 1][y] == 3)) return 0;
        if (y > 0 && board[x][y - 1] == 4) {
            if (x > 0 && board[x - 1][y] == 3) return 0;
            board[x][y] = 2;
            count += fill(board, i + 1, profiles);
        } else if (x > 0 && board[x - 1][y] == 3) {
            board[x][y] = 5;
            count += fill(board, i + 1, profiles);
        } else {
            board[x][y] = 0;
            count += fill(board, i + 1, profiles);
            board[x][y] = 1;
            count += fill(board, i + 1, profiles);
            if (x < 2) {
                board[x][y] = 3;
                count += fill(board, i + 1, profiles);
            }
            if (y < 2) {
                board[x][y] = 4;
                count += fill(board, i + 1, profiles);
            }
        }
        
        return count;
    }
    
    private int[][] makeProfile(int[][] board) {
    
        final int[][] profile = new int[3][3];
        
        for (int y = 0; y < 3; ++y) {
            for (int x = 0; x < 3; ++x) {
                if (board[x][y] == 0) profile[x][y] = 0;
                else if (board[x][y] == 1) profile[x][y] = 2;
                else profile[x][y] = 1;
            }
        }
        
        return profile;
    }
    
    private void print(int[][] board) {
        
        for (int y = 0; y < 3; ++y) {
            for (int x = 0; x < 3; ++x) {
                System.out.print(board[x][y]);
            }
            System.out.println();
        }
        System.out.println();
    }

    /**
     * @param args
     */
    public static void main(String[] args) {

        Euler.run(new Problem324());
    }

}
