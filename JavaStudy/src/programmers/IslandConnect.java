// 섬 연결하기 (프로그래머스 LEVEL3)

/* [ 알고리즘 ]
 * 
 * 1. DFS로 품
 * 2. 연결리스트 이용 (cost를 기준으로 오름차순으로)
 * 3. DFS 순서에 해당하는 인덱스(섬) 별로 코스트를 계산해서 배열에 넣어줌
 * 4. 배열에서 최솟값을 출력
 * 
 */
package programmers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

public class IslandConnect {
	
	static ArrayList<Integer> totalCost = new ArrayList<>();
	static int count;
	static int check;
	
	public static class type {
		private int node;
		private int cost;
		public type(int node, int cost) {
			this.node = node;
			this.cost = cost;
		}
		public String toString() {
			return node + " : " + cost;
		}
	}
	
	public static void main(String args[]) {
		int[][] costs = {{0,1,1}, {0,2,2},{1,2,5},{1,3,3},{2,3,8},{3,4,1}};
		System.out.print(solution(5, costs));
	}
	public static int solution(int n, int[][] costs) {
		LinkedList<type>[] list = new LinkedList[n]; // 정점 수 만큼 배열 생성(연결리스트 갯수)
		
		for(int i=0; i<n; i++) {
			list[i] = new LinkedList<type>();
		}
		
		for(int i=0; i<costs.length; i++) {
			int from = costs[i][0];
			int to = costs[i][1];
			int c = costs[i][2]; // 비용
			
			// 양방향
			list[from].add(new type(to, c));
			list[to].add(new type(from, c)); // 단방향일 경우 이부분 삭제
		}
		
		for(int i=0; i<n; i++) { // 방문 순서를 위해 오름차순 정렬 
			Collections.sort(list[i], new Comparator<type>(){
				@Override
				public int compare(type s1, type s2) {
					return s1.cost - s2.cost;
				}
			});
		}
		/*
		for(int i=0; i<n; i++) {
			for(int j=0; j<list[i].size(); j++) {
				System.out.println(i+ " : " +list[i].get(j).node + " "+ list[i].get(j).cost + " ");
			}
			System.out.println("");
		}*/
		
		for(int i=0; i<n; i++) {
			//System.out.println("check : " + i + " ");
			count = 0;
			check = 0;
			boolean[] isVisited = new boolean[n];
			DFS_Recursion_LL(list, isVisited, n, i);
			if(check==n-1) totalCost.add(count); // 모든 경로를 돈 애들만 체크
		}
		//boolean[] isVisited = new boolean[n];
		//DFS_Recursion_LL(list, isVisited, n, 3);
		//System.out.println("ccc : " + count);
		return Collections.min(totalCost);
	}
	
	public static void DFS_Recursion_LL(LinkedList<type>[] list, boolean[] isVisited, int N, int V) {
		isVisited[V] = true;
		//System.out.print(V + " "); // 0 
		
		for(int j=0; j<list[V].size(); j++) { // 0 1
			if(!isVisited[list[V].get(j).node]) { // 1 2
				DFS_Recursion_LL(list, isVisited, N, list[V].get(j).node); count += list[V].get(j).cost;
				check++;
				break;
			}
		}
	}
}
