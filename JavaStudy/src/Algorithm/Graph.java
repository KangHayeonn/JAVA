package Algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Graph {
	public static class type {
		private int a;  
		
		public type (int a) {
			this.a = a;
		}
		
		public String toString() {
			return a + " ";
			//return value + "";
		}
	}
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // ������ ����
		int M = Integer.parseInt(st.nextToken()); // ������ ����
		int start = Integer.parseInt(st.nextToken()); // Ž���� ������ ������ ��ȣ
		
		LinkedList<Integer>[] list = new LinkedList[N+1]; // ���� �� ��ŭ �迭 ����(���Ḯ��Ʈ ����)
		boolean[] isVisited = new boolean[N+1];
		
		for(int i=0; i<=N; i++) {
			list[i] = new LinkedList<Integer>();
		}
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			
			// �����
			list[from].add(to);
			list[to].add(from); // �ܹ����� ��� �̺κ� ����
		}
		
		//System.out.println(Arrays.toString(isVisited));
		
		// ���Ḯ��Ʈ ��¹�
		/*
		for(int i=0; i<N+1; i++) {
			System.out.print(i + " : ");
			for(Integer j : list[i]) {
				System.out.print(j+ " ");
			}
			System.out.println("");
		}*/
		
		for (int i = 1; i <= N; i++) { // �湮 ������ ���� �������� ���� 
			Collections.sort(list[i]);
		}
		
		DFS_Recursion_LL(list, isVisited, N, M, start);
		
	}
	
	public static void DFS_Recursion_LL(LinkedList<Integer>[] list, boolean[] isVisited, int N, int M, int V) {
		isVisited[V] = true;
		System.out.print(V + " ");
		
		for(Integer j : list[V]) {
			if(!isVisited[j]) DFS_Recursion_LL(list, isVisited, N, M, j);
		}
		
	}
}
