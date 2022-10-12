// N과 M (2) (백준 실버3) - 조합

package baekjoon;

import java.io.*;
import java.util.*;

public class Q15650 {
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
		// combination(arr, tmpArr, 0, 0);
		
		boolean[] isVisited = new boolean[N];
		combination_recur(arr, isVisited, 0, 0);
		
		bw.flush();
		bw.close();
	}
	public static void combination(int[] arr, int[] tmpArr, int idx, int depth) throws IOException {
		if(depth == M) {
			for(int i=0; i<M; i++) {
				bw.write(tmpArr[i] + " ");
			}
			bw.write("\n");
			return;
		}
		
		for(int i=idx; i<N; i++) {
			tmpArr[depth] = arr[i];
			combination(arr, tmpArr, i+1, depth+1);
		}
	}
	public static void combination_recur(int[] arr, boolean[] isVisited, int idx, int depth) throws IOException {
		if(depth == M) {
			for(int i=0; i<N; i++) {
				if(isVisited[i]) bw.write(arr[i] + " ");
			}
			bw.write("\n");
			return;
		}
		if(idx == N) return;
		
		isVisited[idx] = true;
		combination_recur(arr, isVisited, idx+1, depth+1);
		isVisited[idx] = false;
		combination_recur(arr, isVisited, idx+1, depth); // 이부분 주의! (변경하는 값은 늘리고, false로 한 부분은 depth에서 카운팅 안해줌)
	}
}
