package KakaoCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class TestA {

	public static void main(String[] args) {
		List<Integer> arr= new List<Integer>();
		arr.add(3);
		arr.add(1);
		arr.add(2);
		arr.add(2);
		arr.add(4);
		arr.add(5);
		arr.add(2);
		arr.add(2);
		arr.add(3);

		System.out.println(arr);
		customSort(arr);
		System.out.println(arr);
	}

	public static void customSort(List<Integer> arr) {
		// Write your code here
		int count;
		int next;
		ArrayList<Integer> numbering= new ArrayList<Integer>();
		ArrayList<Integer> save= new ArrayList<Integer>();
		HashMap<Integer,Integer> map = new HashMap<Integer,Integer>();
		
		Collections.sort(arr);
		
		count=arr.size();
		for (int i=0;i<count;i++) {
			map.put(arr.get(i), 0);
		}
		for(int i=0;i<count;i++) {
			map.put(arr.get(i),map.get(arr.get(i)).intValue()+1);
		}
		
		Set<Integer> keys = map.keySet();
		Iterator<Integer> it = keys.iterator();
		while(it.hasNext()) {
			int key = it.next();
			int value = map.get(key);
			if (!numbering.contains(value))
				numbering.add(value);
		}
		Collections.sort(numbering);
		for(int i=0;i<numbering.size();i++) {
			next=numbering.get(i);
			for(int j=0;j<arr.size();j++) {
				if(map.get(arr.get(j))==next) {
						save.add(arr.get(j));
					
				}
				
			}
			
		}
		while(!arr.isEmpty())
			arr.remove(0);
		for(int i=0;i<save.size();i++)
			arr.add(save.get(i));
		
		
	}
}
