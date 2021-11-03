// N과 M(1)

// 순서가 있고 중복x -> 순열
package baekjoon;

import java.util.Scanner;

public class Q15649 {
	public static void main(String args[]) {
		Scanner s = new Scanner(System.in);
		int N = s.nextInt();
		int M = s.nextInt();
		int[] answer = new int[N];
		boolean visited[] = new boolean[N];
		permutation(answer, visited, 0, N, M);
		s.close();
	}
	
	public static void permutation(int[] answer, boolean[] visited, int depth, int N, int M) {
		if(depth == M) {
			print(answer, M);
			return;
		}
		for(int i=1; i<=N; i++) {
			if(visited[i-1] != true) {
				visited[i-1] = true;
				answer[depth] = i;
				permutation(answer, visited, depth+1, N, M);
				visited[i-1] = false;
			}
		}
	}
	
	public static void print(int[] answer, int M) {
		for(int i=0; i<M; i++) {
			System.out.print(answer[i]+" ");
		}
		System.out.print("\n");
	}
}
