import java.util.Scanner ;
class Stack{
    private int top = -1;
    private final int[] array ;
    private final int len ;
    Stack (int n){
        len = n ;
        array = new int[n] ;
    }
    void push (int element){
        if (top != len-1){
            array[++top] = element ;
        }
    }
    void pop (){
        if (top != -1){
            top --;
        }
    }
    int top(){
        if (top != -1) {
            return array[top];
        }
        return -1 ;
    }
}
class circularQueue{
    private final int[] array ;
    private final int len ;
    private int first = 0 ;
    private int last = 0 ;
    private int zeroCount ;
    private int oneCount ;
    circularQueue (int n){
        len = n * 2 ;
        array = new int[len];
    }
    void enqueue (int element){
        if ((last + 1) % len != first){
            last = (last + 1) % len ;
            array[last] = element ;
        }
    }
    void enqueue2 (int element){
        if ((last + 1) % len != first){
            last = (last + 1) % len ;
            if (element == 1){
                oneCount ++;
            }
            else{
                zeroCount ++;
            }
            array[last] = element ;
        }
    }
    int deque(){
        if (first != last){
            first = (first + 1 ) % len ;
            return array[first] ;
        }
        return -1;
    }
    int front (){
        return array[(first+1) % len];
    }

    int calculate (Stack stack){
        int counter = 0 ;
        while(true) {
            if (((stack.top() == 0 && zeroCount == 0) || (stack.top() == 1 && oneCount == 0)) || stack.top() == -1){
                break ;
            }
            if (this.front() == stack.top()) {
                int hazf = this.front();
                this.deque();
                stack.pop();
                counter ++ ;
                if (hazf == 1) {
                    oneCount--;
                }else {
                    zeroCount--;
                }
            } else{
                int x = this.deque();
                this.enqueue(x);
            }
        }
        return len/2 - counter ;
    }

}
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input1 = scanner.nextLine();
        int len1 = input1.length()-1;
        String input2 = scanner.nextLine();
        int len2 = input2.length()-1 ;
        input1 = input1.substring(1,len1) ;
        input2 = input2.substring(1,len2);
        StringBuilder input2Reversed = new StringBuilder();
        for (int i=0; i<input2.length(); i++)
        {
            char ch= input2.charAt(i);
            input2Reversed.insert(0, ch);
        }
        String[] array1 = input1.split(",");
        String[] array2 = input2Reversed.toString().split(",");
        Stack stack = new Stack(array2.length);
        for (String s : array2){
            stack.push(Integer.parseInt(s));
        }
        circularQueue circularQueue = new circularQueue(array1.length);
        for (String s : array1){
            circularQueue.enqueue2(Integer.parseInt(s));
        }
        System.out.println(circularQueue.calculate(stack));
    }
}