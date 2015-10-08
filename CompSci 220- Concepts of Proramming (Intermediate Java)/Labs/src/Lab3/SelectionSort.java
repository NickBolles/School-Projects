package Lab3;

public class SelectionSort {
	
	public void sort(int[] number) {
		int minIndex, size, temp;
		size = number.length;
		for (int i=0; i<size-2; i++) {
			minIndex = i;
			for (int j=i+1; j<size-1; j++) {
				if (number[j] < number[minIndex])
					minIndex=j;
			}
			temp= number[i];
			number[i]=number[minIndex];
			number[minIndex] = temp;
		}
	}


}

