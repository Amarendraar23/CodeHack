package org.amar;

import java.util.LinkedHashMap;

class Info{
	int count;
	int pos;
}

public class Test {

	public static void main(String args[]) {
		int arr[] = {2, 5, 2, 8, 5, 6, 8, 8};
		sort(arr);
	}

	private static void sort(int[] arr) {
		LinkedHashMap<Integer,Info> collect = new LinkedHashMap<>();
		for(int i=0;i<arr.length;i++) {
			if(collect.containsKey(arr[i]))
				collect.get(arr[i]).count++;
			else {
				Info info =new Info();
				info.count=1;
				info.pos = i;
				collect.put(arr[i], info);
			}
		}
		
		System.out.println(collect);
	}
}
