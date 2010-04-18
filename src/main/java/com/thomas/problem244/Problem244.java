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
package com.thomas.problem244;

import static com.thomas.util.BitUtils.nextPermutation;
import static com.thomas.util.BitUtils.rotateLeft;
import static com.thomas.util.BitUtils.rotateRight;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

import com.thomas.util.Euler;
import com.thomas.util.Euler.Problem;

/**
 * TODO Type documentation
 * 
 * @author Thomas
 * @since 14.03.2010
 */
public class Problem244 implements Problem {

    static class Vertex implements Comparable<Vertex> {
    
        final int configuration;
        final Vertex[] neighbors = new Vertex[4]; // up, down, left, right
        final Vertex[] previous = new Vertex[4]; // up, down, left, right
        
        int dist = Integer.MAX_VALUE;
        
        public Vertex(int configuration) {
            
            this.configuration = configuration;
        }

        @Override
        public int compareTo(Vertex o) {

            return this.dist - o.dist;
        }
        
    }
    
    /**
     * TODO Method documentation
     * 
     * @return
     * @see com.thomas.util.Euler.Problem#solve()
     * @author Thomas
     * @since 14.03.2010
     */
    @Override
    public Integer solve() {
        
        final Map<Integer, Vertex> vertices = new HashMap<Integer, Vertex>();
        
        for (int g = 0; g < 16; ++g) {
            for (int i = 255; i != 32895; i = nextPermutation(i)) {
                
                final int configuration = g | (i << 4);
                
                vertices.put(configuration, new Vertex(configuration));
            }
        }
        for (Vertex vertex : vertices.values()) {
            
            final int gap = vertex.configuration & 0xf;

            vertex.neighbors[0] = gap < 4 ? null : vertices.get(rotateLeft(vertex.configuration, gap, 4) - 4);
            vertex.neighbors[1] = gap >= 12 ? null : vertices.get(rotateRight(vertex.configuration, 4 + gap, 4) + 4);
            vertex.neighbors[2] = (gap & 0x3) == 0 ? null : vertices.get(vertex.configuration - 1);
            vertex.neighbors[3] = (gap & 0x3) == 3 ? null : vertices.get(vertex.configuration + 1);
        }
        
        final PriorityQueue<Vertex> queue = new PriorityQueue<Vertex>(vertices.size());
        
        final Vertex source = vertices.get(185040);
        
        source.dist = 0;
        queue.add(source);
        
        for (Vertex u; (u = queue.poll()) != null && u.dist != Integer.MAX_VALUE; ) {
            for (int i = 0; i < 4; ++i) {
                
                final Vertex v = u.neighbors[i];
                
                if (v != null) {
                    
                    final int dist = u.dist + 1;
                    
                    if (dist < v.dist) {
                        queue.remove(v);
                        v.dist = dist;
                        v.previous[i] = u;
                        queue.add(v);
                    }
                }
            }
        }

        return sum(vertices.get(419424), source, 0);
    }

    private static final int[] MOVE = {85, 68, 76, 82};
    
    private int sum(Vertex target, Vertex source, int cks) {
    
        if (target.equals(source)) return cks;
        
        int sum = 0;
        
        for (int i = 0; i < 4; ++i) {
            
            final Vertex v = target.previous[i];
            
            if (v != null) sum += sum(v, source, (int)((cks * 243L + MOVE[i]) % 100000007));
        }
        
        return sum;
    }
    
     /**
     * TODO Method documentation
     * 
     * @param args
     * @author Thomas
     * @since 14.03.2010
     */
    public static void main(String[] args) {

        Euler.run(new Problem244());
    }

}
