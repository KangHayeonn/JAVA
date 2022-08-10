package baekjoon;

import java.io.*;
import java.util.*;

public class Q11266 {
	static ArrayList<Integer>[] arr;
	static int[] nodeOrder;
	static ArrayList<Integer> answer;
	static int order = 0;
	static boolean[] isChecked;
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		
		arr = new ArrayList[V+1];
		for(int i=1; i<V+1; i++) {
			arr[i] = new ArrayList<Integer>();
		}
		
		for(int i=0; i<E; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			
			arr[from].add(to);
			arr[to].add(from);
		}
		
		nodeOrder = new int[V+1]; // 노드 방문 순서 저장
		isChecked = new boolean[V+1]; // 이미 정답 배열에 넣은 단절점인지 체크 (단절점 중복 추가 방지)
		answer = new ArrayList<>(); // 정답 저장
		for(int i=1; i<V+1; i++) { // 연결그래프가 아닐 수도 있기 때문
			if(nodeOrder[i] == 0) { // 아직 방문하지 않은 경우(== 갱신 안된 경우)
				dfs(0, i); // 부모, 초기 루트 값
			}
		}
		
		System.out.println(answer.size());
		Collections.sort(answer);
		for(int i : answer) {
			System.out.print(i + " ");
		}
	}
	public static int dfs(int parent, int curr) {
		nodeOrder[curr] = ++order; // 1번부터 순서를 넣기 위해
		
		int child = 0; // 루트 노드일 경우만 사용
		int min = Integer.MAX_VALUE, value;
		
		// 루트 노드가 아닐 경우
		for(int i : arr[curr]) {
			
			// 방문하지 않은 노드의 경우
			if(nodeOrder[i] == 0) {
				value = dfs(curr, i); // 현재 기준 curr가 부모가 되고 그것과 연결된 애가 자식이 됨
				child += 1;
				
				// 부모쪽의 순서보다 더 크거나 같은 값을 리턴하면 해당 부모는 단절점
				if(value >= nodeOrder[curr] && !isChecked[curr] && parent != 0) {
					answer.add(curr);
					isChecked[curr] = true;
				}
				else min = Math.min(min, value);
			} else { // 방문한 노드의 경우 -> 기존의 저장된 값 중에서 최소값으로 갱신
				min = Math.min(min, nodeOrder[i]);
			}
		}
		
		// 루트 노드의 경우 -> 자식이 두개 이상이면 무조건 단절점
		if(parent == 0 && child > 1) {
			answer.add(curr);
			isChecked[curr] = true;
		}
		
		return min;
	}
}