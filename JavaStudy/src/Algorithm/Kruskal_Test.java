package Algorithm;

import java.util.PriorityQueue;
import java.util.Arrays;

public class Kruskal_Test {
	public static void main(String args[]) {
		int[][] costs = {{0,1,1}, {0,2,2},{1,2,5},{1,3,3},{2,3,8},{3,4,1}};
		System.out.print(solution(5, costs));
	}
	static class Node implements Comparable<Node>{
		int start;
		int end;
		int weight;

		public Node(int s, int e, int w) {
			this.start = s;
			this.end = e;
			this.weight = w;
	}

		@Override
		public int compareTo(Node n) {	// 가중치 기준 오름차순 정렬
			return this.weight - n.weight;
		}
	      @Override
	      public String toString(){
	             return "island(" 
	            + this.start + ", " + this.end + ", " + this.weight + ")"; 
	      }

	}
    static PriorityQueue<Node> pq;
    static int[] parent;

      /* 최상위 부모 찾는 함수(재귀) */
      public static int find(int x) {
		if(parent[x] == x) return x;
		return parent[x] = find(parent[x]);  // x의 부모의 부모
	}
	/* 최상위 노드 합치는 함수*/
	public static void union(int a, int b) {
		parent[a]=b; // 해당 간선을 현재의 MST 집합에 추가
	}

    public static int solution(int n, int[][] costs) {
        int answer = 0;
        pq = new PriorityQueue<>();	// 최소힙
        parent = new int[n];	// 사이클 여부 확인 위해
        for(int i = 0; i < n; i++) {
			parent[i] = i;      // 부모는 자기 자신으로 초기화
		}

        // 간선, 가중치 정보 우선순위 큐에 넣기
		for(int i=0; i<costs.length; i++) {
			int s = costs[i][0];
			int e = costs[i][1];
			int w = costs[i][2];

			pq.add(new Node(s, e, w)); 
		}

        while(!pq.isEmpty()) {
			Node node = pq.poll();

			int parentS = find(node.start);
			int parentE = find(node.end);
			System.out.println(parentS +"/"+ parentE);

                   		// 부모가 다르다면(연결되어있지 않다면)
			if(parentS != parentE) { 
				union(parentS, parentE); 	// 둘을 연결
				answer += node.weight;	// 가중치 계산
			}
			System.out.println("parent= " + Arrays.toString(parent));
		}

        return answer;
    }
}