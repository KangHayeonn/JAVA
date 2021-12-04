// 트리

/* [ 알고리즘 ]
 * 
 * 1. 사이클이 있는지 확인하는 함수를 만들어 줌 (사이클O -> 트리X)
 * 2. 사이클이 없을 경우 트리 개수를 count 해줌
 * 3. 사이클이 있을 경우 패스
 * 4. 만약 트리 개수가 0일 경우 No trees. 출력
 * 5. 트리 개수가 하나일 경우 There is one tree.
 * 6. 트리개수가 1보다 클 경우 A forest of T(>1) trees.를 출력
 * 
 */

// NullPointerException 발생 -> 처음에 바로 0 0 으로 입력이 들어올 경우!
// 따로 범위 분리해줌.

/* [ 추가 주의사항 ]
 * 
 * 1. 1개라도 그래프가 있으면 No trees가 아니라 카운트에 포함시키지 않을 뿐
 * 2. 루트 노드가 정해져 있지 않음 a->a도 가능 (길이가 1인 사이클)
 * 
 */

package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Q4803 {
	static ArrayList<Integer>[] adj;
	static boolean[] visited;
	static int max = 0;
	
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int testCount=0;
		StringBuilder sb = new StringBuilder();
		
		while(true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken()); // 정점의 개수
			int m = Integer.parseInt(st.nextToken()); // 간선의 개수
			
			if(n==0 && m==0) break;

			testCount++; // 테스트 케이스의 개수를 세기 위해
			
			adj = new ArrayList[n+1];
			for(int i=1; i<n+1; i++) {
				adj[i] = new ArrayList<>();
			}
			
			for(int i=0; i<m; i++) {
				st = new StringTokenizer(br.readLine());
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				adj[from].add(to);
				adj[to].add(from);
			}
			
			// 트리 확인
			/*
			for(int i=1; i<n+1; i++) {
				for(int j=0; j<adj[i].size(); j++) {
					System.out.print(i + " -> " +adj[i].get(j)+ " ");
				}
				System.out.print("\n");
			}*/
			
			visited = new boolean[n+1];
			int count = 0;
			
			// 방문하지 않은 노드들을 기준으로 cycle을 다시 확인해봄 -> cycle이 없으면 트리이므로 그때 따로 count 해줌
			for(int i=1; i<n+1; i++) {
				if(!visited[i] && !isCycle(i, 0)) count++; 
			}
			
			if(count==0) {
				sb.append("Case ").append(testCount).append(": No trees.").append("\n");
			} else if(count==1) {
				sb.append("Case ").append(testCount).append(": There is one tree.").append("\n");
			} else {
				sb.append("Case ").append(testCount).append(": A forest of ").append(count).append(" trees.").append("\n");
			}
		}
		
		System.out.print(sb);
		return;
	}
	
	//DFS 이용 (true: 사이클 , false: 트리)
	public static boolean isCycle(int curr, int parent) {
		visited[curr] = true;
		
		for(int next : adj[curr]) {
			if(!visited[next]) {
				if(isCycle(next, curr)) { // 그래프가 사이클이 있는지 확인
					return true;
				}
			} else if(next != parent) { // 방문한 기록이 있는데 부모와 동일하지 않을 경우
				return true;
			}
		}
		return false;
	}
}
