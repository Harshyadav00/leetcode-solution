class Solution {
    public int findChampion(int n, int[][] edges) {
        int[] nodes = new int[n];

        // count in-degree of each node
        for (int[] edge : edges) {
            nodes[edge[1]]++;
        }

        int strongestNodeValue = Integer.MAX_VALUE;
        int strongestNode = 0;

        // find the node with lowest in degree
        // and if lowest in-degree is for multiple node the ind = -1
        for (int i = 0; i < n; i++) {
            if (strongestNodeValue > nodes[i]) {
                strongestNodeValue = nodes[i];
                strongestNode = i;
            } else if (strongestNodeValue == nodes[i]) {
                strongestNode = -1;
            }
        }

        return strongestNode;

    }
}