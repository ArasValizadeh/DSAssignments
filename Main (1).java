import java.util.Scanner;

class Node {
    int value;
    int index;
    public Node(int value, int index) {
        this.value = value;
        this.index = index;
    }
}

public class Main {
    static void findRange(int allElements, int arrayLen ,Node[] elements) {
        int count = 0;
        int start = 0;
        int minRange = Integer.MAX_VALUE;
        int minStart = 0;
        int[] recent = new int[arrayLen];
        for (int end = 0; end < allElements; end++) {
            if (recent[elements[end].index] == 0) {
                count++;
            }
            recent[elements[end].index]++;
            while (count == arrayLen) {
                if (elements[end].value - elements[start].value < minRange) {
                    minRange = elements[end].value - elements[start].value;
                    minStart = elements[start].value;
                }
                recent[elements[start].index]--;
                if (recent[elements[start].index] == 0 ) {
                    count--;
                }
                start++;
            }
        }
        System.out.println(minStart + " " + (minStart + minRange));
    }
    static void quickSort(Node[] elements, int low, int high) {
        if (low < high) {
            int pi = partition(elements, low, high);
            quickSort(elements, low, pi - 1);
            quickSort(elements, pi + 1, high);
        }
    }
    static int partition(Node[] elements, int low, int high) {
        Node pivot = elements[high];
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (elements[j].value < pivot.value) {
                i++;
                Node temp = elements[i];
                elements[i] = elements[j];
                elements[j] = temp;
            }
        }
        Node temp = elements[i + 1];
        elements[i + 1] = elements[high];
        elements[high] = temp;
        return i + 1;
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int arrayLen = scanner.nextInt();
        int allElements = 0;
        int[] arraySizes = new int[arrayLen];
        for (int i = 0; i < arrayLen; i++) {
            arraySizes[i] = scanner.nextInt();
            allElements += arraySizes[i];
        }
        Node[] elements = new Node[allElements];
        for (int i = 0, k = 0; i < arrayLen; i++) {
            for (int j = 0; j < arraySizes[i]; j++) {
                elements[k] = new Node(scanner.nextInt(), i);
                k++;
            }
        }
        quickSort(elements, 0, allElements - 1);
        findRange(allElements, arrayLen ,elements);
    }
}
