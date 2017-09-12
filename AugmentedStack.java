

/**
 * Using array for data structure can help us obtain O(1) when randomly accessing elements.
 * IN this generic stack type, the top index and min index shoule be stored.
 *
 */
public class AugmentedStack<T> {

    private final int MAXSIZE = 512;

    private int minIndex = -1; // index of the min element
    private int topIndex = -1; // index of the top element
    private Comparable<T>[] stack = new Comparable[MAXSIZE]; // a stack of max size 512 elements

    /**
     * add the element to tail of the array and increase the index & update min element if it is
     *
     * @param x
     */
    public void push(Comparable<T> x){
        stack[++topIndex] = x; // increase topIndex first (start with -1) and make the assignment
        // obtain the min element and compare with current x
        Comparable min = this.getMin();
        if(min != null){
            if(min.compareTo(x) > 0){
                minIndex = topIndex; // update min index to x
            }
        }else{
            minIndex = topIndex;
        }
    }

    // return the top element and remove it from stack
    public Comparable<T> pop(){
        if(topIndex < 0) return null;
        Comparable obj = stack[topIndex]; // hold the value
        stack[topIndex--] = null; // set null to topIndex and decrease by 1
        return obj;
    }

    public Comparable<T> getMin(){
        return (topIndex < 0 || minIndex < 0) ? null : stack[minIndex]; // directly access the min index O(1)
    }

    public boolean isEmpty(){
        return topIndex < 0 ? true : false;
    }

    // only return the top element but not remove it
    public Comparable<T> top(){
        return topIndex < 0 ? null : stack[topIndex]; // directly access the top index O(1)
    }

    // ONLY for test
    public static void main(String[] args){
        AugmentedStack<Integer> intStack = new AugmentedStack();
        System.out.println("min:" + intStack.getMin());
        System.out.println("isEmpty:" + intStack.isEmpty());
        intStack.push(2);
        System.out.println("isEmpty:" + intStack.isEmpty());
        intStack.push(4);
        System.out.println("min:" + intStack.getMin());
        intStack.push(7);
        System.out.println("pop:" + intStack.pop());
        intStack.push(1);
        System.out.println("min:" + intStack.getMin());
        intStack.push(3);
        System.out.println("min:" + intStack.getMin());
        System.out.println("pop:" + intStack.pop());
        System.out.println("pop:" + intStack.pop());
        System.out.println("pop:" + intStack.pop());
        System.out.println("pop:" + intStack.pop());
        System.out.println("pop:" + intStack.pop());
        System.out.println("pop:" + intStack.pop());
    }
}
