// 도영이가 만든 맛있는 음식 (백준 2961번)

// 신맛일 경우는 곱하고, 쓴맛일 경우는 더해서 그 둘의 합이 최소가 되는 요리의 차이를 출력한다. (절댓값)
// 조합이용
package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Q2961 {
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int arr[][] = new int[N][2];
		
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			arr[i][0] = Integer.parseInt(st.nextToken());
			arr[i][1] = Integer.parseInt(st.nextToken());
		}
		
		
		int min = Math.abs(arr[0][0] - arr[0][1]);
		for(int i=1; i<=N; i++) {
			Set<Integer> set = new HashSet<>();
			int differ = recursiveSum(arr, 0, i, 1, 0, set);
			if(min > differ) min = differ;
		}
		
		System.out.println(min);
	}
	
	public static int recursiveSum(int arr[][], int depth, int i, int sourSum, int bitterSum, Set<Integer> set) {
		if(i==0) {
			int Sum = Math.abs(sourSum-bitterSum);
			set.add(Sum);
			return Sum;
		}
		if(depth == arr.length) return Collections.min(set);
		/*
		check[depth] = true;
		recursiveSum(arr, check, depth+1, i-1, sourSum*arr[depth][0], bitterSum+arr[depth][1], set);
		check[depth] = false;
		recursiveSum(arr, check, depth+1, i, sourSum, bitterSum, set);
		*/
		recursiveSum(arr, depth+1, i-1, sourSum*arr[depth][0], bitterSum+arr[depth][1], set);
		recursiveSum(arr, depth+1, i, sourSum, bitterSum, set);
	
		return Collections.min(set);
	}
}
