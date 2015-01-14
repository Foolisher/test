package test.guava.collections;

import static org.junit.Assert.*;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

import org.junit.Test;

import com.google.common.base.Function;
import com.google.common.collect.Lists;

public class ListsTest {

    @Test
    public void testNewArrayList() {
        fail("Not yet implemented");
    }

    @Test
    public void testNewArrayListEArray() {
        fail("Not yet implemented");
    }

    @Test
    public void testComputeArrayListCapacity() {
        fail("Not yet implemented");
    }

    @Test
    public void testNewArrayListIterableOfQextendsE() {
        fail("Not yet implemented");
    }

    @Test
    public void testNewArrayListIteratorOfQextendsE() {
        fail("Not yet implemented");
    }

    @Test
    public void testNewArrayListWithCapacity() {
        fail("Not yet implemented");
    }

    @Test
    public void testNewArrayListWithExpectedSize() {
        fail("Not yet implemented");
    }

    @Test
    public void testNewLinkedList() {
        fail("Not yet implemented");
    }

    @Test
    public void testNewLinkedListIterableOfQextendsE() {
        fail("Not yet implemented");
    }

    @Test
    public void testNewCopyOnWriteArrayList() {
        fail("Not yet implemented");
    }

    @Test
    public void testNewCopyOnWriteArrayListIterableOfQextendsE() {
        fail("Not yet implemented");
    }

    @Test
    public void testAsListEEArray() {
        fail("Not yet implemented");
    }

    @Test
    public void testAsListEEEArray() {
        fail("Not yet implemented");
    }

    @Test
    public void testCartesianProductListOfQextendsListOfQextendsB() {
        fail("Not yet implemented");
    }

    @Test
    public void testCartesianProductListOfQextendsBArray() {
        fail("Not yet implemented");
    }

    @Test
    public void testTransform() {

        @Data
        @AllArgsConstructor
        class Item {
            private String name;
            private String desc;
        }
        
        List<Item> fromList = Lists.newArrayList();
        fromList.add(new Item("terminus","a new com"));
        fromList.add(new Item("employee","ali"));
        List<String> toList = Lists.transform(fromList, new Function<Item, String>() {
            public String apply(Item input) {
                return input.name;
            }
        });
        
        System.out.println(toList);
    
    }

    @Test
    public void testPartition() {
        fail("Not yet implemented");
    }

    @Test
    public void testCharactersOfString() {
        fail("Not yet implemented");
    }

    @Test
    public void testCharactersOfCharSequence() {
        fail("Not yet implemented");
    }

    @Test
    public void testReverse() {
        fail("Not yet implemented");
    }

    @Test
    public void testHashCodeImpl() {
        fail("Not yet implemented");
    }

    @Test
    public void testEqualsImpl() {
        fail("Not yet implemented");
    }

    @Test
    public void testAddAllImpl() {
        fail("Not yet implemented");
    }

    @Test
    public void testIndexOfImpl() {
        fail("Not yet implemented");
    }

    @Test
    public void testLastIndexOfImpl() {
        fail("Not yet implemented");
    }

    @Test
    public void testListIteratorImpl() {
        fail("Not yet implemented");
    }

    @Test
    public void testSubListImpl() {
        fail("Not yet implemented");
    }

    @Test
    public void testCast() {
        fail("Not yet implemented");
    }

}
