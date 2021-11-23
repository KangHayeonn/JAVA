// �� �μ��� �̵��ϱ�

/* [ �˰��� ]
 * 
 * 1. ���� ��ü ���� �޾� ������ �迭�� ����
 * 2. ó�� (0,0) ĭ���� �����ؼ� �����¿�� �̵� (���� ��ġ���� 4���� �������� ���� �ݺ���) - ���� ��ġ & distance & ���� �μ� Ƚ���� �߰�.
 * 3. ���� ���� ����� ��� continue
 * 4. ���� �̹� �帱�� ����ߴµ� �� ���� ��� continue
 * 5. 3���� 4���� ��� �ƴ� ��� �ΰ��� ���̽��� ������ �̵�
 * 6. (1) �̵��� ��ǥ�� 0�� ��� : �̵��Ÿ� ����, �帱Ƚ�� �״��
 * 7. (2) �̵��� ��ǥ�� 1�� ��� : �̵��Ÿ� ����, �帱Ƚ�� ����
 * 8. ���� �̵��� ��ǥ�� (N-1,M-1) �� �� ��� �׶��� �̵��Ÿ� return
 * 9. ��� ��찡 ������ ��� �ش� �̵��Ÿ��� ���, �̵��Ÿ��� 0�̸� -1�� ��� 
 * 10. N�� M�� ��� 1�� ���� �ٷ� 0�� ���
 * 
 */
package baekjoon;

/*
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Q2206 {
	static int[] dx = {0, 0, -1, 1}; // �����¿� 
	static int[] dy = {1, -1, 0, 0};
	
	public static class type {
		private int x;
		private int y;
		private int distance;
		private int drill;
		
		public type(int x, int y, int distance, int drill) {
			this.x = x;
			this.y = y;
			this.distance = distance;
			this.drill = drill;
		}
	}
	public static void main(String args[]) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[][] map = new int[N][M];
		//int[] check = new int[N*M];
		int[][] check = new int[N][M];
		
		for(int i=0; i<N; i++) {
			String str = br.readLine();
			for(int j=0; j<M; j++) {
				map[i][j] = str.charAt(j)-'0';
				//check[M*(i%M) + j] = Integer.MAX_VALUE;
				check[i][j] = Integer.MAX_VALUE;
			}
		}
		System.out.println(Arrays.toString(check));
		BFS(map, check, N, M);
	}
	
	public static void BFS(int[][] map, int[][] check, int N, int M) {
		Queue<type> move = new LinkedList<>(); // �̵�
		move.add(new type(0, 0, 1, 0));
		check[0][0] = 0;
		
		while(!move.isEmpty()) {
			type temp = move.poll();
			
			if(temp.x == M-1 && temp.y == N-1) {
				System.out.print(temp.distance);
				System.out.println("������?");
				return;
			}
			
			for(int i=0; i<4; i++) {
				int now_x = temp.x + dx[i];
				int now_y = temp.y + dy[i];
				
				if(now_x < 0 || now_y < 0 || now_x > M-1 || now_y > N-1 ) continue;
				if(check[now_y][now_x] <= temp.drill) continue;
				//if(check[now_x*(now_x%M) + now_y] <= temp.drill) continue; // �̹� �ѹ��� ���� ���̱� ������ pass
				System.out.println(Arrays.toString(check));
				if(map[now_y][now_x] == 0) {
					move.add(new type(now_x, now_y, temp.distance+1, temp.drill));
					//check[now_x*(now_x%M) + now_y] = temp.drill;
					check[now_y][now_x] = temp.drill;
				}
				else {
					if(temp.drill == 0) {
						move.add(new type(now_x, now_y, temp.distance+1, temp.drill+1));
						//check[now_x*(now_x%M) + now_y] = temp.drill+1;
						check[now_y][now_x] = temp.drill+1;
					}
				}
			}
		}
		if(move.isEmpty()) {
			System.out.print(-1);
			return;
		}
	}
}*/


import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
 
public class Q2206 {
 
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static int N, M;
    private static int[][] map;
    private static int[][] visited;
    private static int[] dx = {0, 0, -1, 1};
    private static int[] dy = {-1, 1, 0, 0};
 
    static class Point {
        int x, y, distance;
        int drill; // ���� Ƚ��
 
        public Point(int x, int y, int distance, int drill) {
            this.x = x;
            this.y = y;
            this.distance = distance;
            this.drill = drill;
        }
    }
 
    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
 
        map = new int[N][M];
        visited = new int[N][M];
 
        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(String.valueOf(str.charAt(j)));
                visited[i][j] = Integer.MAX_VALUE;
            }
        }
 
        int ans = bfs(0, 0);
        bw.write(ans + "\n");
 
        bw.flush();
        bw.close();
        br.close();
    }
 
    private static int bfs(int x, int y) {
        Queue<Point> q = new LinkedList<>();
        q.add(new Point(x, y, 1, 0)); 
        visited[y][x] = 0; 
 
        while (!q.isEmpty()) {
            Point point = q.poll();
 
            if (point.x == M - 1 && point.y == N - 1)
                return point.distance;
 
            for (int i = 0; i < 4; i++) {
                int nx = point.x + dx[i];
                int ny = point.y + dy[i];
 
                if (nx >= 0 && nx < M && ny >= 0 && ny < N) {
                    if (visited[ny][nx] > point.drill) {
                        if (map[ny][nx] == 0) { // ���� �ƴ� ��
                            q.add(new Point(nx, ny, point.distance + 1, point.drill));
                            visited[ny][nx] = point.drill;
                        } else { // ���� ��
                            if (point.drill == 0) { // ���ݱ��� ���� �μ� Ƚ���� 0�̶�� 
                                q.add(new Point(nx, ny, point.distance + 1, point.drill + 1));
                                visited[ny][nx] = point.drill + 1;
                            }
                        }
                    }
                }
            }
        }
 
        return -1;
    }
}