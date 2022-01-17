// ȿ������ ��ŷ (���� 1325��)

/* [ �˰��� ]
 * 
 * 1. A�� B�� �ŷ��� ��� (B->A�� ���� �ܹ��� �׷����� �����)
 * 2. DFS�� �̿��� ���� �������� �� �� �ִ� �ִ� ��带 �����Ѵ�.
 * 3. ��� �������� 2���� ���� ������.
 * 4. ��� ���� �� ���� ���� ū ��带 ã�� ������ ���� ��尡 ������ ���� ��� ������������ ����Ѵ�.
 */
package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Q1325 {
	static ArrayList<Integer>[] list;
	static int[] count;
	static boolean[] isVisited;
	static int cnt;
	
	public static void main(String args[]) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // ��� ����
		int M = Integer.parseInt(st.nextToken()); // ���� ����
		
		list = new ArrayList[N+1];
		
		for(int i=0; i<=N; i++) {
			list[i] = new ArrayList<Integer>();
		}
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			
			// A�� B�� �ŷ��Ѵ�.
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			
			list[B].add(A);
		}
		
		count = new int[N+1];
		for(int i=1; i<=N; i++) {
			isVisited = new boolean[N+1];
			isVisited[i] = true;
			dfs(N, M, i, i);
		}
		
		int Max = 0;
		for(int i=1; i<=N; i++) {
			if(Max < count[i]) Max = count[i];
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i=1; i<=N; i++) {
			if(Max==count[i]) sb.append(i+" ");
		}
		System.out.print(sb);
	}
	
	public static void dfs(int N, int M, int V, int start) {
		if(V > N) return;
		
		for(Integer i : list[V]) {
			if(!isVisited[i]) {
				dfs(N, M, i, start);
				isVisited[V] = true;
				count[start]++;
			}
		}
	}
}
