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
		public int compareTo(Node n) {	// ����ġ ���� �������� ����
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

      /* �ֻ��� �θ� ã�� �Լ�(���) */
      public static int find(int x) {
		if(parent[x] == x) return x;
		return parent[x] = find(parent[x]);  // x�� �θ��� �θ�
	}
	/* �ֻ��� ��� ��ġ�� �Լ�*/
	public static void union(int a, int b) {
		parent[a]=b; // �ش� ������ ������ MST ���տ� �߰�
	}

    public static int solution(int n, int[][] costs) {
        int answer = 0;
        pq = new PriorityQueue<>();	// �ּ���
        parent = new int[n];	// ����Ŭ ���� Ȯ�� ����
        for(int i = 0; i < n; i++) {
			parent[i] = i;      // �θ�� �ڱ� �ڽ����� �ʱ�ȭ
		}

        // ����, ����ġ ���� �켱���� ť�� �ֱ�
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

                   		// �θ� �ٸ��ٸ�(����Ǿ����� �ʴٸ�)
			if(parentS != parentE) { 
				union(parentS, parentE); 	// ���� ����
				answer += node.weight;	// ����ġ ���
			}
			System.out.println("parent= " + Arrays.toString(parent));
		}

        return answer;
    }
}