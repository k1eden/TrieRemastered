import com.k1.TrieRemastered;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TrieRemasteredTest {

    @org.junit.jupiter.api.Test
    void find() {
        TrieRemastered trie = new TrieRemastered();
        assertFalse(trie.find("jsjs"));
        trie.put("Кекс");
        assertFalse(trie.find("s"));
        assertTrue(trie.find("Кекс"));
    }

    @org.junit.jupiter.api.Test
    void put() {
        TrieRemastered trie = new TrieRemastered();
        trie.put("Челка");
        trie.put("Кепка");
        trie.put("Сорняк");
        trie.put("Лизинг");
        trie.put("Тууа");
        trie.put("Карамель");
        assertTrue(trie.find("Челка"));
        assertTrue(trie.find("Кепка"));
        assertTrue(trie.find("Сорняк"));
        assertTrue(trie.find("Лизинг"));
        assertTrue(trie.find("Тууа"));
        assertTrue(trie.find("Карамель"));
        assertFalse(trie.find("ккррк"));
    }

    @org.junit.jupiter.api.Test
    void remove() {
        TrieRemastered trie = new TrieRemastered();
        trie.put("литва");
        trie.put("корона");
        trie.put("вирус");
        trie.put("газон");
        trie.put("ритм");
        trie.remove("литва");
        assertFalse(trie.find("литва"));
        assertTrue(trie.find("вирус"));
    }

    @org.junit.jupiter.api.Test
    void findByPrefix() {
        TrieRemastered trie = new TrieRemastered();
        trie.put("кос");
        trie.put("коса");
        trie.put("косач");
        trie.put("косапий");
        trie.put("негатив");
        trie.put("позитив");
        trie.put("кремний");
        assertEquals(trie.findByPrefix("кос"), List.of("кос", "коса", "косач", "косапий"));
    }
}