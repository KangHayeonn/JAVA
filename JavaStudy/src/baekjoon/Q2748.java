// 피보나치 수 2 (백준 브론즈1)
package baekjoon;

import java.io.*;
/*
public class Q2748 {
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		long[] arr = new long[n+1];
		arr[0] = 0; arr[1] = 1;
		fibonacci(n, 2, arr);
		
		System.out.println(arr[n]);
	}
	public static void fibonacci(int n, int curr, long[] arr) {
		if(curr > n) return;
		arr[curr] = arr[curr-1] + arr[curr-2];
		fibonacci(n, curr+1, arr);
	}
}*/

public class Q2748 {
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		long[] arr = new long[n+1];
		arr[0] = 0; arr[1] = 1;
		System.out.println(fibonacci(n, arr));
	}
	public static long fibonacci(int n, long[] arr) {
		if(arr[n] != 0 || n==0) return arr[n];
		return arr[n] = fibonacci(n-1, arr) + fibonacci(n-2, arr);
	}
}

