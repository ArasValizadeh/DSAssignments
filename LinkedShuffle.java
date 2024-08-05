import java.util.Scanner ;
class Node {
    public Node next;
    public String name;
    Node(String name) {
        this.name = name;
        this.next = null;
    }
}

class LinkedList {
    Node first;
    Node last;
    LinkedList (){
        this.first = null;
        this.last = null;
    }
    void insertLast(String name) {
        Node node = new Node(name) ;
        if(last == null && first== null) {
            first = node ;
        }else{
            last.next = node ;
        }
        last = node ;
    }
    void insertFirst(String name){
        Node node = new Node(name);
        Node q = first ;
        node.next = q ;
        first = node ;
    }

    LinkedList reverseLinkedList() {
        Node current = first;
        LinkedList linkedList = new LinkedList();
        while (current != null) {
            linkedList.insertFirst(current.name);
            current = current.next;
        }
        return linkedList ;
    }

    public void doLinkedShuffle() {
        LinkedList reversedList = this.reverseLinkedList();
        LinkedList result = new LinkedList() ;
        Node current1 = this.first ;
        Node currentLen = this.first ;
        Node current2 = reversedList.first ;
        int len = 0 ;
        while(currentLen != null){
            len ++ ;
            currentLen = currentLen.next ;
        }
        int counter = 0 ;
        while (current1 != null && current2!= null) {
                if (current2.name.equals(current1.name) && len - counter <= 2){
                    result.insertLast(current1.name);
                    break ;
                }
                if (current2.next.name.equals(current1.name) && len - counter <= 2){
                    result.insertLast(current2.next.name);
                    result.insertLast(current1.next.name);
                    break;
                }
                result.insertLast(current1.name);
                result.insertLast(current2.name);
                counter += 2 ;
                current1 = current1.next;
                current2 = current2.next;
        }
        this.first = result.first ;
    }

    void printLinkedList () {
        StringBuilder myString = new StringBuilder();
        Node q = first;
        if (q != null){
            myString.append(q.name);
            while (q.next != null) {
                q = q.next;
                myString.append(" -> ").append(q.name);
            }
        }
        System.out.println(myString);
    }
    static LinkedList convertStringToLinkedList(String s){
        String[] splited = s.split(" -> ");
        LinkedList result = new LinkedList();
        for(String number : splited){
            result.insertLast(number);
        }
        return result ;
    }
    static boolean check (Node q , Node l){
        while(q!=null && l!=null){
            if (!q.name.equals(l.name)){
                return false ;
            }
            q = q.next ;
            l = l.next ;
        }
        return true ;
    }
}

public class LinkedShuffle {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int Line1 = scanner.nextInt();
        scanner.nextLine();
        String Line2 = scanner.nextLine();
        int Line3 = scanner.nextInt();
        LinkedList linkedList = LinkedList.convertStringToLinkedList(Line2);
        LinkedList linkedList1 = LinkedList.convertStringToLinkedList(Line2);
        Node l = linkedList.first ;
        int mod = 0 ;
        boolean flag = true ;
        while(flag){
            mod ++ ;
            linkedList1.doLinkedShuffle();
            Node q = linkedList1.first ;
            if (LinkedList.check(q, l)){
                break ;
            }
        }
        for (int i = 0 ; i< Line3 % mod ; i++){
            linkedList.doLinkedShuffle();
        }
        linkedList.printLinkedList();
    }
}

