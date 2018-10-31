package org.crackcode;

//Given an array of positive integers. All numbers occur even number of times except one number which occurs odd number of times. 
//Find the number in O(n) time & constant space.

public class G3 {

	public static void main(String[] args) 
    { 
        int arr[] = new int[]{ 2, 3, 5, 4, 5, 2, 4, 3, 5, 2, 4, 4, 2 }; 
        int n = arr.length; 
        System.out.println(getOddOccurrence(arr, n)); 
    }

	private static int getOddOccurrence(int[] arr, int n) {
		int num = 0;
		for(int i=0;i<n;i++)
			num = num ^arr[i];
		return num;
	} 
}
