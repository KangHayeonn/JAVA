// Ư�� �Ÿ��� ���� ã��

/* [ �˰��� ]
 * 
 * 1. BFS Ȱ��
 */
package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

public class Q18352 {
	public static void main(String args[]) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // ����(����)�� ����
		int M = Integer.parseInt(st.nextToken()); // ����(����)�� ����
		int K = Integer.parseInt(st.nextToken()); // �Ÿ� ����
		int X = Integer.parseInt(st.nextToken()); // ��� ������ ��ȣ
		
		Map<Integer, ArrayList<Integer>> map = new HashMap<>();
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			ArrayList<Integer> value = map.containsKey(from)? map.get(from) : new ArrayList<>();
			value.add(to);
			map.put(from, value);
		}
		
		boolean[] isVisited = new boolean[N+1];
		//DFS_Stack_LL(list, isVisited, N, M, start);
		BFS_Queue_Array(map, isVisited, N, X);
	}
	
	// BFS ���� : ������� & ť
	public static void BFS_Queue_Array(Map<Integer, ArrayList<Integer>> map, boolean[] isVisited, int N, int V) {
		Queue<Integer> queue = new LinkedList<Integer>();
		isVisited[V] = true; // 1
		queue.add(V); // 1
		
		while(!queue.isEmpty()) {
			int x = queue.poll(); // 1
			System.out.println(x + " ");
			if(map.get(x) == null) continue;
			for(Integer i: map.get(x)) {
				//System.out.println(i);
				if(!isVisited[i]) {
					queue.add(i);
					isVisited[i] = true;
				}
			}
		}
	}
}
