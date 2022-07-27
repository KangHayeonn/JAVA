// 사탕상자 (백준 플래티넘4)

package baekjoon;

import java.io.*;
import java.util.*;

public class Q2243 {
	static int[] indexTree;
	static int tempN;
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder("");
		StringTokenizer st = new StringTokenizer("");
		int n = Integer.parseInt(br.readLine());
		tempN = getTempN(1000000);
		indexTree = new int[2*tempN];
		int a, b, c = 0, idx = 0;
		
		while(n-- > 0) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			
			if(a == 1) {
				b = Integer.parseInt(st.nextToken());
				idx = 1;
				
				sb.append(getNum(b, 1) + 1).append("\n");
			} else { // a == 2
				b = Integer.parseInt(st.nextToken());
				c = Integer.parseInt(st.nextToken());
				
				idx = tempN + (b-1);
				setDiff(c, idx);
			}
		}
		
		System.out.print(sb);
	}
	private static int getTempN(int length) {
		 int idx = 1;
		 
		 while(idx < length) {
			 idx *= 2;
		 }
		 
		 return idx;
	}
	private static int getNum(int b, int idx) {// b: 찾을 친구, idx: 현재 위치인덱스
		if(tempN <= idx) {
			setDiff(-1, idx);
			return idx-tempN;
		}
		
		int leftChild = indexTree[idx*2];
		
		if((leftChild - b) >= 0) {
			return getNum(b, idx*2);
		} else {
			return getNum(b-leftChild, idx*2+1);
		}
		
	}
	private static void setDiff(int diff, int idx) {
		indexTree[idx] += diff;
		while(idx > 1) {
			idx /= 2;
			indexTree[idx] += diff;
		}
	}
}
