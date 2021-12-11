// 불 (백준 5427번)

/* [ 알고리즘 ] - BFS
 * 
 * 1. 매초마다, 상근이는 동서남북 인접한 칸으로 이동
 * 2. 매초마다, 불은 동서남북 인접한 칸으로 이동
 * 3. 상근이는 #을 만나면 이동 x , .을 만나면 이동가능 (좌표 바뀌고 1 추가) , * 있는 곳이면 이동 x
 * 4. 상근이가 이동할 곳이 없을 경우 IMPOSSIBLE을 출력해주고 바로 종료
 * 5. 건물을 벗어나게 되면 해당 초를 count 한뒤 탈출
 * 6. 불은 # 일 경우 퍼지지 않음, .일 경우 *로 좌표를 바꿈, @이와 만나면 해당 좌표를 *로 바꾸고 상근이 부분을 돌려줌 
 * 
 */
package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Q5427 {
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
		
		Node(){ // 생성자 함수 (초기화)
			x = -1;
			y = -1;
			fire = false;
			count = 0;
		}

		public int getX() {
			return x;
		}

		public void setX(int x) {
			this.x = x;
		}

		public int getY() {
			return y;
		}

		public void setY(int y) {
			this.y = y;
		}

		public boolean isFire() {
			return fire;
		}

		public void setFire(boolean fire) {
			this.fire = fire;
		}

		public int getCount() {
			return count;
		}

		public void setCount(int count) {
			this.count = count;
		}
	}
	
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int count = Integer.parseInt(br.readLine());
		
		while(count-->0) {
			q = new LinkedList<>(); // 큐를 초기화 시켜줘야 다른 테스트 케이스에서 값이 바뀜 
			                        //(매번 시켜주지 않으면 이전 테스트케이스에서 저장된 큐가 그대로 남아있음)
			String[] st = br.readLine().split(" ");
			w = Integer.parseInt(st[0]); // 건물의 너비
			h = Integer.parseInt(st[1]); // 건물의 높이
			
			String[][] building = new String[h][w];
			Node person = new Node();
			
			for(int i=0; i<h; i++) {
				st = br.readLine().split("");
				for(int j=0; j<w; j++) {
					//System.out.println("테스트" + i +" -> " + j);
					building[i][j] = st[j];
					if(st[j].equals("@")) {
						person.setX(j);
						person.setY(i);
						person.setFire(false);
						person.setCount(0);
					} else if(st[j].equals("*")) {
						Node n = new Node(); // 기본 생성자(객체 생성)
						/* [ 설명 ] 
						 * 이 부분에 선언해줘서 매번 다른 객체를 이용해 q에 넣어줘야 값이 바껴서 들어감
						 * 이 부분에 선언해주는게 아니라 위에 선언해줄 경우,
						 * 모든 큐가 한 메모리(n)를 가리키게 되서 마지막 갱신 값이 모든 큐의 값이 됨
						 */
						n.setX(j);
						n.setY(i);
						n.setFire(true);
						n.setCount(0);
						q.add(n); // q에 매번 다른 객체를 넣어줘야함
					}
				}
			}
			/*
			while(!q.isEmpty()) {
				Node test = q.poll();
				System.out.println(test.getX() +" " + test.getY() +" " + test.isFire() +" " + test.getCount());
			}*/
			
			//System.out.println(Arrays.deepToString(building));
			boolean[][] isVisited = new boolean[h][w];
			BFS(building, q, isVisited, person);
		}
	}
	public static void BFS(String[][] building, Queue<Node> q, boolean[][] isVisited, Node person) {
		q.add(person);
		isVisited[person.getY()][person.getX()] = true;
		
		while(!q.isEmpty()) {
			Node n = q.poll();
			int x = n.getX();
			int y = n.getY();
			int count = n.getCount();
			
			if ((x == 0 || y == 0 || x == w-1 || y == h-1) && n.isFire() == false) {
                System.out.println(count+1);
                return;
            }
			
			for(int i=0; i<4; i++) {
				int nowX = x + dx[i];
				int nowY = y + dy[i];
				
				if(nowX < 0 || nowY < 0 || nowX >= w || nowY >= h) continue;
				if(building[nowY][nowX].equals("#") || building[nowY][nowX].equals("*")) continue;
				
				if(n.isFire() == false && !isVisited[nowY][nowX]) {
					Node p = new Node();
					p.setX(nowX);
					p.setY(nowY);
					p.setFire(n.isFire());
					p.setCount(count+1);
					q.add(p);
					isVisited[nowY][nowX] = true;
				} else if(n.isFire() == true) {
					building[nowY][nowX] = "*";
					Node f = new Node();
					f.setX(nowX);
					f.setY(nowY);
					f.setFire(n.isFire());
					f.setCount(count+1);
					q.add(f);
				}
			}
		}
		System.out.println("IMPOSSIBLE");
	}
}
