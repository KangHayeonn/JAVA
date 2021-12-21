// 가장 긴 바이토닉 부분 수열

/* [ 알고리즘 ]
 * 
 * LIS(최장 증가 수열) + LDS(최장 감소 수열)
 */
package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q11054 {
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine()); // 수열 A의 크기 N
		StringTokenizer st = new StringTokenizer(br.readLine());
		int seq[] = new int[N];
		
		for(int i=0; i<N; i++) {
			seq[i] = Integer.parseInt(st.nextToken());
		}
		
		int LIS[] = new int[N];
		int LDS[] = new int[N];
		
		// LIS
		for(int i=0; i<N; i++) {
			if(LIS[i]==0) {
				LIS[i] = 1;
				for(int j=i-1; j>=0; j--) {
					if(seq[i] > seq[j])
						LIS[i] = Math.max(LIS[i], LIS[j]+1);
				}
			}
		}
		
		// LDS
		for(int i=N-1; i>=0; i--) { 
			if(LDS[i]==0) {
				LDS[i] =1;
				for(int j=i+1; j<N; j++) {
					if(seq[i] > seq[j]) {
						LDS[i] = Math.max(LDS[i], LDS[j]+1);
					}
				}
			}
		}
		
		int max = 0;
		for(int i=0; i<N; i++) {
			int sum = LIS[i]+LDS[i] -1; // 중복되는거 하나 빼줌
			if(max < sum) max = sum;
		}
		System.out.print(max);
	}
}
