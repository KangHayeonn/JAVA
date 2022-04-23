// 시험 감독 (백준 브론즈2)

package beakjoon_samsung;

import java.io.*;
import java.util.*;

public class Q13458 {
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int[] list = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			list[i] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(br.readLine());
		int B = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		long total = 0;
		
		for(int i=0; i<N; i++) {
			long value = list[i]-B; // 1-1
			if(value > 0) {
				total += (int)Math.ceil((float)value/C);
			}
			total += 1; // 총감독관
		}
		System.out.println(total);
	}
}
