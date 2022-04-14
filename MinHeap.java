import java.util.ArrayList;
import java.util.Collections;

public class MinHeap {

    private ArrayList<Node> heap;


    public MinHeap() {
        heap = new ArrayList<>();

        heap.add(null);
    }


    public void insert(Node val) {
        heap.add(val);
        int childPos = heap.size() - 1;
        int parentPos = childPos / 2;

        while (parentPos >= 1 && heap.get(childPos).txt < heap.get(parentPos).txt) {
            Collections.swap(heap, childPos, parentPos);

            childPos = parentPos;
            parentPos = childPos / 2;
        }
    }


    public boolean isEmpty() {
        return (heap.size() <= 1);
    }


    public Node MinNode() {

        if (isEmpty()) return null;
        Node min = heap.get(1);
        int top = heap.size() - 1;


        heap.set(1, heap.get(top));
        heap.remove(top);


        int parentPos = 1;
        int leftPos = parentPos * 2;
        int rightPos = parentPos * 2 + 1;

        while (leftPos <= heap.size() - 1) {
            int index;

            if (rightPos > heap.size() - 1) {

                if (heap.get(leftPos).txt >= heap.get(parentPos).txt)
                    break;
                index = leftPos;
            }
            else {

                if (heap.get(leftPos).txt >= heap.get(parentPos).txt
                        && heap.get(rightPos).txt >= heap.get(parentPos).txt) break;

                index = (heap.get(leftPos).txt < heap.get(rightPos).txt) ? leftPos : rightPos;
            }
            Collections.swap(heap, index, parentPos);

            parentPos = index;
            leftPos = parentPos * 2;
            rightPos = parentPos * 2 + 1;
        }
        return min;
    }

}
