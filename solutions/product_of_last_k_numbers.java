import java.util.*;


/*
 * O(1) Solution
 */


class ProductOfNumbersK {

    List<Integer> productStream;
    int totalSize;

    /*
     * Intitalize the class with neccessary variables
     * productStream ---> stores the product of all the num after a zero appeared
     * totalSize ---> stores the total elements added till now
     */
    public ProductOfNumbersK() {
        productStream = new ArrayList<>();
        totalSize = 0;
    }

    /*
     * Add the Elements in the productStream
     * if number is zero the starts new product Stream
     * else stores the product of num and last stored value in stream
     */
    public void add(int num) {
        totalSize++;
        if (num == 0) {
            productStream = new ArrayList<>();
            return;
        }
        int size = productStream.size();

        int lastProduct = 1;
        if (size != 0)
            lastProduct = productStream.get(size - 1);

        productStream.add(lastProduct * num);
    }


    /*
     * return the product of last k numbers as per stored in stream array
     * if in between a zero is appeared it will return 0
     */
    public int getProduct(int k) {

        int size = productStream.size();
        if (size < k && totalSize >= k)
            return 0;

        int lastProduct = productStream.get(size - 1);
        if (size == k)
            return lastProduct;
        int previousKProduct = productStream.get(size - k - 1);
        return (lastProduct / previousKProduct);
    }
}


/*
 * O(K) Solution
 * 
 */

class ProductOfNumbers {

    List<Integer> list ;

    /*
     * Intializes necessary variables
     * list ---> stores the  all the num;
     */
    public ProductOfNumbers() {
        list = new ArrayList<>();
    }
    

    /*
     * add num to list
     */
    public void add(int num) {
        list.add(num);
    }
    

    /*
     * calculates the product of last k numbers by iterating over it
     */
    public int getProduct(int k) {
        int size = list.size();
        int min = Math.max(0, size-k);
        int ans = 1;
        for(int i = min; i<size; i++)
            ans *= list.get(i);

        return ans ;
    }
}


/**
 * Your ProductOfNumbers object will be instantiated and called as such:
 * ProductOfNumbers obj = new ProductOfNumbers();
 * obj.add(num);
 * int param_2 = obj.getProduct(k);
 */