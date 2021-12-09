// 사회망 서비스(SNS)

/* DP? 메모이제이션?
 * 
 */

package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Stack;
import java.util.StringTokenizer;

public class Q2533 {
	public static void main(String args[]) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine()); // 정점의 개수
		int[] level = new int[N+1]; // 각 노드별 level 저장 (최대 level 노드 찾기 위해)
		ArrayList<Integer> arr[] = new ArrayList[N+1];
		
		for(int i=1; i<N+1; i++) {
			arr[i] = new ArrayList<>();
		}
		
		for(int i=0; i<N-1; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			arr[from].add(to);
			arr[to].add(from);
			if(from < to) level[to] = level[from] + 1;
			else level[from] = level[to] + 1;
		}
		System.out.println(Arrays.toString(level));
		
		int max = Arrays.stream(level).max().getAsInt();
		Boolean[] isVisited = new Boolean[N+1]; // 방문했는지 체크
		Boolean[] check = new Boolean[N+1]; // 얼리어답터인지 체크
		Arrays.fill(isVisited, false);
		check[1] = true;
		
		for(int i=1; i<N+1; i++) {
			Collections.sort(arr[i]);
		}
		
		for(int i=1; i<=N; i++) {
			if(level[i] == max) {
				earlyadopter(arr, isVisited, check, level, N, i);
			}
		}
		/*
		// 트리 확인
		for(int i=1; i<N+1; i++) {
			for(int j=0; j<arr[i].size(); j++) {
				System.out.print(i + " -> " +arr[i].get(j)+ " ");
			}
			System.out.print("\n");
		}*/
		int count = 0;
		for(int i=0; i<check.length; i++) {
			if(check[i]==null) continue;
			if(check[i]) count++;
		}
		System.out.println(Arrays.toString(check) + " " + count);
	}
	
	public static void earlyadopter(ArrayList<Integer>[] graph, Boolean[] isVisited, Boolean[] check, int[] level, int N, int V) {
		Stack<Integer> stack = new Stack<>();
		boolean chk;
		isVisited[V] = true;
		check[V] = false;
		stack.push(V);
		
		while(!stack.isEmpty()) {
			int x = stack.peek();
			chk = false;

			for(Integer i : graph[x]) { // x : 현재 노드 , i : 다음 노드
				if(!isVisited[i]) {
					if(level[x] > level[i] && x != V) {
						if(check[i]==null) check[x] = true;
						else if(check[i]==true) {
							check[i] = false;
							//System.out.println("들어오나? " + x + " " + i);
							//System.out.println(Arrays.toString(check));
						}
					}
					if(level[x] < level[i] && x != V) {
						if(check[i]==null) check[x] = true;
						else if(check[i]==true) check[i] = false;
					}
					stack.push(i);
					chk = true;
					isVisited[i] = true;
					break; // 필수
				}
			}
			if(!chk) stack.pop();
		}
	}
}
