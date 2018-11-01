package org.crackcode.arrays;

/* 
 * An element in a sorted array can be found in O(log n) time via binary search. 
 * But suppose we rotate an ascending order sorted array at some pivot unknown to you beforehand. 
 * So for instance, 1 2 3 4 5 might become 3 4 5 1 2. Devise a way to find an element in the rotated array in O(log n) time. 
 * https://www.geeksforgeeks.org/search-an-element-in-a-sorted-and-pivoted-array
 * */

public class G5 {

	public static void main(String[] args) 
    { 
		int arr[] = {1,3}; 
        int n = arr.length; 
        int key = 0; 
        int i = search(arr, 0, n-1, key); 
        if (i != -1)  
            System.out.println("Index: " + i); 
        else
            System.out.println("Key not found"); 
    }

	private static int search(int[] arr, int i, int j, int key) {
		if(arr.length==0)
			return -1;
		int pivot = findPivot(arr);
		int index = binarySearch(arr,key,0,pivot);
		if(index!=-1)
			return index;
		else
			return binarySearch(arr,key,pivot+1,arr.length-1);
	}

	private static int binarySearch(int[] arr, int key, int start, int end) {
		while(start<=end) {
			int mid = (start+end)/2;
			if(arr[mid] == key)
				return mid;
			else if(arr[mid]>key)
				end = mid-1;
			else
				start = mid+1;
		}
		return -1;
	}

	private static int findPivot(int[] arr) {
		int low = 0;
		int high = arr.length-1;
		while(low<high) {
			int mid = (high+low)/2;
			if(mid>low && arr[mid]<arr[mid-1])
				return mid-1;
			else if(mid<high && arr[mid]>arr[mid+1])
				return mid;
			else if(arr[low]>=arr[mid])
				high = mid-1;
			else
				low = mid+1;
		}
		return 0;
	}
}
