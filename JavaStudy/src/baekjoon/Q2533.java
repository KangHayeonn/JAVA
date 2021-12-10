// 사회망 서비스(SNS) (백준 2533번)

/* 노드 1부터 얼리어답터일 경우와 아닐 경우로 나누어 경우의 수를 구해줌 
 * - ver1 : DP 이용
 * - ver2 : DFS 이용
 */

package baekjoon;

// 레벨이 큰 노드부터 작은 노드로 dfs 하면서 체크 -> 실패
/*
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
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Q2533 {
	static int[][] dpList;
	static boolean[] isVisited; 
	public static void main(String args[]) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine()); // 정점의 개수
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
		}
		
		dpList = new int[N+1][2]; // 배열의 크기가 2인 이유 : 해당 노드(인덱스)에서 얼리어답터인 경우(O) || 얼리어답터가 아닌 경우(X)
		isVisited = new boolean[N+1];
		//dp(arr, 1, 0);
		dfs(arr, 1);
		System.out.print(Math.min(dpList[1][0], dpList[1][1]));
	}
	// dp 버전
	public static void dp(ArrayList<Integer>[] tree, int child, int parent) {
		dpList[child][0] = 0; // 해당 노드에서 얼리어답터가 아니라고 가정한 경우 -> 갯수 카운트 0 해줌
		dpList[child][1] = 1; // 해당 노드에서 얼리어답터라고 가정한 경우 -> 갯수 카운트 1 해줌
		
		for(Integer next: tree[child]) {
			if(next != parent) { // 자신이 넘어온 parent값과 같으면 계속 반복됨 (순환있으면 안됨)
				dp(tree, next, child);
				dpList[child][0] += dpList[next][1];
				dpList[child][1] += Math.min(dpList[next][0], dpList[next][1]);
			}
		}
	}
	// dfs 버전
	public static void dfs(ArrayList<Integer>[] tree, int node) {
		dpList[node][0] = 0;
		dpList[node][1] = 1;
		isVisited[node] = true;
		
		for(Integer next : tree[node]) {
			if(!isVisited[next]) {
				dfs(tree, next);
				dpList[node][0] += dpList[next][1];
				dpList[node][1] += Math.min(dpList[next][0], dpList[next][1]);
			}
		}
	}
}