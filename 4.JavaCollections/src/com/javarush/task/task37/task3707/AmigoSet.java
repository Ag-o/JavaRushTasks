package com.javarush.task.task37.task3707;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.*;

public class AmigoSet<E> extends AbstractSet<E> implements Serializable, Cloneable, Set<E> {
    //заглушка
    private static final Object PRESENT = new Object();

    //Список ключей будет нашим сэтом, а вместо значений будем пихать в мапу заглушку PRESENT.
    private transient HashMap<E, Object> map;

    public AmigoSet() {
        this.map = new HashMap<>();
    }

    public AmigoSet(Collection<? extends E> collection) {
        this.map = new HashMap<>(Math.max(16, (int) (Math.ceil(collection.size() / .75f))));
        this.addAll(collection);
    }

    @Override
    public boolean add(E e) {
        return map.put(e, PRESENT) == null;
    }

    @Override
    public Iterator iterator() {
        return map.keySet().iterator();
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return map.containsKey(o);
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public boolean remove(Object o) {
        map.remove(o);
        return super.remove(o);
    }

    @Override
    public Object clone() {
        try {
            map.clone();
        } catch (Exception e) {
            throw new InternalError();
        }
        return this;
    }

    private void writeObject(ObjectOutputStream oos) throws IOException {
        oos.defaultWriteObject();
        // т.к. в значении мапы стоит заглушка то мы сериализуем ее только по сету ключей
        // нам нужен size set key
        int s = map.size();
        Set<E> keys = map.keySet();
        oos.writeObject(s);

        for (E e : keys) {
            oos.writeObject(e);
        }
        // capacity and Load Factor get reflection
        int capacity = HashMapReflectionHelper.callHiddenMethod(map, "capacity");
        float loadFactor = HashMapReflectionHelper.callHiddenMethod(map, "loadFactor");
        oos.writeObject(capacity);
        oos.writeObject(loadFactor);

    }

    private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        ois.defaultReadObject();
        int s = (int) ois.readObject();
        Set<E> keys = new HashSet<>();
        for (int i = 0; i < s; i++) {
            keys.add((E) ois.readObject());
        }
        int capacity = (int) ois.readObject();
        float loadFactor = (float) ois.readObject();

        map = new HashMap<>(capacity, loadFactor);
        for (E e : keys) {
            map.put(e, PRESENT);
        }
    }
}
