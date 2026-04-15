package Day52.huffmancoding;

import java.util.ArrayList;

class Heap<T extends Comparable<T>> {

    /*
    Generic Min Heap using ArrayList
    T extends Comparable<T>:
    → T must implement Comparable
    → This allows compareTo() between elements
    → Works with any type: Integer, Node, String, etc.

    Why ArrayList not array?
    → Dynamic sizing (no fixed capacity)
    → Easy remove from end (remove(size-1))
    → No need for manual resize
    */

    private ArrayList<T> list;

    public Heap() {
        list = new ArrayList<>();
    }

    private void swap(int first, int second) {
        T temp = list.get(first);
        list.set(first, list.get(second));
        list.set(second, temp);
    }

    public int size() {
        return list.size();
    }

    private int parent(int index) { return (index - 1) / 2; }
    private int left(int index) { return index * 2 + 1; }
    private int right(int index) { return index * 2 + 2; }

    public void insert(T value) {
        list.add(value);            // Add at end
        upheap(list.size() - 1);   // Fix heap property upward
    }

    private void upheap(int index) {
        if (index == 0) return;     // Reached root, stop

        int p = parent(index);

        // If current < parent (min heap property violated)
        if (list.get(index).compareTo(list.get(p)) < 0) {
            swap(index, p);
            upheap(p);              // Continue fixing upward
        }
    }

    public T remove() throws Exception {
        if (list.isEmpty()) {
            throw new Exception("Removing from an empty heap!");
        }

        T temp = list.get(0);                   // Save root (minimum)
        T last = list.remove(list.size() - 1);  // Remove last element

        if (!list.isEmpty()) {
            list.set(0, last);    // Move last to root
            downheap(0);          // Fix heap property downward
        }

        return temp;
    }

    private void downheap(int index) {
        int min = index;
        int left = left(index);
        int right = right(index);

        // Find smallest among current, left, right
        if (left < list.size() && list.get(min).compareTo(list.get(left)) > 0) {
            min = left;
        }
        if (right < list.size() && list.get(min).compareTo(list.get(right)) > 0) {
            min = right;
        }

        if (min != index) {
            swap(min, index);
            downheap(min);        // Continue fixing downward
        }
    }

    public ArrayList<T> heapSort() throws Exception {
        ArrayList<T> data = new ArrayList<>();
        while (!list.isEmpty()) {
            data.add(this.remove());
        }
        return data;  // Returns elements in sorted order (ascending)
    }
}