package utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Stream;

public class GenClass {

	
	public static void main(String [] args)
	{
		String str = "abacsusddhadsdksferriwjioj";
		char[] c = str.toCharArray();
		Map<Character, Integer> map = new HashMap<Character, Integer>();
		int clength = c.length;
		
	for(int i=0 ; i< clength ; i++) 
	{
		if(map.containsKey(c[i]))
		{
			map.put(c[i], map.get(c[i])+1);
		}
		
		else 
			map.put(c[i], 1);
	}
		
	List<Entry<Character, Integer>> finalList = map.entrySet().stream()
			.sorted((item1, item2)->Integer.compare(item1.getValue(),  item2.getValue())).toList();
	
	System.out.println(finalList);
	}
}
