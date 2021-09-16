package assign03;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * 
 * @author Daniel Kopta and ??
 * A utility class for searching, containing just one method (see below).
 *
 */
public class SearchUtil {

	
	/**
	 * 
	 * @param <T>	The type of elements contained in the list
	 * @param list	An ArrayList to search through. 
	 * 				This ArrayList is assumed to be sorted according 
	 * 				to the supplied comparator. This method has
	 * 				undefined behavior if the list is not sorted. 
	 * @param item	The item to search for
	 * @param cmp	Comparator for comparing Ts or a super class of T
	 * @return		true if the item exists in the list, false otherwise
	 */
	public static <T> boolean binarySearch(ArrayList<T> list, T item, Comparator<? super T> cmp)
	{
		//Is cmp.compare(item, midpoint) = 0 or < 0 or > 0
		//if greater search the right half
		//if less than search the left half
		//if equals return true
		//if not found return false
		//example in CS1410/src/a5/Completer.java
		
		int intLo = 0;
		int intHi = list.size()-1;
		T lo = list.get(intLo);
		T hi = list.get(intHi);
		while(cmp.compare(lo, hi) < 0) {
			int mid = intLo + (intHi - intLo)/2;
			if(cmp.compare(list.get(mid), item) == 0) 		//if mid equals return true
				return true;
			else if(cmp.compare(list.get(mid), item) < 0) {	//if mids less than the item, the items on the right side
				intLo = mid + 1;
				lo = list.get(intLo);
			}
			else if(cmp.compare(list.get(mid), item) > 0) {	//if mids greater than item, items on the left side
				intHi = mid - 1;
				hi = list.get(intHi);
			}
		}
		return false;
		
		
	}	
}