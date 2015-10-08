package Lab3;

public class MergeSort {
	  
	public void sort(int[] array, int low, int high )    {
		  // test base case; size of array equals 1     
		  if ( ( high - low ) >= 1 ) // if not base case
		  {
		       int middle1 = ( low + high ) / 2; // calculate middle of array
		       int middle2 = middle1 + 1; // calculate next element over     
		
		       // output split step
		      // System.out.println( "split:   " + subarray(array,low, high ) );
		      // System.out.println( "         " + subarray(array,low, middle1 ) );
		     //  System.out.println( "         " + subarray(array,middle2, high ) );
		     //  System.out.println();
		
		       // split array in half; sort each half (recursive calls)
		       sort( array, low, middle1 ); // first half of array       
		       sort( array, middle2, high ); // second half of array     
		
		       // merge two sorted arrays after split calls return
		       merge (array, low, middle1, middle2, high );             
		  } // end if                                           
	} // end method split   
	
	private void merge( int[] array, int left, int middle1, int middle2, int right ) {
		int leftIndex = left; // index into left subarray              
		int rightIndex = middle2; // index into right subarray         
		int combinedIndex = left; // index into temporary working array
		int[] combined = new int[ array.length ]; // working array      
	      
		// output two subarrays before merging
		//System.out.println( "merge:   " + subarray(array, left, middle1 ) );
		//System.out.println( "         " + subarray(array, middle2, right ) );
		
		// merge arrays until reaching end of either         
		while ( leftIndex <= middle1 && rightIndex <= right )
		{
		     // place smaller of two current elements into result  
		     // and move to next space in arrays                   
		     if ( array[leftIndex ] <= array[ rightIndex ] )        
		            combined[ combinedIndex++ ] = array[ leftIndex++ ]; 
		         else                                                  
		            combined[ combinedIndex++ ] = array[ rightIndex++ ];
		} // end while                                           
		// if left array is empty                                
		if ( leftIndex == middle2 ) {                              
		         // copy in rest of right array                        
		         while ( rightIndex <= right )                         
		            combined[ combinedIndex++ ] = array[ rightIndex++ ];
		} else { // right array is empty                             
				// copy in rest of left array                         
		         while ( leftIndex <= middle1 )                        
		            combined[ combinedIndex++ ] = array[ leftIndex++ ]; 
		}
		      // copy values back into original array
		for ( int i = left; i <= right; i++ )  
		        array[ i ] = combined[ i ];          
		
		      // output merged array
		//System.out.println( "         " + subarray(array, left, right ) );
		//System.out.println();
	} // end method merge
	
	public String subarray(int[] array, int low, int high ) {
		 StringBuffer temporary = new StringBuffer();
		
		 // output spaces for alignment
		 for ( int i = 0; i < low; i++ )
		      temporary.append("  " );
		
		 // output elements left in array
		 for ( int i = low; i <= high; i++ )
		      temporary.append(" " + array[ i ] );
		
		 return temporary.toString();
	} // end method subarray
}		
		  

