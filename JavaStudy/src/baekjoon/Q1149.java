// RGB�Ÿ� (���� 1149��)

/* [ �˰��� ]
 * 
 * 1. N�� �ּڰ��� ���Ѵ�.
 * 2. N�� �ּڰ��� �� �� �ִ� ��� 
 *  (1) (N-1)�� + N     -> N : RED, N-1 : GREEN or BLUE (�ּڰ�) ���� 
 *  (2) (N-1)�� + N     -> N : GREEN, N-1 : RED or BLUE (�ּڰ�) ���� 
 *  (2) (N-1)�� + N     -> N : BLUE, N-1 : RED or GREEN (�ּڰ�) ����
 * 3. DP�� �޸������̼��� �̿��� ǰ 
 */
package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
		/*
		for(int i=0; i<N; i++) {
			System.out.println(cost[i].R + " " + cost[i].G + " " + cost[i].B);
		}*/
		
		int[][] dp = new int[N][3]; // color -> 0 : R, 1 : G, 2: B
		
		dp[0][0] = cost[0].R;
		dp[0][1] = cost[0].G;
		dp[0][2] = cost[0].B;
		
		for(int i=1; i<N; i++) {  // �ݺ����� ���� �߿�! (color�� �����ϰ� �Ǹ� ���� ������� ����)
			for(int c=0; c<3; c++) { // color 3���� ����� �� ��� ���ϱ� ����
				if(dp[i][c]==0) { 
					switch(c) {
						case 0: dp[i][c] = Math.min(dp[i-1][1], dp[i-1][2]) + cost[i].R; break; // RED �� ���
						case 1: dp[i][c] = Math.min(dp[i-1][0], dp[i-1][2]) + cost[i].G; break; // GREEN �� ���
						case 2: dp[i][c] = Math.min(dp[i-1][0], dp[i-1][1]) + cost[i].B; break; // BLUE �� ���
						default : break;
					}
				}
			}
		}
		
		int min = Integer.MAX_VALUE;
		for(int c=0; c<3; c++) {
			if(min > dp[N-1][c]) min = dp[N-1][c];
		}
		
		System.out.print(min);
	}
}
