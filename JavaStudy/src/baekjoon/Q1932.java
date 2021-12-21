// 정수 삼각형 (백준 1932번)

/* [ 알고리즘 ]
 * 
 * 1. 문제와 반대로 아래에서 위로 올라오면서 큰 값을 선택하면서 올라옴
 * 2. 본인 노드를 기준으로 왼쪽 오른쪽 값중 큰 값을 취하고 본인 노드를 더해서 다음 노드의 값을 또 구해줌 (재귀 이용)
 * 3. 처음 값을 받아 주는 것에 있어서 좌우 값을 어떻게 선택할지 고민 
 * 4. 트리 처럼 2xn, 2xn+1 로 할 경우 문제가 됨 -> 따라서 행과 열을 기준으로 본인노드([column][row])와 본인 아래 왼쪽([column+1][row]), 본인 아래 오른쪽([column+1][row+1]) 로 입력값 받아줌
 */
package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q1932 {
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine()); // 삼각형의 크기
		StringTokenizer st = new StringTokenizer("");
		Integer[][] arr = new Integer[n][n];
		
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<i+1; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		Integer[][] dp = new Integer[n][n];

		System.out.print(DP(arr, dp,  n-1, 0, 0));
	}
	public static int DP(Integer[][] arr, Integer[][] dp, int n, int column, int row) {
		if(column==n) {
			return arr[column][row]; // 마지막 행은 그대로 출력
		}
		
		if(dp[column][row]==null) {
			dp[column][row] = Math.max(DP(arr, dp, n, column+1, row), DP(arr, dp, n, column+1, row+1)) + arr[column][row];
		}
		
		return dp[column][row];
	}
}
