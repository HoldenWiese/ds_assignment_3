package assign03;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author Daniel Kopta and Holden Wiese and Brensen Villegas
 * Implements the Collection interface using an array as storage.
 * The array must grow as needed.
 * An ArrayCollection can not contain duplicates.
 * All methods should be implemented as defined by the Java API, unless otherwise specified.
 * 
 * It is your job to fill in the missing implementations and to comment this class. 
 * Every method should have comments (Javadoc-style prefered). 
 * The comments should at least indicate what the method does, 
 * what the arguments mean, and what the returned value is. 
 * It should specify any special cases that the method
 * handles. Any code that is not self-explanatory should be commented.
 *
 * @param <T> - generic type placeholder
 */
public class ArrayCollection<T> implements Collection<T> {

	private T data[]; // Storage for the items in the collection
	private int size; // Keep track of how many items this collection holds

	// There is no clean way to convert between T and Object, so we suppress the warning.
	@SuppressWarnings("unchecked")  
	public ArrayCollection()
	{
		size = 0;
		// We can't instantiate an array of unknown type T, so we must create an Object array, and typecast
		data = (T[]) new Object[10]; // Start with an initial capacity of 10
	}

	/**
	 * This is a helper method specific to ArrayCollection
	 * Doubles the size of the data storage array, retaining its current contents.
	 */
	@SuppressWarnings("unchecked")
	private void grow()
	{
		T newData[] = (T[]) new Object[data.length * 2];
		for(int i = 0; i < data.length; i++) {
			newData[i] = data[i];
		}
		data = newData;
	}


	/**
	 * adds a value not already existing into the collection
	 * @param arg0 item to be added
	 * @return true if item added successfully
	 */
	public boolean add(T arg0) {
		if(this.contains(arg0))
			return false;
		
//		 capacity must allow add or grow
		if(size() == data.length)
			grow();
		
		data[size++] = arg0; //add arg0 to internal array data[] at index of size, incrementing size by 1
		
		return true;
	}

	/**
	 * addAll of one Collection, to the Collection it was called on
	 * @param arg0 collection you want to add
	 * @return boolean if any were added
	 */
	public boolean addAll(Collection<? extends T> arg0) {
		int tempSize = size();
		for(T item: arg0) {
			this.add(item);
		}
		
		return tempSize != size();
	}
	
	/**
	 * clears the collection it was called on
	 */
	@SuppressWarnings("unchecked")
	public void clear() {
		data = (T[]) new Object[10];
		size = 0;
	}

	/**
	 * returns true if item is contained within collection
	 */
	public boolean contains(Object arg0) {
		Iterator<T> itr = iterator();
		while(itr.hasNext()) {
			if(itr.next().equals(arg0))
				return true;
		}
		return false;
	}

	/**
	 * returns true if every item in arg0 is in collection it was called on
	 * @param arg0 collection to see if all of these are contained
	 * @return boolean if all are contained
	 */
	public boolean containsAll(Collection<?> arg0) {
		// TODO Auto-generated method stub
		Iterator<?> itr = arg0.iterator();
		while(itr.hasNext()) {
			if(!this.contains(itr.next()))
					return false;
		}
		return true;
	}

	/**
	 * if empty, returns true
	 */
	public boolean isEmpty() {
		if(size == 0)
			return true;
		return false;
	}

	/**
	 * creates and returns a new iterator for ArrayCollection
	 */
	public Iterator<T> iterator() {
		return new ArrayCollectionIterator();
	}

	/**
	 * Removes arg0 by selecting with iterator what to remove
	 */
	public boolean remove(Object arg0) {
		 for(int i = 0; i < size; i++) {
			 if(data[i].equals(arg0)) {
				 for(int j = i + 1; j < size; j++) {
						data[j - 1] = data[j]; 
					 }
				 size--;
				 return true;
			 } 	
		 }
		return false;
	}

	/**
	 * removes all things from collection called on contained in arg0
	 * @param arg0 a collection of things to remove
	 * @return boolean true if something was removed
	 */
	public boolean removeAll(Collection<?> arg0) {
		int tempSize = this.size;
		Iterator<?> itr = this.iterator();
		while(itr.hasNext()) {
			if(arg0.contains(itr.next())){
				itr.remove();
			}
		}
		return tempSize != size;
	}

	/**
	 * removes all things that are NOT contained in arg0
	 * @param arg0 a collection of things to retain
	 * @return boolean true if something was removed
	 */
	public boolean retainAll(Collection<?> arg0) {
		int tempSize = this.size;
		Iterator<?> itr = this.iterator();
		while(itr.hasNext()) {
			if(!arg0.contains(itr.next())){
				itr.remove();
			}
		}
		return tempSize != size;
	}

	/**
	 * returns the size of items in the collection
	 */
	public int size() {
		return size;
	}

	/**
	 * returns an object array of items in the collection
	 */
	public Object[] toArray() {
		Object[] arr = new Object[size];
		for(int i = 0; i < size; i++) {
			arr[i] = data[i];
		}
		
		return arr;
	}

	/* 
	 * Don't implement this method (unless you want to).
	 * It must be here to complete the Collection interface.
	 * We will not test this method.
	 */
	@SuppressWarnings("hiding") //cuz the warning is annoying
	public <T> T[] toArray(T[] arg0) {
		return null;
	}


	/**
	 * Sorting method specific to ArrayCollection - not part of the Collection interface.
     	Must implement a selection sort (see Assignment 2 for code).
     	Must not modify this ArrayCollection.
	 * @param cmp - the comparator that defines item ordering
	 * @return - the sorted list
	 */
	public ArrayList<T> toSortedList(Comparator<? super T> cmp)
	{
		ArrayList<T> arr = new ArrayList<T>(); // create NEW ArrayList<T>
		
		
		for(int i = 0; i < this.size(); i++) { 					// copies data
			arr.add(this.data[i]);
		}
		
		for(int i = 0; i < arr.size() - 1; i++) {					// Selection Sort
			int j, minIndex;
			for(j = i + 1, minIndex = i; j < arr.size(); j++) {
				if(cmp.compare(arr.get(j), arr.get(minIndex)) < 0) {
					minIndex = j; }
			}
			T temp = arr.get(i);
			arr.set(i, arr.get(minIndex));
			arr.set(minIndex, temp);
		}
		return arr;
	}
	
	


	/**
	 * 
	 * @author Brensen Villegas and Holden Wiese
	 * 
	 * iterates/navigates ArrayCollection, holding data to know where
	 * the iterator is, if theres a next item, and how to remove something
	 * and update nextIndex
	 *
	 */
	private class ArrayCollectionIterator implements Iterator<T>
	{
		private int nextIndex = 0;
		
		private boolean canRemove = false;
		
	
		/**
		 * returns true if theres a next item
		 */
		public boolean hasNext() {
			return nextIndex < size; 
		}
		
		/**
		 * returns the next item in the collection
		 * 
		 * if no item exists, throws an exception
		 */
		public T next() throws NoSuchElementException {
			if(!hasNext())
				throw new NoSuchElementException();
			
			canRemove = true;
			return data[nextIndex++];
		}

		/**
		 * removes whatever has been selected by iterator
		 */
		public void remove() throws IllegalStateException {
			
			if(!canRemove)  								
				throw new IllegalStateException();				 
			ArrayCollection.this.remove(data[nextIndex - 1]);	
			nextIndex--;
			canRemove = false;												
		}

	}

}
