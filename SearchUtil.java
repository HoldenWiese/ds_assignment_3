package assign03;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * 
 * @author Daniel Kopta and Holden Wiese and Brensen Villegas
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
		if(list.size() == 0)
			return false;
		
		int intLo = 0;
		int intHi = list.size()-1;
		T lo = list.get(intLo);
		T hi = list.get(intHi);
		while(intLo <= intHi) {							//while the lowest index is less not greater than the highest index
			int mid = intLo + (intHi - intLo)/2;
			lo = list.get(intLo);
			hi = list.get(intHi);
			if(cmp.compare(list.get(mid), item) == 0) 		//if mid value equals search value return true
				return true;
			else if(cmp.compare(list.get(mid), item) < 0) {	//if mids value less than the item value, the items on the right side
				intLo = mid + 1;
			}
			else if(cmp.compare(list.get(mid), item) > 0) {	//if mids value greater than item value, items on the left side
				intHi = mid - 1;
			}
		}
		return false;
		
		
	}	
}