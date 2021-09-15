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
	}

	@Test
	void testClear() {
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
		fail("Not yet implemented");
	}

	@Test
	void testIsEmpty() {
		fail("Not yet implemented");
	}

	@Test
	void testIterator() {
		fail("Not yet implemented");
	}

	@Test
	void testRemove() {
		fail("Not yet implemented");
	}

	@Test
	void testRemoveAll() {
		fail("Not yet implemented");
	}

	@Test
	void testRetainAll() {
		fail("Not yet implemented");
	}

	@Test
	void testSize() {
		fail("Not yet implemented");
	}

	@Test
	void testToArray() {
		fail("Not yet implemented");
	}

	@Test
	void testToArrayTArray() {
		fail("Not yet implemented");
	}

	@Test
	void testToSortedList() {
		fail("Not yet implemented");
	}

}
