import java.util.Scanner ;
class Node {
    public Node next;
    public int value;
    Node(int value) {
        this.value = value;
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
    void insertLast(int value) {
        Node node = new Node(value) ;
        if(last == null && first== null) {
            first = node ;
        }else{
            last.next = node ;
        }
        last = node ;
    }

    public void addAll(int[] array) {
        LinkedList result = new LinkedList();
        for(int number : array){
            result.insertLast(number);
        }
        this.first = result.first ;
        this.last = result.last ;
    }
}

public class SlitherIO {
    public static int[][] playCircleOfDeath(int r, int c, Node head) {
        int[][] result = new int[r][c];
        int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int directionIndex = 0;
        int row = 0, col = 0;
        while (head != null) {
            result[row][col] = head.value;
            int newRow = row + directions[directionIndex][0];
            int newCol = col + directions[directionIndex][1];
            if (newRow >= 0 && newRow < r && newCol >= 0 && newCol < c && result[newRow][newCol] == 0) {
                row = newRow;
                col = newCol;
                head = head.next;
            } else {
                directionIndex = (directionIndex + 1) % 4;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] inputAdad = scanner.nextLine().split(" ");
        int r = Integer.parseInt(inputAdad[0]);
        int c = Integer.parseInt(inputAdad[1]);
        String input = scanner.nextLine();
        String[] splited = input.split(" ");
        int[] array = new int[splited.length];
        int counter = 0 ;
        for (String s : splited){
            array[counter] = Integer.parseInt(s);
            counter ++ ;
        }
        LinkedList linkedList = new LinkedList();
        linkedList.addAll(array);
        int[][] result = playCircleOfDeath(r, c, linkedList.first);
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (result[i][j] == 0 ){
                    System.out.print(-1 + " ");
                }else {
                    System.out.print(result[i][j] + " ");
                }
            }
            System.out.println();
        }
    }
}
