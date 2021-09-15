package assign03;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Scanner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ArrayCollectionTest {
	
	private ArrayCollection<Integer> arr;
	private ArrayCollection<String> arrString;
	private Iterator<Integer> itr;
	private Iterator<String> itrString;
	
	@BeforeEach
	void setUp() throws Exception {
		arr = new ArrayCollection<Integer>();
		arrString = new ArrayCollection<String>();
		
		itr = arr.iterator();
		itrString = arrString.iterator();
		
		for(int i = 0; i < 100; i++) { 
			arr.add(i);
		}
		
		try {
			String filename = "src/assign03/collectionStringElements.txt";
			Scanner fileIn = new Scanner(new File(filename));
			while(fileIn.hasNext())
				arrString.add(fileIn.next());
			
		} catch(FileNotFoundException e) {
			System.err.println(e.getMessage() + " Nothing added to the library.");
			return;
		}
		
	}

	@Test
	void testAdd() { //DONE? //do we need to test for like, strings.
		
		ArrayCollection<Integer> arr = new ArrayCollection<Integer>(); //Must instantiate new arr to clear setUp arr
		
		for(int i = 0; i < 100; i++) { //add 0-99 :) u smart dawg
			arr.add(i);
		}
		
		assertEquals(100, arr.size()); // Asserting that the size is correct
		
		Iterator<Integer> itr = arr.iterator();
		for(int i = 0; i < arr.size(); i++) { // Asserting that the values are in the collection
			assertEquals(i, itr.next());
		}
		
		assertTrue(arr.add(100));		// Returns True Test 
		assertEquals(101, arr.size()); 	// Duplicate Test - Adding a 101th element to arr
		assertFalse(arr.add(100)); 		// Repeat Value Test - False because we try to add a repeat value
	}

	@Test
	void testAddAll() { //DONE? idk. i think soooo? check for like, any other edge cases.
		ArrayCollection<Integer> arr = new ArrayCollection<Integer>();		//Must instantiate new arr to clear setUp arr
		ArrayCollection<Integer> arrToAdd = new ArrayCollection<Integer>();
		ArrayCollection<Integer> empty = new ArrayCollection<Integer>();
		
		
		for(int i = 0; i < 10; i++) // add 0-9 to myArr
			arr.add(i);
		
		for(int i = 0; i < 20; i++) // add 0-19 to arrToAdd
			arrToAdd.add(i);
			
		assertTrue(arr.addAll(arrToAdd));		//add 10-19 to myArr (no duplicates)
		
		Iterator<Integer> itr = arr.iterator(); //Tests all original data retained and new data was added
		for(int i = 0; i < 20; i++)
			assertEquals(i, itr.next());
		
		assertEquals(20, arr.size()); // Tests no duplicates added and size updated.
		assertFalse(arr.addAll(empty)); // Tests False case, empty arrayCollection added
		
	}

	@Test
	void testClear() {
		arr.clear();
		assertEquals(0, arr.size()); //Test size is 0
		assertThrows(NoSuchElementException.class, () -> { itr.next(); }); //Test nothing is in the array
	}

	@Test
	void testContains() {
		assertTrue(arr.contains(50));		// test true case
		assertFalse(arr.contains(150));		// test false case
		assertFalse(arr.contains("five"));	// test non compatible case
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
		ArrayCollection<Integer> empty = new ArrayCollection<Integer>();
		assertTrue(empty.isEmpty());
		assertFalse(arr.isEmpty());
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
	void testRemove() { //not done
		assertTrue(arr.remove(50));
		assertEquals(99,arr.size());
		assertFalse(arr.remove(50));
		assertEquals(99, arr.size());
		assertFalse(arr.contains(50));
		
		//test if item is removed
		//test if original data is still there
		//test if size is updated
		
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
