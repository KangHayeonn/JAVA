package Algorithm;

import java.util.Arrays;
import java.util.Random;
/*
public class test {
	public static void main(String args[]) { // 0.027√ 
		Random random = new Random(System.nanoTime());
		int[] arr = new int[50000];
		for(int i = 0; i < arr.length; i++) {
			arr[i] = random.nextInt(50000);
			for(int j=0; j<i; j++) {
				if(arr[i] == arr[j]) {
					i--;
				}
			}
		}
		
		long start, end;
		start = System.currentTimeMillis();
		Arrays.sort(arr);
		end = System.currentTimeMillis();
		System.out.println((end - start) / 1000.0);
	}
}*/

public class test {
	public static void main(String[] args) { // 0.041√ 
	    Random random = new Random(System.nanoTime());
	    Integer[] arr = new Integer[50000];
	    for (int i = 0; i < arr.length; i++) {
	        arr[i] = random.nextInt(50000);
	        for (int j = 0; j < i; j++) {
	            if (arr[i].equals(arr[j])) {
	                i--;
	            }
	        }
	    }
	    long start, end;
	    start = System.currentTimeMillis();
	    Arrays.sort(arr);
	    end = System.currentTimeMillis();
	    System.out.println((end - start) / 1000.0);
	}
}

