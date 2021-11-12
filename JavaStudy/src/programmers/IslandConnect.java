// �� �����ϱ� (���α׷��ӽ� LEVEL3)

/* [ �˰��� ]
 * 
 * 1. DFS�� ǰ
 * 2. ���Ḯ��Ʈ �̿� (cost�� �������� ������������)
 * 3. DFS ������ �ش��ϴ� �ε���(��) ���� �ڽ�Ʈ�� ����ؼ� �迭�� �־���
 * 4. �迭���� �ּڰ��� ���
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
		LinkedList<type>[] list = new LinkedList[n]; // ���� �� ��ŭ �迭 ����(���Ḯ��Ʈ ����)
		
		for(int i=0; i<n; i++) {
			list[i] = new LinkedList<type>();
		}
		
		for(int i=0; i<costs.length; i++) {
			int from = costs[i][0];
			int to = costs[i][1];
			int c = costs[i][2]; // ���
			
			// �����
			list[from].add(new type(to, c));
			list[to].add(new type(from, c)); // �ܹ����� ��� �̺κ� ����
		}
		
		for(int i=0; i<n; i++) { // �湮 ������ ���� �������� ���� 
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
			if(check==n-1) totalCost.add(count); // ��� ��θ� �� �ֵ鸸 üũ
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
