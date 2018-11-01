package org.crackcode.arrays;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;


// https://www.geeksforgeeks.org/sort-elements-frequency-set-4-efficient-approach-using-hash/


class Info{
	int count;
	int pos;
	int var;
	
	public String toString() {
		return String.valueOf(this.var);
	}
}

public class G1 {

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
				info.var = arr[i];
				collect.put(arr[i], info);
			}
		}
		List<Info> list = new ArrayList<Info>(collect.values());
		Collections.sort(list,new Comparator<Info>() {

			@Override
			public int compare(Info o1, Info o2) {
				if(o1.count>o2.count)
					return -1;
				else if(o1.count<o2.count)
					return 1;
				else {
					if(o1.pos<o2.pos)
						return -1;
					else
						return 1;
				}
			}
		});
		System.out.println(list);
	}
}
