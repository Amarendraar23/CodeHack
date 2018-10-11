package org.crackcode;

public class G2 {

	public static void main(String args[]) {
		int arr[] = new int[] { 1, 20, 6, 4, 5 }; 
        System.out.println("Number of inversions are " + mergeSort(arr, 5)); 
	}

	private static int mergeSort(int[] arr, int len) {
		int temp[] = new int[len]; 
        return _mergeSort(arr, temp, 0, len - 1); 
	}

	private static int _mergeSort(int[] arr, int[] temp, int i, int j) {
		
		int mid,inversion_count=0;
		if(j>i) {
			mid = (i+j)/2;
			
			inversion_count  = _mergeSort(arr, temp, i, mid);
			inversion_count  += _mergeSort(arr, temp, mid+1, j);
			
			inversion_count += merge(arr,temp,i,mid+1,j);
		}
		return inversion_count;
	}

	private static int merge(int[] arr, int[] temp, int left, int mid, int right) {
		int invertion_count = 0;
		
		int i=left;
		int j=mid;
		int k=left;
		
		while(i<=mid-1 && j<=right) {
			if(arr[i] <= arr[j])
				temp[k++] = arr[i++];
			else {
				invertion_count += (mid-i);
				temp[k++] = arr[j++];
			}
		}
		
		while(i<=mid-1) {
			temp[k++] = arr[i++];
		}
		
		while(j<=right) {
			temp[k++] = arr[j++];
		}
		
		for(i=left ;i<=right;i++) {
			arr[i] = temp[i];
		}
		return invertion_count;
	}
}
