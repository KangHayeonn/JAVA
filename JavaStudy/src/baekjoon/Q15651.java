// N과 M (3) (백준 실버3) - 중복순열

package baekjoon;

import java.io.*;
import java.util.*;

public class Q15651 {
	static int N, M;
	static BufferedWriter bw;
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		int[] arr = new int[N];
		for(int i=1; i<=N; i++) {
			arr[i-1] = i;
		}
		
		int[] tmpArr = new int[M];
		rePermutation(arr, tmpArr, 0);
		bw.flush();
		bw.close();
	}
	public static void rePermutation(int[] arr, int[] tmpArr, int depth) throws IOException {
		if(depth == M) {
			for(int i=0; i<M; i++) {
				bw.write(tmpArr[i] + " ");
			}
			bw.write("\n");
			return;
		}
		
		for(int i=0; i<N; i++) {
			tmpArr[depth] = arr[i];
			rePermutation(arr, tmpArr, depth+1);
		}
	}
}
