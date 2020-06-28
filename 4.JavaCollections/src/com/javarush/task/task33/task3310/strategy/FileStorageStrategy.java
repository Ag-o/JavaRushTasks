package com.javarush.task.task33.task3310.strategy;

public class FileStorageStrategy implements StorageStrategy {
    private static final int DEFAULT_INITIAL_CAPACITY = 16;
    private static final long DEFAULT_BUCKET_SIZE_LIMIT = 1000L;
    private FileBucket[] table = new FileBucket[DEFAULT_INITIAL_CAPACITY];
    int size;
    private long bucketSizeLimit = DEFAULT_BUCKET_SIZE_LIMIT;
    long maxBucketSize;

    public int hash(Long k) {
        int h;
        return (k == null) ? 0 : (h = k.hashCode()) ^ (h >>> 16);
    }

    public int indexFor(int hash, int length) {
        return (length - 1) & hash;
    }

    public Entry getEntry(Long key) {
        int hash = hash(key);
        FileBucket bucket = this.table[indexFor(hash, this.table.length)];

        for (Entry e = bucket.getEntry(); e != null; e = e.next) {
            Long k;
            if (e.hash == hash && ((k = e.key) == key || key.equals(k))) {
                return e;
            }
        }
        return null;
    }

    public void resize(int newCapacity) {
        FileBucket[] newTable = new FileBucket[newCapacity];
        transfer(newTable);
        this.table = newTable;

        for (FileBucket bucket : table) {
            bucket.remove();
        }
    }

    public void transfer(FileBucket[] newTable) {
        FileBucket[] src = this.table;
        int newCapacity = newTable.length;

        for (int i = 0; i < src.length; i++) {
            FileBucket oldTableBucket = src[i];
            Entry entry = oldTableBucket.getEntry();

            if (entry != null) {
                src[i] = null;

                do {
                    Entry next = entry.next;
                    int index = indexFor(entry.hash, newCapacity);
                    FileBucket newTableBucket;

                    if (newTable[index] == null) {
                        newTableBucket = new FileBucket();
                        newTable[index] = newTableBucket;
                    } else {
                        newTableBucket = newTable[index];
                    }

                    entry.next = newTableBucket.getEntry();
                    newTableBucket.putEntry(entry);

                    entry = next;

                } while (entry != null);
            }
        }
    }

    public void addEntry(int hash, Long key, String value, int bucketIndex) {
        createEntry(hash, key, value, bucketIndex);
        FileBucket bucket = this.table[bucketIndex];

        if (bucket.getFileSize() > bucketSizeLimit) {
            resize(this.table.length * 2);
        }
    }

    public void createEntry(int hash, Long key, String value, int bucketIndex) {
        FileBucket bucket = this.table[bucketIndex];

        if (bucket == null) {
            bucket = new FileBucket();
            this.table[bucketIndex] = bucket;
        }

        Entry entry = bucket.getEntry();
        Entry newEntry = new Entry(hash, key, value, entry);
        bucket.putEntry(newEntry);
        size++;
    }

    @Override
    public boolean containsKey(Long key) {
        return getEntry(key) != null;
    }

    @Override
    public boolean containsValue(String value) {
        for (int i = 0; i < this.table.length; i++) {
            FileBucket bucket = this.table[i];

            if (bucket == null) {
                continue;
            }

            for (Entry entry = bucket.getEntry(); entry != null; entry = entry.next) {
                if (entry.getValue().equals(value)) {
                    return true;
                }
            }
        }

        return false;
    }

    @Override
    public void put(Long key, String value) {
        int hash = hash(key);
        int index = indexFor(hash, this.table.length);

        FileBucket bucket = this.table[index];

        if (bucket == null) {
            bucket = new FileBucket();
            this.table[index] = bucket;
        }

        for (Entry entry = bucket.getEntry(); entry != null; entry = entry.next) {
            Long k;

            if (entry.hash == hash && ((k = entry.key) == key || key.equals(k))) {
                entry.value = value;
                return;
            }
        }

        addEntry(hash, key, value, index);
    }

    @Override
    public Long getKey(String value) {
        for (int i = 0; i < this.table.length; i++) {
            for (Entry entry = this.table[i].getEntry(); entry != null; entry = entry.next) {
                if (entry.getValue().equals(value)) {
                    return entry.getKey();
                }
            }
        }

        return null;
    }

    @Override
    public String getValue(Long key) {
        return getEntry(key).getValue();
    }

    public long getBucketSizeLimit() {
        return bucketSizeLimit;
    }

    public void setBucketSizeLimit(long bucketSizeLimit) {
        this.bucketSizeLimit = bucketSizeLimit;
    }
}
