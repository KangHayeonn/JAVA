// N과 M (8) - 중복조합

package baekjoon;

import java.io.*;
import java.util.*;

public class Q15657 {
	static int N, M;
	static BufferedWriter bw;
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		int[] arr = new int[N];
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(arr);
		
		int[] tmpArr = new int[M];
		reCombination(arr, tmpArr, 0, 0);
		
		bw.flush();
		bw.close();
	}
	public static void reCombination(int[] arr, int[] tmpArr, int idx, int depth) throws IOException {
		if(depth == M) {
			for(int i=0; i<M; i++) {
				bw.write(tmpArr[i] + " ");
			}
			bw.write("\n");
			return;
		}
		
		if(idx == N) return;
		/*
		for(int i=idx; i<N; i++) {
			tmpArr[depth] = arr[i];
			reCombination(arr, tmpArr, idx, depth+1); // 뽑은 걸 또 뽑을 수 있음 (idx+1이 아닌 idx로 넣음)
		}*/
		
		tmpArr[depth] = arr[idx];
		reCombination(arr, tmpArr, idx, depth+1); // 뽑는 경우
		reCombination(arr, tmpArr, idx+1, depth); // 안뽑는 경우
	}
}
