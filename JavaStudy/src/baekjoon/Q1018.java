// ü���� �ٽ� ĥ�ϱ� (���� 1018��)

package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q1018 {
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // ��
		int M = Integer.parseInt(st.nextToken()); // ��
		
		int arr[][] = new int[N][M];
		
		for(int i=0; i<N; i++) {
			String str = br.readLine();
			
			for(int j=0; j<M; j++) {
				arr[i][j] = str.charAt(j);
			}
		}
		
		for(int i=0; i <= N-8; i++) {
			for(int j=0; j <= M-8; j++) {
				
			}
		}
	;}
}
