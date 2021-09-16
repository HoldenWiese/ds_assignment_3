package assign03;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Scanner;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import assign02.LibraryBookGeneric;

class ArrayCollectionTest {
	
	private ArrayCollection<Integer> arr;
	private ArrayCollection<String> arrString;
	private Iterator<Integer> itr;
	private Iterator<String> itrString;
	
	
	protected class cmpInteger implements Comparator<Integer> {
		public int compare(Integer x, Integer y) {
			return x - y;
		}
	}
	
	protected class cmpString implements Comparator<String> {
		public int compare(String x, String y) {
			return x.compareTo(y);
		}
	}
	
	/**
	 * This method reads in a file and converts each word in the file to a string, for the use of testing
	 * @param filename name of filepath
	 * @return ArrayCollection<String> of each word in file
	 */
	private ArrayCollection<String> readFile(String filename) {
		ArrayCollection<String> sArr = new ArrayCollection<String>(); //start with fresh Array
		String[] parsed = new String[0];
		
		try {
			Scanner fileIn = new Scanner(new File(filename));
			while(fileIn.hasNext()) {
				//String[] parsed;
				parsed = fileIn.next().split(" ");
				for(String word: parsed)
					sArr.add(word);
			}
			
		} catch(FileNotFoundException e) {
			System.err.println(e.getMessage() + " Nothing added to the library.");
			return arrString;
		}
		
		return sArr;
	}
	
	@BeforeEach
	void setUp() throws Exception {
		arr = new ArrayCollection<Integer>();
		arrString = new ArrayCollection<String>(); //if we do String tests
		
		itr = arr.iterator();
		itrString = arrString.iterator();
		
		for(int i = 0; i < 100; i++) { 
			arr.add(i);
		}
		
		arrString = readFile("src/assign03/collectionStringElements.txt"); //if we do String tests
		
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
		ArrayCollection<Integer> arr1 = new ArrayCollection<Integer>();
		ArrayCollection<Integer> arr2 = new ArrayCollection<Integer>();
		ArrayCollection<Integer> arr3 = new ArrayCollection<Integer>();
		ArrayCollection<Integer> arr4 = new ArrayCollection<Integer>();
		ArrayCollection<Integer> arr5 = new ArrayCollection<Integer>();
		ArrayCollection<Integer> arr6 = new ArrayCollection<Integer>();
		
		for(int i = 0; i < 10; i++) {
			arr1.add(i);
		}
		for(int i = 5; i < 15; i++) {
			arr2.add(i);
		}
		for(int i = 5; i < 8; i++) {
			arr3.add(i);
			arr5.add(i);
		}
		for(int i = -2; i < 20; i++) {
			arr4.add(i);
			arr6.add(i);
		}
		
		assertTrue(arr1.containsAll(arr3));
		assertFalse(arr1.containsAll(arr2));
		assertEquals("[0, 1, 2, 3, 4, 5, 6, 7, 8, 9]", Arrays.toString(arr1.toArray())); // Assert values are not changed
		
		assertFalse(arr3.containsAll(arr4)); //arr3 does not contain all items in arr4
		assertTrue(arr3.containsAll(arr5)); //arr3 contains same items as arr5
		assertTrue(arr4.containsAll(arr3)); // arr4 contains lots of items including all items in arr3
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
		
		ArrayCollection<Integer> arr1 = new ArrayCollection<Integer>();
		itr = arr1.iterator();
		
		for(int i = 0; i < 10; i++) {
			arr1.add(i);
		}
		
		assertTrue(arr1.iterator() instanceof Iterator); // Assert iterator() returns an iterator object.
		assertEquals(0, itr.next());
		assertTrue(itr.hasNext());
		
		itr.remove();
		assertEquals("[1, 2, 3, 4, 5, 6, 7, 8, 9]", Arrays.toString(arr1.toArray())); // remove should remove last next item.
		assertThrows(IllegalStateException.class, () -> itr.remove());
		
		while(itr.hasNext()) {
			itr.next();
		}
		assertThrows(NoSuchElementException.class, () -> itr.next());
		
	}

	@Test
	void testRemove() { //not done
		assertTrue(arr.remove(50));
		assertEquals(99,arr.size());
		assertFalse(arr.remove(50));
		assertEquals(99, arr.size());
		assertFalse(arr.contains(50));		
	}

