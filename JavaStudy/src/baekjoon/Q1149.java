// RGB�Ÿ� (���� 1149��)

/* [ �˰��� ]
 * 
 * 1. N�� �ּڰ��� ���Ѵ�.
 * 2. N�� �ּڰ��� �� �� �ִ� ��� (ex. N-2 : Blue ����)
 *  (1) Sum(N-2����) + (N-1)�� + N     -> N-1 : Red ���� 
 *  (2) Sum(N-2����) + (N-1)�� + N     -> N-1 : Green ����
 * 3. DP�� �޸������̼��� �̿��� ǰ 
 */
package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Q1149 {
	public static class type {
		private int R;
		private int G;
		private int B;
		public type(int R, int G, int B) {
			this.R = R;
			this.G = G;
			this.B = B;
		}
	}
	public static void main(String args[]) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine()); // ���� ��
		StringTokenizer st = new StringTokenizer("");
		type[] cost = new type[N];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			int red = Integer.parseInt(st.nextToken());
			int green = Integer.parseInt(st.nextToken());
			int blue = Integer.parseInt(st.nextToken());
			cost[i] = new type(red, green, blue);
		}
		
		boolean[] check = new boolean[3]; // R, G, B
		
		for(int i=0; i<N; i++) {
			System.out.println(cost[i].R + " " + cost[i].G + " " + cost[i].B);
		}
		
		int[][] dp = new int[N][3]; // color -> 0 : R, 1 : G, 2: B
		
		System.out.println(Arrays.deepToString(dp));
		
		dp[0][0] = cost[0].R;
		dp[0][1] = cost[0].G;
		dp[0][2] = cost[0].B;
		
		System.out.println("2 : " + Arrays.deepToString(dp));
		
		for(int c=0; c<3; c++) { // color 3���� ����� �� ��� ���ϱ� ����
			for(int i=1; i<N; i++) {
				System.out.println("������? " + "c : " + c + " " + " i : " + i +  " dp[i][c] : " + dp[i-1][1] + " " + cost[i].R);
				if(dp[i][c]==0) { // RED �� ���
					switch(c) {
						case 0: dp[i][c] = Math.min(dp[i-1][1], dp[i-1][2]) + cost[i].R; System.out.println("������R"); break; 
						case 1: dp[i][c] = Math.min(dp[i-1][0], dp[i-1][2]) + cost[i].G; break;
						case 2: dp[i][c] = Math.min(dp[i-1][0], dp[i-1][1]) + cost[i].B; break;
						default : break;
					}
					System.out.println(dp[i][c] + " -> " + (dp[i-1][1] + cost[i].R));
				}
			}
		}
		
		System.out.println("3 : " + Arrays.deepToString(dp));
		
		int min = Integer.MAX_VALUE;
		for(int c=0; c<3; c++) {
			if(min > dp[N-1][c]) min = dp[N-1][c];
		}
		
		System.out.print(min);
	}
}
