// 달리기 (백준 2517번)

package baekjoon;

import java.io.*;
import java.util.*;

public class Q2517 {
	static int tempN;
	static int[] indexTree;
	public static class Type {
		int score, order; // score : 점수, order : 초기 입력 순서 
		
		public Type(int score, int order) {
			this.score = score;
			this.order = order;
		}
	}
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int N = Integer.parseInt(br.readLine());
		ArrayList<Type> arr = new ArrayList<>();
		Type[] compress = new Type[N];
		int num;
		
		for(int i=0; i<N; i++) {
			num = Integer.parseInt(br.readLine());
			compress[i] = new Type(num, i+1);
		}
		
		Arrays.sort(compress, (x, y) -> x.score - y.score);
		
		for(int i=0; i<N; i++) {
			Type t = compress[i];
			arr.add(new Type(i+1, t.order));
		}
		
		Collections.sort(arr, (x, y) -> x.order - y.order);
		
		tempN = getTempN(500000);
		indexTree = new int[2*tempN];
		int idx, sum = 0;
		
		for(Type t : arr) {
			idx = tempN-1 + t.score;
			
			while(idx >= 1) {
				indexTree[idx] += 1;
				idx /= 2;
			}
			
			if(t.score != 1) sum = getSum(0, tempN-1, 0, t.score-2, 1);
			else sum = 0;
			
			bw.write((t.order-sum) + "\n"); // 원래 순서 - 0~본인 순서 이전까지의 부분합
		}
		
		bw.flush();
		bw.close();
	}
	public static int getTempN(int N) {
		int x = 1;
		
		while(x <= N) {
			x *= 2;
		}
		
		return x;
	}
	public static int getSum(int currStart, int currEnd, int getStart, int getEnd, int currIdx) {
		if(currEnd < getStart || getEnd < currStart) return 0;
		if(getStart <= currStart && currEnd <= getEnd) return indexTree[currIdx]; 
		return getSum(currStart, (currEnd+currStart)/2, getStart, getEnd, currIdx*2) + getSum((currEnd+currStart)/2+1, currEnd, getStart, getEnd, currIdx*2+1);
	}
}
