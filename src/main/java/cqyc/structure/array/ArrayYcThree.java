package cqyc.structure.array;


/**
 * @author cqyc
 * @Description: 说实话，动态数组搞一搞
 * @date 2020/12/31
 */
public class ArrayYcThree<T> {
    private T[] data;

    private int size;

    //有参构造，传入数组的容量capacity构造array
    public ArrayYcThree(int capacity) {
        data = (T[]) new Object[capacity];
        size = 0;
    }

    public ArrayYcThree(T[] arr) {
        data = (T[]) new Object[arr.length];
        for (int i = 0; i < arr.length; i++) {
            data[i] = arr[i];
        }
        size = arr.length;
    }

    //无参构造，初始值10
    public ArrayYcThree() {
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
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("索引必须为正数且不能大于该数组的长度");
        }
        if (size == data.length) {
            //继续扩容
            resize(2 * data.length);
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
        //增加懒加载方式，当数据减少到4倍的时候在进行缩减
        if (size == data.length / 4 && data.length / 2 != 0) {
            resize(data.length / 2);
        }
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

    private void resize(int newCapacity) {
        T[] newData = (T[]) new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            newData[i] = data[i];
        }
        data = newData;
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

    public T getLast() {
        return get(size - 1);
    }

    public T getFirst() {
        return get(0);
    }

    /**
     * 交换两个索引的值
     */
    public void swap(int i, int j) {
        if (i < 0 || i >= size || j < 0 || j >= size) {
            throw new IllegalArgumentException("Index is illegal");
        }
        T t = data[i];
        data[i] = data[j];
        data[j] = t;
    }
}
