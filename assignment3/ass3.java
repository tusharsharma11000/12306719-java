// TASK 1: MyList Interface
interface MyList<T> {
    void add(T element);
    void insert(T element, int index);
    T get(int index);
    void delete(int index);
    int size();
    boolean isEmpty();
}

// TASK 2: MyArrayList Implementation
class MyArrayList<T> implements MyList<T> {

    private Object[] data;
    private int size;
    private int capacity;

    public MyArrayList() {
        capacity = 1;
        data = new Object[capacity];
        size = 0;
    }

    private void resize() {
        capacity *= 2;
        Object[] newData = new Object[capacity];

        for (int i = 0; i < size; i++) {
            newData[i] = data[i];
        }

        data = newData;
    }

    @Override
    public void add(T element) {
        if (size == capacity) {
            resize();
        }
        data[size++] = element;
    }

    @Override
    public void insert(T element, int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index out of range");
        }

        if (size == capacity) {
            resize();
        }

        for (int i = size; i > index; i--) {
            data[i] = data[i - 1];
        }

        data[index] = element;
        size++;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T get(int index) {
        if (isEmpty()) {
            throw new IllegalStateException("List is empty");
        }

        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of range");
        }

        return (T) data[index];
    }

    @Override
    public void delete(int index) {
        if (isEmpty()) {
            throw new IllegalStateException("List is empty");
        }

        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of range");
        }

        for (int i = index; i < size - 1; i++) {
            data[i] = data[i + 1];
        }

        size--;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }
}

// TASK 3: MyLinkedList Implementation
class MyLinkedList<T> implements MyList<T> {

    private class Node {
        T data;
        Node next;

        Node(T data) {
            this.data = data;
        }
    }

    private Node head;
    private int size;

    @Override
    public void add(T element) {
        Node newNode = new Node(element);

        if (head == null) {
            head = newNode;
        } else {
            Node temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = newNode;
        }

        size++;
    }

    @Override
    public void insert(T element, int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index out of range");
        }

        Node newNode = new Node(element);

        if (index == 0) {
            newNode.next = head;
            head = newNode;
        } else {
            Node temp = head;
            for (int i = 0; i < index - 1; i++) {
                temp = temp.next;
            }
            newNode.next = temp.next;
            temp.next = newNode;
        }

        size++;
    }

    @Override
    public T get(int index) {
        if (isEmpty()) {
            throw new IllegalStateException("List is empty");
        }

        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of range");
        }

        Node temp = head;
        for (int i = 0; i < index; i++) {
            temp = temp.next;
        }

        return temp.data;
    }

    @Override
    public void delete(int index) {
        if (isEmpty()) {
            throw new IllegalStateException("List is empty");
        }

        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of range");
        }

        if (index == 0) {
            head = head.next;
        } else {
            Node temp = head;
            for (int i = 0; i < index - 1; i++) {
                temp = temp.next;
            }
            temp.next = temp.next.next;
        }

        size--;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }
}

// TASK 6: Driver Class
public class ass3 {

    // Wildcard method
    public static void printList(MyList<?> list) {
        System.out.println("List size: " + list.size());
        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i) + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {

        try {
            MyList<Integer> list1 = new MyArrayList<>();
            MyList<String> list2 = new MyLinkedList<>();

            // Successful operations
            list1.add(10);
            list1.add(20);
            list1.insert(15, 1);

            list2.add("A");
            list2.add("B");
            list2.delete(0);

            printList(list1);
            printList(list2);

            // Exception generation
            list1.get(10); // Invalid index

        } catch (IndexOutOfBoundsException e) {
            System.out.println("Caught Exception: " + e.getMessage());

        } catch (IllegalStateException e) {
            System.out.println("Caught Exception: " + e.getMessage());

        } finally {
            System.out.println("Program executed successfully.");
        }
    }
}
