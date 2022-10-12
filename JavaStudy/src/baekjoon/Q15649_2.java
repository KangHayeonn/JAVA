// N과 M (1) (백준 실버3) - 순열

package baekjoon;

// import java.util.Scanner;
import java.io.*;
import java.util.*;

public class Q15649_2 {
	static int N, M;
	static BufferedWriter bw;
	public static void main(String args[]) throws IOException {
		/*
		Scanner s = new Scanner(System.in);
		N = s.nextInt();
		M = s.nextInt();*/
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out)); // 시간 초과 때문에 추가
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		int[] arr = new int[N];
		for(int i=1; i<=N; i++) {
			arr[i-1] = i;
		}
		
		boolean[] isVisited = new boolean[N];
		int[] tmpArr = new int[M];
		permutation(arr, isVisited, tmpArr, 0);
		
		bw.flush();
		bw.close();
	}
	public static void permutation(int[] arr, boolean[] isVisited, int[] tmpArr, int depth) throws IOException {
		if(depth == M) {
			for(int i=0; i<M; i++) {
				bw.write(tmpArr[i] + " ");
			}
			bw.write("\n");
			return;
		}
		
		for(int i=0; i<N; i++) {
			if(!isVisited[i]) {
				isVisited[i] = true;
				tmpArr[depth] = arr[i];
				permutation(arr, isVisited, tmpArr, depth+1);
				isVisited[i] = false;
			}
		}
	}
}
