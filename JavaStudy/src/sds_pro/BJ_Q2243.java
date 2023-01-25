// 사탕상자 (백준 플래티넘5)

package sds_pro;

import java.io.*;
import java.util.*;

public class BJ_Q2243 {
	static int[] tree;
	static int tempN;
	public static void main(String args[]) throws IOException {
		// System.setIn(new FileInputStream("input"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder("");
		
		int N = Integer.parseInt(br.readLine());
		tempN = getTempN(1000000);
		tree = new int[tempN * 2];
		
		StringTokenizer st;
		int typeN, b, c;
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			typeN = Integer.parseInt(st.nextToken());
			
			if(typeN == 1) {
				b = Integer.parseInt(st.nextToken());
				sb.append(getCandy(b, 1)).append("\n");
			}
			
			if(typeN == 2) {
				b = Integer.parseInt(st.nextToken());
				c = Integer.parseInt(st.nextToken());
				setDiff(tempN + b-1, c);
			}
		}
		
		System.out.print(sb);
	}
	public static int getTempN(int n) {
		int sum = 1;
		
		while(sum < n) {
			sum *= 2;
		}
		
		return sum;
	}
	public static void setDiff(int idx, int value) {
		while(idx > 0) {
			tree[idx] += value;
			idx /= 2;
		}
	}
	public static int getCandy(int n, int idx) {
		if(tempN <= idx) {
			setDiff(idx, -1);
			return idx-tempN + 1;
		}
		
		int leftChild = tree[idx*2];
		
		if(n <= leftChild) {
			return getCandy(n, idx*2);
		} else {
			return getCandy(n - leftChild, idx*2+1);
		}
	}
}
