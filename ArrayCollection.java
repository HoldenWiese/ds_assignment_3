package assign03;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

import assignment1.Matrix;

/**
 * @author Daniel Kopta and ??
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
		// TODO fill in
		// You will need to use something similar to the code in the constructor above to create a new array.
		T newData[] = (T[]) new Object[data.length * 2];
		for(int i = 0; i < data.length; i++) {
			newData[i] = data[i];
		}
		data = newData;
	}


	public boolean add(T arg0) {
		// TODO Auto-generated method stub
		if(this.contains(arg0))
			return false;
		
//		 capacity must allow add or grow
		if(size() == data.length)
			grow();
		
		data[size++] = arg0; //add arg0 to internal array data[] at index of size, incrementing size by 1
		
		return true;
	}

	public boolean addAll(Collection<? extends T> arg0) {
		for(T item: arg0) {
			this.add(item);
		}
		return false;
	}

	public void clear() {
		data = (T[]) new Object[10];
		size = 0;
	}

	public boolean contains(Object arg0) {
		// TODO: Check instance of!?
		
		Iterator<T> itr = iterator();
		while(itr.hasNext()) {
			if(itr.next().equals(arg0))
				return true;
		}
		return false;
	}

	public boolean containsAll(Collection<?> arg0) {
		// TODO Auto-generated method stub
		Iterator itr = arg0.iterator();
		while(itr.hasNext()) {
			if(!this.contains(itr.next()))
					return false;
		}
		return true;
	}

	public boolean isEmpty() {
		if(size == 0)
			return true;
		else
			return false;
	}

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
				 return true;
			 } 	
		 }
		return false;
	}

	public boolean removeAll(Collection<?> arg0) {
		int tempSize = this.size;
		Iterator itr = this.iterator();
		while(itr.hasNext()) {
			if(arg0.contains(itr.next())){
				itr.remove();
			}
		}
		return !(tempSize == size);
	}

	public boolean retainAll(Collection<?> arg0) {
		int tempSize = this.size;
		Iterator itr = this.iterator();
		while(itr.hasNext()) {
			if(!arg0.contains(itr.next())){
				itr.remove();
			}
		}
		return !(tempSize == size);
	}

	public int size() {
		return size;
	}

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
	public <T> T[] toArray(T[] arg0) {
		return null;
	}



	/*
     
	*/
	/**
	 * Sorting method specific to ArrayCollection - not part of the Collection interface.
     	Must implement a selection sort (see Assignment 2 for code).
     	Must not modify this ArrayCollection.
	 * @param cmp - the comparator that defines item ordering
	 * @return - the sorted list
	 */
	public ArrayList<T> toSortedList(Comparator<? super T> cmp)
	{
		ArrayList<T> arr = new ArrayList<T>();
		for(T item: data) {
			arr.add(item);
		}
		
		for(int i = 0; i < arr.size() - 1; i++) {
			int j, minIndex;
			for(j = i + 1, minIndex = i; j < arr.size(); j++)
				if(cmp.compare(arr.get(j), arr.get(minIndex)) < 0)
					minIndex = j;
			T temp = arr.get(i);
			arr.set(i, arr.get(minIndex));
			arr.set(minIndex, temp);
		}
		return arr;
	}


	/**
	 * 
	 * @author ??
	 * Describe your ArrayCollectionIterator class here.
	 *
	 */
	private class ArrayCollectionIterator implements Iterator<T>
	{
		private int nextIndex = 0;
		
		private boolean canRemove = false;
		
	
		public boolean hasNext() {
			return nextIndex < size; // when nextIndex is equal to the size, it means there's n amount of <numbers> in data[]
									 // and index n of data[] is out of bounds, which is when nextIndex would be pointing "out of bounds" 
									 // so i.e. for an array of 5 numbers, when nextIndex is 4, the last thing showed was at index 3. There's
									 // something in index 4 (the fifth number), and index 4 is less than 5 as well (the size of array)
									 // so since it has a next, if next() is called, nextIndex now equals 5, and the fifth number is returned
									 // but since now the nextIndex is 5 and its equal to the size, hasNext() or next() would be false.
		}
		
		/**
		 * returns the next item in the collection
		 * 
		 * if no item exists, throws an exception
		 */
		public T next() throws NoSuchElementException{
			if(!hasNext())
				throw new NoSuchElementException();
			
			canRemove = true;
			return data[nextIndex++];
		}

		/**
		 * removes whatever has been selected by iterator
		 */
		public void remove() {
			// TODO Auto-generated method stub
			
			if(!canRemove)  								//	I think the reason for doing 'can remove' instead of just checking the case
				throw new IllegalStateException();				// where the nextIndex is zero, is more so a question of intuitiveness of 
			ArrayCollection.this.remove(data[nextIndex - 1]);	// Iterator, because, if remove is supposed to remove "what's last been seen" or
			nextIndex--;										// last been selected, so if you remove something, and try to remove again without
															// calling next() again, what are you removing? you don't "know" whats next to remove
															// so to speak, because the iterator works forward, removing forwards, not backward
															// removing the one before and subsequently before
		}

	}

}
