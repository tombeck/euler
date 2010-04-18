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
package com.thomas.problem60;

import static com.thomas.util.PrimeUtils.isPrime;
import static java.lang.Integer.parseInt;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import com.thomas.util.Euler;
import com.thomas.util.PrimeUtils;
import com.thomas.util.Euler.Problem;

/**
 * TODO Type documentation
 * 
 * @author Thomas
 * @since 29.11.2009
 */
public class Problem60 implements Problem {

    static final class Solution implements Comparable<Solution> {
    
        public static final Solution EMPTY = new Solution();
        
        final Set<Node> nodes;
        final int sum;
        final int highest;
        
        private Solution(Solution solution, Node node) {
            
            this.nodes = new HashSet<Node>(solution.nodes);
            this.sum = solution.sum + node.value;
            this.highest = node.value;

            this.nodes.add(node);
        }
        
        private Solution() {
            
            this.nodes = new HashSet<Node>();
            this.sum = 0;
            this.highest = 0;
        }

        public boolean isComplete() {
        
            return this.nodes.size() == 5;
        }
        
        public Solution add(Node node) {
            
            return new Solution(this, node);
        }
        
        @Override
        public int compareTo(Solution other) {

            return other == null ? -1 : this.sum - other.sum;
        }
        
    }
    
    static final class Node {
        
        public static int checksum(int n) {
        
            int checksum = 0;
            
            for (; n > 0; n /= 10) checksum += n % 10;

            return checksum;
        }
        
        final int value;
        final Set<Node> edges = new LinkedHashSet<Node>();
        final String sValue;
        final int checksum;
        
        public Node(int value) {

            this.value = value;
            this.sValue = Integer.toString(this.value);
            this.checksum = checksum(this.value) % 3;
        }
        
        public void connect(Node other, List<Integer> primes) {
            
            if (this.checksum != other.checksum) return;
            if (isPrime(parseInt(this.sValue + other.sValue), primes)
                    && isPrime(parseInt(other.sValue + this.sValue), primes)) {
                this.edges.add(other);
                other.edges.add(this);
            }
        }
     
        public Solution findClique(Solution current, Solution best) {

            if (!this.edges.containsAll(current.nodes)) return best;
            
            current = current.add(this);
            
            if (current.compareTo(best) > 0) return best;
            if (current.isComplete()) return current;
            
            for (Node node : this.edges) {
                if (node.value > current.highest) best = node.findClique(current, best);
            }
            
            return best;
        }
        
    }
    
    /**
     * TODO Method documentation
     * 
     * @return
     * @see com.thomas.util.Euler.Problem#solve()
     * @author Thomas
     * @since 09.02.2010
     */
    @Override
    public Integer solve() {

        final List<Integer> primes = PrimeUtils.primes(10000);
        final List<Node> nodes = new ArrayList<Node>();
        
        for (int prime : primes) nodes.add(new Node(prime));
        for (int i = 1, size = nodes.size(); i < size; ++i) {
            for (int j = i + 1; j < size; ++j) {
                nodes.get(i).connect(nodes.get(j), primes);
            }
        }
        
        Solution solution = null;
        
        for (Node node : nodes) solution = node.findClique(Solution.EMPTY, solution);
        
        return solution.sum;
    }

    /**
     * TODO Method documentation
     * 
     * @param args
     * @author Thomas
     * @throws InterruptedException 
     * @since 29.11.2009
     */
    public static void main(String[] args) throws InterruptedException {

        Euler.run(new Problem60());
   }

}
