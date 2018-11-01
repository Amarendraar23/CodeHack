package org.crackcode;

//find the sum of contiguous subarray within a one-dimensional array of numbers which has the largest sum. 
//https://www.geeksforgeeks.org/largest-sum-contiguous-subarray/

public class G4 {

	public static void main(String[] args) 
    { 
		int a[] = { -2, -3, 4, -1, -2, 1, 5, -3 }; 
        int n = a.length; 
        maxSubArraySum(a, n);
    }

	private static void maxSubArraySum(int[] a, int n) {
		int max_so_far = Integer.MIN_VALUE;
		int max_ending_here = 0;
		int start = 0;
		int end = 0;
		int s = 0;
		
		for(int i=0;i<n;i++) {
			max_ending_here +=a[i];
			if(max_so_far < max_ending_here) {
				max_so_far = max_ending_here;
				start = s;
				end = i;
			}
			if(max_ending_here<0) {
				s = i+1;
				max_ending_here = 0;
			}
		}
		
		System.out.println("Maximum Sum for Contiguos subarray is "+max_so_far +" starting at "+start+" ending at "+end);
	}
}
