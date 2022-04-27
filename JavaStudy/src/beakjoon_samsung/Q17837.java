// ���ο� ���� 2 (���� ���2)

package beakjoon_samsung;

import java.io.*;
import java.util.*;

public class Q17837 {
	static int N, K;
	static int[][] map;
	static int[][] keyMap;
	static Map<Integer, Type> horse;
	static Map<Integer, Stack<Integer>> turnHorse; // turn �߿� ��ǥ�� ���̴� ��
	static int[] dr = {0, -1, 0, 1}; // �� �� �� ��
	static int[] dc = {1, 0, -1, 0};
	public static class Type {
		int r, c;
		int direction;
		public Type (int r, int c, int direction) {
			this.r = r;
			this.c = c;
			this.direction = direction;
		}
	}
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		keyMap = new int[N][N];
		int idx = 0;
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				keyMap[i][j] = idx++;
			}
		}
		
		horse = new HashMap<>();
		turnHorse = new HashMap<>();
		for(int i=1; i<=K; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken()) -1;
			int c = Integer.parseInt(st.nextToken()) -1;
			int direction = Integer.parseInt(st.nextToken()) -1;
			
			if(direction == 1) direction = 2;
			else if(direction == 2) direction = 1;
			
			horse.put(i, new Type(r, c, direction));
			int key = keyMap[r][c];
			if(!turnHorse.containsKey(key)) {
				Stack<Integer> s = new Stack<>();
				s.push(i);
				turnHorse.put(key, s);
			} else {
				Stack<Integer> s = turnHorse.get(key);
				s.push(i);
				turnHorse.put(key, s);
			}
		}
		
		int turn = 0;
		while(++turn <= 1000) {
			boolean check = false;
			
			for(int i=1; i<=K; i++) { // 1�������� K�� ������ ������� �̵�
				// ���� ���ǿ� ���� �̵�
				if(moveHorse(i, 0) >= 4) {
					check = true;
					break;
				};
				// ���� ���� 4�� �̻� ���̴� ���� ���� ���� (check =true;)
			}
			if(check) {
				break;
			}
		}
		System.out.println(turn > 1000 ? -1 : turn);
	}
	public static int moveHorse(int idx, int depth) {
		// �̵��� �ε���
		Type t = horse.get(idx);
		int nr = t.r + dr[t.direction];
		int nc = t.c + dc[t.direction];
		
		// ���� ��� ���
		if(nr < 0 || nc < 0 || nr >= N || nc >=N) {
			if(depth == 1) return 0;
			int direction = (t.direction + 2) % 4;
			horse.put(idx, new Type(t.r, t.c, direction));
			return moveHorse(idx, depth+1);
		}
		// �Ķ����� ���
		if(map[nr][nc] == 2 && depth == 0) {
			int direction = (t.direction + 2) % 4;
			horse.put(idx, new Type(t.r, t.c, direction));
			return moveHorse(idx, depth+1);
		}
		if(map[nr][nc] == 2 && depth == 1) {
			return 0;
		}
		// ����� ���
		if(map[nr][nc] == 0) {
			Stack<Integer> tempS = new Stack<>();
			int key = keyMap[t.r][t.c];
			Stack<Integer> s = turnHorse.get(key);
			
			while(!s.isEmpty()) {
				int n = s.pop();
				if(n == idx) {
					tempS.push(n);
					break;
				} else {
					horse.put(n, new Type(nr, nc, horse.get(n).direction));
					tempS.push(n);
				}
			}
			while(!tempS.isEmpty()) {
				int i = tempS.pop();
				int newKey = keyMap[nr][nc];
				if(!turnHorse.containsKey(newKey)) {
					s = new Stack<>();
					s.push(i);
					turnHorse.put(newKey, s);
				} else {
					s = turnHorse.get(newKey);
					s.push(i);
					turnHorse.put(newKey, s);
				}
			}
			horse.put(idx, new Type(nr, nc, t.direction));
		}
		// �������� ���
		if(map[nr][nc] == 1) {
			Queue<Integer> tempQ = new LinkedList<>();
			int key = keyMap[t.r][t.c];
			Stack<Integer> s = turnHorse.get(key);
			
			while(!s.isEmpty()) {
				int n = s.pop();
				if(n == idx) {
					tempQ.offer(n);
					break;
				} else {
					horse.put(n, new Type(nr, nc, horse.get(n).direction));
					tempQ.offer(n);
				}
			}
			while(!tempQ.isEmpty()) {
				int i = tempQ.poll();
				int newKey = keyMap[nr][nc];
				if(!turnHorse.containsKey(newKey)) {
					s = new Stack<>();
					s.push(i);
					turnHorse.put(newKey, s);
				} else {
					s = turnHorse.get(newKey);
					s.push(i);
					turnHorse.put(newKey, s);
				}
			}
			horse.put(idx, new Type(nr, nc, t.direction));
		}
		
		int outputKey = keyMap[nr][nc];
		
		if(turnHorse.get(outputKey).isEmpty()) return 0;
		else return turnHorse.get(outputKey).size();
	}
}