	@Test
	void testRemoveAll() {
		ArrayCollection<Integer> arr1 = new ArrayCollection<Integer>();
		ArrayCollection<Integer> arr2 = new ArrayCollection<Integer>();
		ArrayCollection<Integer> arr3 = new ArrayCollection<Integer>();
		ArrayCollection<Integer> arr4 = new ArrayCollection<Integer>();
		ArrayCollection<Integer> arr5 = new ArrayCollection<Integer>();
		ArrayCollection<Integer> arr6 = new ArrayCollection<Integer>();
		
		for(int i = 0; i < 10; i++) {
			arr1.add(i);
		}
		for(int i = 5; i < 15; i++) {
			arr2.add(i);
		}
		for(int i = 5; i < 8; i++) {
			arr3.add(i);
			arr5.add(i);
		}
		for(int i = -2; i < 20; i++) {
			arr4.add(i);
			arr6.add(i);
		}
		
		assertTrue(arr1.removeAll(arr2)); // Calling remove all.
		assertEquals("[0, 1, 2, 3, 4]", Arrays.toString(arr1.toArray())); // Checking the new values after calling remove all.
		assertFalse(arr1.removeAll(arr2)); //Assert that when there is no change to the array it returns false.
		assertEquals("[5, 6, 7, 8, 9, 10, 11, 12, 13, 14]", Arrays.toString(arr2.toArray())); // Assert that the argument was not changed.
		assertEquals(5, arr1.size()); // Assert that arr1 is expected size.
		
		assertTrue(arr3.removeAll(arr4));
		assertEquals("[]", Arrays.toString(arr3.toArray())); // arr3 should remove all values.
		
		assertTrue(arr6.removeAll(arr5));
		assertEquals("[-2, -1, 0, 1, 2, 3, 4, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19]", Arrays.toString(arr6.toArray())); // arr6 should remove arr5(5, 6, and 7)
	}

	@Test
	void testRetainAll() {
		ArrayCollection<Integer> arr1 = new ArrayCollection<Integer>();
		ArrayCollection<Integer> arr2 = new ArrayCollection<Integer>();
		ArrayCollection<Integer> arr3 = new ArrayCollection<Integer>();
		ArrayCollection<Integer> arr4 = new ArrayCollection<Integer>();
		ArrayCollection<Integer> arr5 = new ArrayCollection<Integer>();
		ArrayCollection<Integer> arr6 = new ArrayCollection<Integer>();
		
		for(int i = 0; i < 10; i++) {
			arr1.add(i);
		}
		for(int i = 5; i < 15; i++) {
			arr2.add(i);
		}
		for(int i = 5; i < 8; i++) {
			arr3.add(i);
			arr5.add(i);
		}
		for(int i = -2; i < 20; i++) {
			arr4.add(i);
			arr6.add(i);
		}
		
		assertTrue(arr1.retainAll(arr2)); // Calling retain all.
		assertEquals("[5, 6, 7, 8, 9]", Arrays.toString(arr1.toArray())); // Checking the new values after calling retain all.
		assertFalse(arr1.retainAll(arr2)); //Assert that when there is no change to the array it returns false.
		assertEquals("[5, 6, 7, 8, 9, 10, 11, 12, 13, 14]", Arrays.toString(arr2.toArray())); // Assert that the arguement was not changed.
		assertEquals(5, arr1.size());
		
		assertFalse(arr3.retainAll(arr4)); // arr4 has same values so returns false. arr3 was not changed.
		assertEquals("[5, 6, 7]", Arrays.toString(arr3.toArray())); // arr3 should retain all values.
		
		assertTrue(arr6.retainAll(arr5));
		assertEquals("[5, 6, 7]", Arrays.toString(arr6.toArray())); // arr6 should retain arr5(5, 6, and 7)
	}

	@Test
	void testSize() {
		assertEquals(100, arr.size());
		arr.add(500);
		assertEquals(101, arr.size());
		arr.remove(4);
		assertEquals(100, arr.size());
		//test initial size
		//add 1 or some and test size again
		
		//fail("Not yet implemented");
	}

	@Test
	void testToArray() {
		assertEquals(100, arr.toArray().length);
		assertEquals(99, arr.toArray()[99]);
		assertEquals(0, arr.toArray()[0]);
	}


	@Test
	void testToSortedList() {
		arr.clear();
		for(int i = 100; i > 0; i--) { //adds 100 - 1 in descending order
			arr.add(i);
		}
		
		System.out.println(arr.size());
		cmpInteger cmp = new cmpInteger();
		ArrayList<Integer> intArr = arr.toSortedList(cmp);
		
		for(int i = 100; i < 0; i--) { //adds 100 - 1 in descending order
			assertEquals(i, itr.next());
		}
		
		for(int i = 1; i < 101; i++) { //adds 100 - 1 in descending order
			assertEquals(i, intArr.get(i-1));
		}
		
		assertEquals(100 ,intArr.size());
		assertEquals(100, arr.size());
			
		
		
		
		//test that its sorted
		//test the size does not change (arraylist.length == sized)
		//test arraylist contains all things in data[]
		
		//fail("Not yet implemented");
	}

}
