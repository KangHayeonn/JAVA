// 사탕상자 (백준 플래티넘5)

package sds_pro;

import java.io.*;
import java.util.*;

public class BJ_Q2243 {
	static int[] tree;
	public static void main(String args[]) throws IOException {
		System.setIn(new FileInputStream("input"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int tempN = getTempN(1000000);
		tree = new int[tempN * 2];
		
		StringTokenizer st;
		int typeN, b, c;
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			typeN = Integer.parseInt(st.nextToken());
			
			if(typeN == 1) {
				b = Integer.parseInt(st.nextToken());
				getCandy(b);
			}
			
			if(typeN == 2) {
				b = Integer.parseInt(st.nextToken());
				c = Integer.parseInt(st.nextToken());
				getDiff(tempN, b, c);
			}
		}
	}
	public static int getTempN(int n) {
		int idx = 0;
		int sum = 1;
		
		while(sum < n) {
			sum *= 2;
			idx += 1;
		}
		
		return sum;
	}
	public static void getDiff(int tempN, int idx, int value) {
		int n = tempN + idx;
		while(n > 0) {
			tree[n] += value;
			n /= 2;
		}
	}
	public static void getCandy(int n) {
		int idx = 1;
		int value = tree[idx];
		System.out.println(value);
		
		int left, right;
		while(n < value) {
			idx *= 2;
			left = idx;
			right = idx + 1;
			
			if(n <= tree[left]) {
				value = tree[left];
				idx = left;
			} else {
				value = tree[right];
				idx = right;
			}
		}
		
		System.out.println(idx + " -> " + value);
		return;
	}
}
