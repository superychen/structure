package cqyc.structure.array;

/**
 * @author cqyc
 * @Description: 使用泛型
 * @date 2020/12/31
 */
public class ArrayYcTwo<T> {
    private T[] data;

    private int size;

    //有参构造，传入数组的容量capacity构造array
    public ArrayYcTwo(int capacity) {
        data = (T[]) new Object[capacity];
        size = 0;
    }

    //无参构造，初始值10
    public ArrayYcTwo() {
        this(10);
    }

    public int getSize() {
        return size;
    }

    public int getCapacity() {
        return data.length;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    //向索引的最后一个位置插入值
    public void addLast(T t) {
        add(size, t);
    }

    public void addFirst(T t) {
        add(0, t);
    }

    //向指定位置添加元素，插入位置后面所有元素统一往后面挪一位
    public void add(int index, T t) {
        if (size == data.length) {
            throw new IllegalArgumentException("当前数组长度已经超过所设置的长度");
        }
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("索引必须为正数且不能大于该数组的长度");
        }
        for (int i = size - 1; i >= index; i--) {
            data[i + 1] = data[i];
        }
        data[index] = t;
        size++;
    }

    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("get failed, 传入参数有错误");
        }
        return data[index];
    }

    public void set(int index, T t){
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("get failed, 传入参数有错误");
        }
        data[index] = t;
    }

    //查找数组中是否有元素e
    public boolean contains(T t) {
        for (int i = 0; i < size; i++) {
            if (data[i].equals(t)) {
                return true;
            }
        }
        return false;
    }

    //查找数组中元素e所在的索引，如果不存在元素e,则返回-1
    public int find(T t) {
        for (int i = 0; i < size; i++) {
            if (data[i].equals(t)) {
                return i;
            }
        }
        return -1;
    }

    //从数组中删除index位置的元素，返回删除的元素
    public T remove(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("remove failed, index is illegal");
        }
        T ret = data[index];
        for (int i = index + 1; i < size; i++) {
            data[i - 1] = data[i];
        }
        size--;
        //释放最后一个泛型类型的空间， loitering objects != memory leak(内存泄漏)
        data[size] = null;
        return ret;
    }

    public T removeFirst() {
        return remove(0);
    }

    public T removeLast() {
        return remove(size - 1);
    }

    //从数组中删除元素e
    public void removeElement(T e) {
        int index = find(e);
        if (index != -1) {
            remove(index);
        }
    }


    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("Array: size = %d, capacity = %d\n", size, data.length));
        builder.append("[");
        for (int i = 0; i < size; i++) {
            builder.append(data[i]);
            if (i != size - 1) {
                builder.append(", ");
            }
        }
        builder.append("]");
        return builder.toString();
    }
}
