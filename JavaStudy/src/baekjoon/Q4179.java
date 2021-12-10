// 불! (백준 4179번)

// BFS 이용
package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Q4179 {
	static int[] dx = {0, 0, -1, 1}; // 상하좌우
	static int[] dy = {1, -1, 0, 0};
	static Queue<Node> q;
	static int w; // 건물의 너비
	static int h; // 건물의 높이
	
	public static class Node{
		private int x;
		private int y;
		private boolean fire; // 불이면 true, 상근이면 false
		private int count;
		
		public Node(int x, int y, boolean fire, int count){ // 생성자 함수 
			this.x = x;
			this.y = y;
			this.fire = fire;
			this.count = count;
		}
	}
	
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		q = new LinkedList<>(); 
		String[] st = br.readLine().split(" ");
		h = Integer.parseInt(st[0]); // 건물의 높이
		w = Integer.parseInt(st[1]); // 건물의 너비
		
		String[][] building = new String[h][w];
		Node person = new Node(-1, -1, false, 0); // 생성자(객체?) 초기화
		
		for(int i=0; i<h; i++) {
			st = br.readLine().split("");
			for(int j=0; j<w; j++) {
				//System.out.println("테스트" + i +" -> " + j);
				building[i][j] = st[j];
				if(st[j].equals("J")) {
					person = new Node(j, i, false, 0);
				} else if(st[j].equals("F")) {
					q.add(new Node(j, i, true, 0)); // q에 매번 다른 객체를 넣어줘야함
				}
			}
		}

		boolean[][] isVisited = new boolean[h][w];
		BFS(building, q, isVisited, person);
		
	}
	public static void BFS(String[][] building, Queue<Node> q, boolean[][] isVisited, Node person) {
		q.add(person);
		isVisited[person.y][person.x] = true;
		
		while(!q.isEmpty()) {
			Node n = q.poll();
			int x = n.x;
			int y = n.y;
			int count = n.count;
			
			if ((x == 0 || y == 0 || x == w-1 || y == h-1) && n.fire == false) {
                System.out.println(count+1);
                return;
            }
			
			for(int i=0; i<4; i++) {
				int nowX = x + dx[i];
				int nowY = y + dy[i];
				
				if(nowX < 0 || nowY < 0 || nowX >= w || nowY >= h) continue;
				if(building[nowY][nowX].equals("#") || building[nowY][nowX].equals("F")) continue;
				
				if(n.fire == false && !isVisited[nowY][nowX]) {
					q.add(new Node(nowX, nowY, n.fire, count+1));
					isVisited[nowY][nowX] = true;
				} else if(n.fire == true) {
					building[nowY][nowX] = "F";
					q.add(new Node(nowX, nowY, n.fire, count+1));
				}
			}
		}
		System.out.println("IMPOSSIBLE");
	}
}

