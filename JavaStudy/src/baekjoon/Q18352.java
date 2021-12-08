// 특정 거리의 도시 찾기

/* [ 알고리즘 ]
 * 
 * 1. BFS 활용
 */
package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

public class Q18352 {
	//static StringBuilder sb = new StringBuilder();
	static ArrayList<Integer> arr = new ArrayList<>();
	public static class type {
		private int depth; 
		private int node; 
		
		public type (int depth, int node) {
			this.depth = depth;
			this.node = node;
		}
	}
	public static void main(String args[]) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 도시(정점)의 개수
		int M = Integer.parseInt(st.nextToken()); // 도로(간선)의 개수
		int K = Integer.parseInt(st.nextToken()); // 거리 정보
		int X = Integer.parseInt(st.nextToken()); // 출발 도시의 번호
		
		int[] city = new int[N+1];
		
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
		BFS_Queue_Array(map, isVisited, city, N, X, K);
		//System.out.print(sb);
		Collections.sort(arr);
		
		if(arr.size()==0) {
			System.out.print(-1);
		} else {
			for(int i=0; i<arr.size(); i++) {
				System.out.println(arr.get(i));
			}
		}
	}
	
	// BFS 구현 : 인접행렬 & 큐
	public static void BFS_Queue_Array(Map<Integer, ArrayList<Integer>> map, boolean[] isVisited, int[] city, int N, int V, int K) {
		Queue<type> queue = new LinkedList<type>();
		isVisited[V] = true; 
		queue.add(new type(0, V));
		city[V] = 0;
		
		while(!queue.isEmpty()) {
			type q = queue.poll();
			int depth = q.depth; 
			int x = q.node; 

			if(map.get(x) == null) continue;
			
			for(Integer i: map.get(x)) { 
				if(!isVisited[i]) {
					queue.add(new type(depth+1, i));
					isVisited[i] = true;
					if(city[i] == 0 || city[i] > depth+1) {
						city[i] = depth+1;			
					}
				}
			}
		}	
		//boolean check = false;
		for(int i=1; i<city.length; i++) {
			if(city[i] == K) {
				//sb.append(i).append("\n");
				//check = true;
				arr.add(i);
			}
		}
		/*
		if(check==false) {
			sb.append(-1).append("\n");
		}*/
	}
}
