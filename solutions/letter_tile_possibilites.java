import java.util.*;

class Solution {
    public int numTilePossibilities(String tiles) {
        // O(n! * n) solution
        // return traverseAllPermutations(tiles, 0, new HashSet<Integer>(), new StringBuilder(), new HashSet<String>());

        // Time Complexity : ??
        return constructFreqMap(tiles);
    }

    public int constructFreqMap(String tiles) {
        Map<Character, Integer> freqMap = new HashMap<>();
        for (char ch : tiles.toCharArray()) {
            freqMap.put(ch, freqMap.getOrDefault(ch, 0) + 1);
        }

        List<Character> uniqueChar = new ArrayList<>();
        for (Map.Entry<Character, Integer> entry : freqMap.entrySet()) {
            uniqueChar.add(entry.getKey());
        }

        return possibleSequence(freqMap, uniqueChar, new ArrayList<>(), 0);

    }

    public int possibleSequence(Map<Character, Integer> freqMap, List<Character> uniqueChar, List<Integer> list,
            int ind) {
        if (ind == freqMap.size()) {
            return calculatePossibilites(list);
        }

        int validCombinations = 0;
        char key = uniqueChar.get(ind);
        for (int i = 0; i <= freqMap.get(key); i++) {
            if (i != 0)
                list.add(i);
            validCombinations += possibleSequence(freqMap, uniqueChar, list, ind + 1);
            if (i != 0)
                list.remove(list.size() - 1);
        }

        return validCombinations;
    }

    int calculatePossibilites(List<Integer> list) {

        if (list.size() < 1)
            return 0;

        int sum = 0;
        int factorialProduct = 1;
        for (int num : list) {
            sum += num;
            factorialProduct *= fact(num);
        }

        return fact(sum) / factorialProduct;
    }

    int fact(int num) {
        if (num == 0 || num == 1) {
            return 1;
        }

        return num * fact(num - 1);
    }

    // private int traverseAllPermutations(String tiles, int ind, Set<Integer>
    // sequenceIndContent, StringBuilder sequence,
    // Set<String> uniqueSequence) {
    // int n = tiles.length();
    // if (ind == n) {
    // if (sequence.length() > 0 && !uniqueSequence.contains(sequence.toString())) {
    // uniqueSequence.add(sequence.toString());
    // return 1;
    // }
    // return 0;
    // }

    // int validCombinations = 0;

    // for (int i = 0; i < n; i++) {
    // if (sequenceIndContent.contains(i))
    // continue;

    // sequenceIndContent.add(i);
    // sequence.append(tiles.charAt(i));
    // validCombinations += traverseAllPermutations(tiles, ind + 1,
    // sequenceIndContent, sequence, uniqueSequence);
    // sequence.deleteCharAt(sequence.length() - 1);
    // sequenceIndContent.remove(i);
    // }

    // validCombinations += traverseAllPermutations(tiles, ind + 1,
    // sequenceIndContent, sequence, uniqueSequence);

    // return validCombinations;

    // }
}