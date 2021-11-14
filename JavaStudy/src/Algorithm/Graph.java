package Algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class Graph {
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // ������ ����
		int M = Integer.parseInt(st.nextToken()); // ������ ����
		int start = Integer.parseInt(st.nextToken()); // Ž���� ������ ������ ��ȣ
		boolean[] isVisited = new boolean[N+1];
		
		// ������ķ� ����
		/*
		int[][] arr = new int[N+1][N+1];
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			
			// �����
			arr[from][to] = 1;
			arr[to][from] = 1; // �ܹ����� ��� �̺κ� ����
		}
		//DFS_Recursion_Array(arr, isVisited, N, M, start);
		//DFS_Stack_Array(arr, isVisited, N, M, start);
		BFS_Queue_Array(arr, isVisited, N, M, start);
		*/
		
		// ��������Ʈ�� ����
		
		LinkedList<Integer>[] list = new LinkedList[N+1]; // ���� �� ��ŭ �迭 ����(���Ḯ��Ʈ ����)
		
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
		
		for (int i = 1; i <= N; i++) { // �湮 ������ ���� �������� ���� 
			Collections.sort(list[i]);
		}
		
		DFS_Recursion_LL(list, isVisited, N, M, start);
		System.out.println("");
		isVisited = new boolean[N+1];
		//DFS_Stack_LL(list, isVisited, N, M, start);
		BFS_Queue_LL(list, isVisited, N, M, start);
		
		// ���Ḯ��Ʈ ��¹�
		/*
		for(int i=0; i<N+1; i++) {
			System.out.print(i + " : ");
			for(Integer j : list[i]) {
				System.out.print(j+ " ");
			}
			System.out.println("");
		}*/
	}
	
	// DFS ���� : ������� & ���
	public static void DFS_Recursion_Array(int[][] arr, boolean[] isVisited, int N, int M, int V) {
		isVisited[V] = true;
		System.out.print(V + " ");
		
		for(int i=1; i<N+1; i++) {
			if(arr[V][i] == 1 && !isVisited[i]) DFS_Recursion_Array(arr, isVisited, N, M, i);
		}
	}
	
	// DFS ���� : ��������Ʈ & ���
	public static void DFS_Recursion_LL(LinkedList<Integer>[] list, boolean[] isVisited, int N, int M, int V) {
		isVisited[V] = true;
		System.out.print(V + " ");
		
		for(Integer j : list[V]) {
			if(!isVisited[j]) DFS_Recursion_LL(list, isVisited, N, M, j);
		}
	}
	
	// DFS ���� : ������� & ����
	public static void DFS_Stack_Array(int[][] arr, boolean[] isVisited, int N, int M, int V) {
		Stack<Integer> stack = new Stack<Integer>();
		boolean check;
		isVisited[V] = true;
		System.out.print(V + " ");
		stack.push(V);
		
		while(!stack.isEmpty()) {
			int x = stack.peek();
			check = false;
			
			for(int i=1; i<N+1; i++) {
				if(arr[x][i] == 1 && !isVisited[i]) {
					stack.push(i);
					System.out.print(i + " ");
					check = true;
					isVisited[i] = true;
					break; // �ʼ�
				}
			}
			
			if(!check) stack.pop();
		}
	}
	
	// DFS ���� : ��������Ʈ & ����
	public static void DFS_Stack_LL(LinkedList<Integer>[] list, boolean[] isVisited, int N, int M, int V) {
		Stack<Integer> stack = new Stack<Integer>();
		boolean check;
		isVisited[V] = true;
		System.out.print(V + " ");
		stack.push(V);
		
		while(!stack.isEmpty()) {
			int x = stack.peek();
			check = false;
			
			for(Integer i : list[x]) {
				if(!isVisited[i]) {
					stack.push(i);
					System.out.print(i + " ");
					check = true;
					isVisited[i] = true;
					break; // �ʼ�
				}
			}
			
			if(!check) stack.pop();
		}
	}
	
	// BFS ���� : ������� & ť
	public static void BFS_Queue_Array(int[][] arr, boolean[] isVisited, int N, int M, int V) {
		Queue<Integer> queue = new LinkedList<Integer>();
		isVisited[V] = true;
		queue.add(V);
		
		while(!queue.isEmpty()) {
			int x = queue.poll();
			System.out.print(x + " ");
			
			for(int i=1; i<N+1; i++) {
				if(arr[x][i] == 1 && !isVisited[i]) {
					queue.add(i);
					isVisited[i] = true;
				}
			}
		}
	}
	
	// BFS ���� : ��������Ʈ & ť
	public static void BFS_Queue_LL(LinkedList<Integer>[] list, boolean[] isVisited, int N, int M, int V) {
		Queue<Integer> queue = new LinkedList<Integer>();
		isVisited[V] = true;
		queue.add(V);
		//System.out.print(V + " ");
		
		while(!queue.isEmpty()) {
			int x = queue.poll();
			System.out.print(x + " ");
			
			Iterator<Integer> iter = list[x].listIterator();
			
			while(iter.hasNext()) {
				int y = iter.next();
				if(!isVisited[y]) {
					queue.add(y);
					isVisited[y] = true;
					//System.out.print(y + " ");
				}
			}
		}
	}
}