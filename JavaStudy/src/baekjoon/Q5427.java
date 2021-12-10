// �� (���� 5427��)

/* [ �˰��� ] - BFS
 * 
 * 1. ���ʸ���, ����̴� �������� ������ ĭ���� �̵�
 * 2. ���ʸ���, ���� �������� ������ ĭ���� �̵�
 * 3. ����̴� #�� ������ �̵� x , .�� ������ �̵����� (��ǥ �ٲ�� 1 �߰�) , * �ִ� ���̸� �̵� x
 * 4. ����̰� �̵��� ���� ���� ��� IMPOSSIBLE�� ������ְ� �ٷ� ����
 * 5. �ǹ��� ����� �Ǹ� �ش� �ʸ� count �ѵ� Ż��
 * 6. ���� # �� ��� ������ ����, .�� ��� *�� ��ǥ�� �ٲ�, @�̿� ������ �ش� ��ǥ�� *�� �ٲٰ� ����� �κ��� ������ 
 * 
 */
package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Q5427 {
	static int[] dx = {0, 0, -1, 1}; // �����¿�
	static int[] dy = {1, -1, 0, 0};
	static Queue<Node> q;
	static int w; // �ǹ��� �ʺ�
	static int h; // �ǹ��� ����
	
	public static class Node{
		private int x;
		private int y;
		private boolean fire; // ���̸� true, ����̸� false
		private int count;
		
		Node(){ // ������ �Լ� (�ʱ�ȭ)
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
			q = new LinkedList<>(); // ť�� �ʱ�ȭ ������� �ٸ� �׽�Ʈ ���̽����� ���� �ٲ� 
			                        //(�Ź� �������� ������ ���� �׽�Ʈ���̽����� ����� ť�� �״�� ��������)
			String[] st = br.readLine().split(" ");
			w = Integer.parseInt(st[0]); // �ǹ��� �ʺ�
			h = Integer.parseInt(st[1]); // �ǹ��� ����
			
			String[][] building = new String[h][w];
			Node person = new Node();
			
			for(int i=0; i<h; i++) {
				st = br.readLine().split("");
				for(int j=0; j<w; j++) {
					//System.out.println("�׽�Ʈ" + i +" -> " + j);
					building[i][j] = st[j];
					if(st[j].equals("@")) {
						person.setX(j);
						person.setY(i);
						person.setFire(false);
						person.setCount(0);
					} else if(st[j].equals("*")) {
						Node n = new Node(); // �⺻ ������(��ü ����)
						/* [ ���� ] 
						 * �� �κп� �������༭ �Ź� �ٸ� ��ü�� �̿��� q�� �־���� ���� �ٲ��� ��
						 * �� �κп� �������ִ°� �ƴ϶� ���� �������� ���,
						 * ��� ť�� �� �޸�(n)�� ����Ű�� �Ǽ� ������ ���� ���� ��� ť�� ���� ��
						 */
						n.setX(j);
						n.setY(i);
						n.setFire(true);
						n.setCount(0);
						q.add(n); // q�� �Ź� �ٸ� ��ü�� �־������
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
