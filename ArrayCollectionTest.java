package assign03;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ArrayCollectionTest {
	
	@BeforeEach
	void setUp() throws Exception {
		
	}

	@Test
	void testAdd() {
		//Test for general things added (returns true and check size)
		//Test for duplicates not added (returns false)
		//Test for capacity growth i.e. return true after adding a lot of things and checking for size
		ArrayCollection<Integer> arr = new ArrayCollection<Integer>();
		
		for(int i = 0; i < 100; i++) {
			arr.add(i);
		}
		
		assertEquals(100, arr.size()); // Asserting that the size is correct
		assertEquals(50, arr.toArray()[50]); // Asserting that the values are in the collection
		
		arr.add(100);
		assertEquals(101, arr.size()); // Adding a 101th element to arr
		assertFalse(arr.add(100)); // False because we try to add a repeat value
	}

	@Test
	void testAddAll() {
		ArrayCollection<Integer> arr = new ArrayCollection<Integer>();
		
		for(int i = 0; i < 10; i++) {
			arr.add(i);
		}
		
		Integer[] intArray = new Integer[] {
				10, 11, 12, 13, 14, 15
		};
		arr.addAll(intArray);
		//test all were added (no misses)
		//test original data was unchanged
		//test add empty collection
		//test add all to an empty collection
		
	}

	@Test
	void testClear() {
		//test that its empty
		//test clear on emtpy collection
		
		fail("Not yet implemented");
	}

	@Test
	void testContains() {
		fail("Not yet implemented");
		//check if it does contain
		//check if it doesn't
	}

	@Test
	void testContainsAll() {
		//test if contains all
		//test if contains all of smaller collection
		//test smaller collections (doesn't) contain all of bigger collection
		
		fail("Not yet implemented");
	}

	@Test
	void testIsEmpty() {
		//test when true (check data and size)
		//test when false (check data and size)
		
		fail("Not yet implemented");
	}

	@Test
	void testIterator() {
		//test Iterator can
		//do next() and throw exceptions
		//do hasnext() for true and false
		//do remove() and updates int nextIndex 
		
		fail("Not yet implemented");
	}

	@Test
	void testRemove() {
		//test if item is removed
		//test if original data is still there
		//test if size is updated
		
		fail("Not yet implemented");
	}

	@Test
	void testRemoveAll() {
		// test if all that data was removed and original is still there
		// test if smaller collection removes all things from a bigger collection resulting in nothing
		// test if bigger collection removes all things from a smaller collection resulting in remaining data
		// test if items in other collection are not contained in the collection, nothing is removed
		
		fail("Not yet implemented");
	}

	@Test
	void testRetainAll() {
		// test if all data in other collection is retained
		// test if other collection is bigger but has all that your has resulting in retaining all
		// test if yours is bigger and other is smaller resulting in retaining only other
		
		fail("Not yet implemented");
	}

	@Test
	void testSize() {
		//test initial size
		//add 1 or some and test size again
		
		fail("Not yet implemented");
	}

	@Test
	void testToArray() {
		// test array length == size of collection
		// test all data the same
		// test something else idk?
		fail("Not yet implemented");
	}


	@Test
	void testToSortedList() {
		//test that its sorted
		//test the size does not change (arraylist.length == sized)
		//test arraylist contains all things in data[]
		
		fail("Not yet implemented");
	}

}
