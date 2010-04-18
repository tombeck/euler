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
package com.thomas.problem82;

import static com.thomas.util.IOUtils.closeQuietly;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

import com.thomas.util.Euler;
import com.thomas.util.Euler.Problem;

/**
 * TODO Type documentation
 * 
 * @author Thomas
 * @since 25.10.2009
 */
public class Problem82 implements Problem {

    interface Node extends Comparable<Node> {
        
        void computeDistance(int sum);
        
        void visitNeighbours();
        
        int getRow();

        int getColumn();
        
   }
    
    /**
     * {@inheritDoc}
     * @throws IOException 
     */
    @Override
    public Integer solve() throws IOException {

        final int[][] matrix = new int[80][80];
        final int[][] graph = new int[80][80];
        final PriorityQueue<Node> queue = new PriorityQueue<Node>();

        final BufferedReader reader = new BufferedReader(new InputStreamReader(Problem82.class.getResourceAsStream ("matrix.txt")));

        try {
            
            String line;
            for (int i = 0; (line = reader.readLine()) != null; ++i) {
                final String[] cells = line.split(",");
                for (int j = 0; j < cells.length; ++j) {
                    matrix[i][j] = Integer.parseInt(cells[j]);
                    graph[i][j] = Integer.MAX_VALUE;
                }
            }
        } finally {
            closeQuietly(reader);
        }
        
        class NodeImpl implements Node {
            
            private final int row;
            private final int column;
            
            public NodeImpl(int row, int column) {

                this.row = row;
                this.column = column;
            }

            public int getRow() { return this.row; }

            public int getColumn() { return this.column; }

            public void computeDistance(int sum) {
                
                if (matrix[this.row][this.column] == -1) return; // already visited
                
                queue.remove(this);
                
                final int oldDistance = graph[this.row][this.column];
                final int newDistance = matrix[this.row][this.column] + sum;
                
                graph[this.row][this.column] = Math.min(oldDistance, newDistance);
                
                queue.add(this);
            }
            
            public void visitNeighbours() {
                int sum = graph[this.row][this.column];
                if (this.column < 80 - 1) new NodeImpl(this.row, this.column + 1).computeDistance(sum);
                //if (this.column > 0) new NodeImpl(this.row, this.column - 1).computeDistance(sum);
                if (this.row < 80 - 1) new NodeImpl(this.row + 1, this.column).computeDistance(sum);
                if (this.row > 0) new NodeImpl(this.row - 1, this.column).computeDistance(sum);
                matrix[this.row][this.column] = -1;
                //System.out.println(this.row + "/" + this.column);
            }
            
            @Override
            public int compareTo(Node o) {

                return graph[this.row][this.column] - graph[o.getRow()][o.getColumn()];
            }

            @Override
            public int hashCode() {

                int result = 31 + this.column;
                
                result = 31 * result + this.row;
                
                return result;
            }

            @Override
            public boolean equals(Object obj) {

                if (this == obj) return true;
                if (obj == null) return false;
                if (getClass() != obj.getClass()) return false;
                
                final NodeImpl other = (NodeImpl)obj;
                
                return this.column == other.column && this.row == other.row;
            }
            
            
        }
        
        for (int i = 0; i < 80; ++i) {
            new NodeImpl(i, 0).computeDistance(0);
        }
        
        Node currentNode;
        
        while((currentNode = queue.poll()) != null) {
            //if (currentNode.getRow() == 79 && currentNode.getColumn() == 79) break;
            currentNode.visitNeighbours();
        }
        
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < 80; ++i) {
            min = Math.min(min, graph[i][79]);
        }
        
        return min;
    }

    /**
     * TODO Method documentation
     * 
     * @param args
     * @author Thomas
     * @since 25.10.2009
     */
    public static void main(String[] args) {

        Euler.run(new Problem82());
    }

}
