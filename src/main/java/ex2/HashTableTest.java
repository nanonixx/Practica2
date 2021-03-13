package ex2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class HashTableTest {

    @ParameterizedTest
    @CsvSource({"0", "1", "2", "15"})
    void count(int n) {

        HashTable ht = new HashTable();
        for (int i = 0; i < n; i++) {
            ht.put("key"+i, "value"+i);
        }

        ht.drop("key0");

        System.out.println(ht.toString());

        if (n!=0) Assertions.assertEquals(n-1, ht.count());
        else Assertions.assertEquals(0, ht.count());

    }

    @org.junit.jupiter.api.Test
    void size() {
        HashTable ht = new HashTable();
//      pues da 16
        for (int i = 0; i < 30; i++) {
            ht.put("key"+i, "value"+i);
        }

        System.out.println(ht.toString());
        Assertions.assertEquals(16, ht.size());
    }

    @ParameterizedTest
    @CsvSource({"key1, value1", "key2, value2", "key3, value3"})
    void put(String key, String value) {
        HashTable ht = new HashTable();
        ht.put("key1", "value");

        ht.put(key, value);
        Assertions.assertEquals(value, ht.get(key));
        //no se sustituye cuando
        System.out.println(ht.toString());


    }

    @ParameterizedTest
    @CsvSource({"key, value", "key1, value1", "key2, value"})
    void get(String key, String value) {
        HashTable ht = new HashTable();

        ht.put(key, value);

        if (key.equals("key1")) Assertions.assertEquals(value, ht.get("key1"));
        else Assertions.assertNull(ht.get("key1"));
    }

    @ParameterizedTest
    @CsvSource({"key", "key1", "3"}) //3 da colisión (spoiler: se va rallar)
    void drop(String key) {
        HashTable ht = new HashTable();

        ht.put(key, "value1");
        ht.put("key2", "value");

        System.out.println(ht.toString());
        ht.drop(key);

        System.out.println(ht.toString());
        Assertions.assertNotEquals("", ht.toString());

    }
}