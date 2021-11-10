// 바이러스

// DFS 이용

package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Stack;
import java.util.StringTokenizer;

public class Q2606 {
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int c_number = Integer.parseInt(br.readLine());
		int count = Integer.parseInt(br.readLine());
		StringTokenizer st;
		boolean[] isVisited = new boolean[c_number+1];
		
		LinkedList<Integer>[] list = new LinkedList[c_number+1]; // 정점 수 만큼 배열 생성(연결리스트 갯수)
		
		for(int i=0; i<=c_number; i++) {
			list[i] = new LinkedList<Integer>();
		}
		
		
		for(int i=0; i<count; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			
			// 양방향
			list[from].add(to);
			list[to].add(from); // 단방향일 경우 이부분 삭제
		}
		
		DFS_Stack_LL(list, isVisited, c_number, 1);			
	}
	
	// DFS 구현 : 인접리스트 & 스택
	public static void DFS_Stack_LL(LinkedList<Integer>[] list, boolean[] isVisited, int N, int V) {
		Stack<Integer> stack = new Stack<Integer>();
		boolean check;
		isVisited[V] = true;
		//System.out.print(V + " ");
		stack.push(V);
		int count = 0;
		
		while(!stack.isEmpty()) {
			int x = stack.peek();
			check = false;
			
			for(Integer i : list[x]) {
				if(!isVisited[i]) {
					stack.push(i);
					//System.out.print(i + " ");
					check = true;
					isVisited[i] = true;
					count++;
					break; // 필수
				}
			}
			
			if(!check) stack.pop();
		}
		
		System.out.print(count);
	}
}
