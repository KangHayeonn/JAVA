// 연산자 끼워넣기 (백준 실버1)

// 백트래킹

package beakjoon_samsung;

import java.io.*;
import java.util.*;

public class Q14888 {
	static ArrayList<Integer> arr;
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] numList = new int[N];
		arr = new ArrayList<>();
		
		for(int i=0; i<N; i++) {
			numList[i] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(br.readLine());
		int add = Integer.parseInt(st.nextToken());
		int sub = Integer.parseInt(st.nextToken());
		int mul = Integer.parseInt(st.nextToken());
		int div = Integer.parseInt(st.nextToken());
		
		calculator(add, sub, mul, div, numList[0], 1, numList);
		Collections.sort(arr);
		System.out.println(arr.get(arr.size()-1));
		System.out.println(arr.get(0));
	}
	public static void calculator(int add, int sub, int mul, int div, int total, int depth, int[] numList) {
		if(depth==numList.length) {
			arr.add(total);
			return;
		}
		
		if(add > 0) calculator(add-1, sub, mul, div, total+numList[depth], depth+1, numList);
		if(sub > 0) calculator(add, sub-1, mul, div, total-numList[depth], depth+1, numList);
		if(mul > 0) calculator(add, sub, mul-1, div, total*numList[depth], depth+1, numList);
		if(div > 0) calculator(add, sub, mul, div-1, total/numList[depth], depth+1, numList);
	}
}
