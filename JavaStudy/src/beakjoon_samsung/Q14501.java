// 퇴사 (백준 실버3)

package beakjoon_samsung;

import java.io.*;
import java.util.*;

public class Q14501 {
	public static class Type{
		int T, P;
		public Type(int T, int P) {
			this.T = T;
			this.P = P;
		}
	}
	public static void main(String args[]) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		Type[] arr = new Type[N+2];
		// ArrayList<Type> arr = new ArrayList<>(); // 빈공간이 생길 수 있음
		
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int T = Integer.parseInt(st.nextToken());
			int P = Integer.parseInt(st.nextToken());
			arr[i] = new Type(T, P);
		}
		
		int[] dp = new int[N+2];
		
		for(int i=N-1; i>=0; i--) {
			if(i+arr[i].T >= N+1) dp[i] = dp[i+1];
			else {
				dp[i] = Math.max((arr[i].P+dp[i+arr[i].T]), dp[i+1]);
			}
		}
		
		System.out.println(dp[0]);
	}
}
