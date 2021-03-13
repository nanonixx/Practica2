package ex1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

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

        if (n!=0) assertEquals(n-1, ht.count());
        else assertEquals(0, ht.count());

    }

    @org.junit.jupiter.api.Test
    void size() {
        HashTable ht = new HashTable();
//      pues da 16
        for (int i = 0; i < 30; i++) {
            ht.put("key"+i, "value"+i);
        }

        System.out.println(ht.toString());
        assertEquals(16, ht.size());
    }

    @ParameterizedTest
    @CsvSource({"key1, value1", "key2, value2", "3, value3"})
    void put(String key, String value) {
        HashTable ht = new HashTable();
        ht.put("key2", "value");

        ht.put(key, value);
        assertEquals(value, ht.get(key));
        //no se sustituye cuando
        System.out.println(ht.toString());


    }

    @ParameterizedTest //get valor null, en el medio o al final
    @CsvSource({"0", "3", "32"})
    void get2(int n) {
        HashTable ht = new HashTable();
        for (int i = 0; i < n; i++) {
            ht.put("key"+i, "value"+i);
        }
        ht.put("3", "value");
        System.out.println(ht.toString());

        if (n!=0) assertEquals("value2", ht.get("key2"));
        else assertNull(ht.get("key3"));

    }

    @ParameterizedTest //get un solo valor
    @CsvSource({"key, value", "key1, value1", "key2, value"})
    void get(String key, String value) {
        HashTable ht = new HashTable();

        ht.put(key, value);

        if (key.equals("key1")) assertEquals(value, ht.get("key1"));
        else Assertions.assertNull(ht.get("key1"));
    }

    @ParameterizedTest
    @CsvSource({"key", "key1", "3"}) //3 da colisión (spoiler: se va rallar)
    void drop(String key) { //drop de el unico elemento
        HashTable ht = new HashTable();
        ht.getCollisionsForKey("key2");

        ht.put(key, "value1");
        ht.put("key2", "value");

        System.out.println(ht.toString());
        ht.drop(key);

        System.out.println(ht.toString());
        ht.drop("key3"); //no debe dar nullpointer
        System.out.println(ht.toString());
        System.out.println(ht.count());

        assertNotEquals("", ht.toString());

    }

    @ParameterizedTest
    @CsvSource({"0", "3", "17"}) //17 da error
    void drop2(int n){
        HashTable ht = new HashTable();
        for (int i = 0; i < n; i++) {
            ht.put("key"+i, "value"+i);
        }

        System.out.println(ht.toString());
        ht.drop("key2");
        System.out.println(ht.toString());

        assertEquals(null, ht.get("key2"));
    }

}