package cqyc.structure.array;

/**
 * @author cqyc
 * @Description:
 * @date 2020/12/31
 */
public class ArrayYc {
    private int[] data;

    private int size;

    //有参构造，传入数组的容量capacity构造array
    public ArrayYc(int capacity) {
        data = new int[capacity];
        size = 0;
    }

    //无参构造，初始值10
    public ArrayYc() {
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
    public void addLast(int e) {
        add(size, e);
    }

    public void addFirst(int e) {
        add(0, e);
    }

    //向指定位置添加元素，插入位置后面所有元素统一往后面挪一位
    public void add(int index, int e) {
        if (size == data.length) {
            throw new IllegalArgumentException("当前数组长度已经超过所设置的长度");
        }
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("索引必须为正数且不能大于该数组的长度");
        }
        for (int i = size - 1; i >= index; i--) {
            data[i + 1] = data[i];
        }
        data[index] = e;
        size++;
    }

    public int get(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("get failed, 传入参数有错误");
        }
        return data[index];
    }

    public void set(int index, int e){
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("get failed, 传入参数有错误");
        }
        data[index] = e;
    }

    //查找数组中是否有元素e
    public boolean contains(int e) {
        for (int i = 0; i < size; i++) {
            if (data[i] == e) {
                return true;
            }
        }
        return false;
    }

    //查找数组中元素e所在的索引，如果不存在元素e,则返回-1
    public int find(int e) {
        for (int i = 0; i < size; i++) {
            if (data[i] == e) {
                return i;
            }
        }
        return -1;
    }

    //从数组中删除index位置的元素，返回删除的元素
    public int remove(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("remove failed, index is illegal");
        }
        int ret = data[index];
        for (int i = index + 1; i < size; i++) {
            data[i - 1] = data[i];
        }
        size--;
        return ret;
    }

    public int removeFirst() {
        return remove(0);
    }

    public int removeLast() {
        return remove(size - 1);
    }

    //从数组中删除元素e
    public void removeElement(int e) {
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
