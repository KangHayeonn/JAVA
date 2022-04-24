// �ֻ��� ������ (���� ���4)

package beakjoon_samsung;

import java.io.*;
import java.util.*;

public class Q14499 {
	static int[][] map; // ����
	static HashMap<String, Integer> boxIndex; // �ֻ��� ���
	static int[] box = new int[7]; // �ֻ����� �ԷµǴ� ��
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // ����ũ��
		int M = Integer.parseInt(st.nextToken()); // ����ũ��
		int r = Integer.parseInt(st.nextToken()); // r��ǥ
		int c = Integer.parseInt(st.nextToken()); // c��ǥ
		int K = Integer.parseInt(st.nextToken()); // ����� ����
		
		map = new int[N][M];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		boxIndex = new HashMap<>();
		boxIndex.put("top", 1);
		boxIndex.put("back", 2);
		boxIndex.put("right", 3);
		boxIndex.put("left", 4);
		boxIndex.put("front", 5);
		boxIndex.put("bottom", 6);
		
		st = new StringTokenizer(br.readLine());
		int[] dr = {0, 0, 0, -1, 1}; // �����ϳ�
		int[] dc = {0, 1, -1, 0, 0};
		ArrayList<Integer> resultArr = new ArrayList<>();
		for(int i=0; i<K; i++) {
			int command = Integer.parseInt(st.nextToken());
			int nr = r + dr[command];
			int nc = c + dc[command];
			
			if(nr < 0 || nc < 0 || nr >= N || nc >= M) continue;
			
			r = nr; c = nc;
			
			turnBox(command);
			valueMatch(r, c);
			resultArr.add(box[boxIndex.get("top")]);
		}
		
		for(Integer n : resultArr) {
			System.out.println(n);
		}
	}
	public static void turnBox(int command) {
		int top, back, right, left, front, bottom;
		switch(command) {
			case 1 : { // ����
				left = boxIndex.get("bottom");
				top = boxIndex.get("left");
				right = boxIndex.get("top");
				bottom = boxIndex.get("right");
				boxIndex.put("left", left);
				boxIndex.put("top", top);
				boxIndex.put("right", right);
				boxIndex.put("bottom", bottom);
				break;
			}
			case 2 : { // ����
				left = boxIndex.get("top");
				top = boxIndex.get("right");
				right = boxIndex.get("bottom");
				bottom = boxIndex.get("left");
				boxIndex.put("left", left);
				boxIndex.put("top", top);
				boxIndex.put("right", right);
				boxIndex.put("bottom", bottom);
				break;	
			}
			case 3 : { // ����
				front = boxIndex.get("bottom");
				top = boxIndex.get("front");
				back = boxIndex.get("top");
				bottom = boxIndex.get("back");
				boxIndex.put("front", front);
				boxIndex.put("top", top);
				boxIndex.put("back", back);
				boxIndex.put("bottom", bottom);
				break;
			}
			case 4 : { // ����
				front = boxIndex.get("top");
				top = boxIndex.get("back");
				back = boxIndex.get("bottom");
				bottom = boxIndex.get("front");
				boxIndex.put("front", front);
				boxIndex.put("top", top);
				boxIndex.put("back", back);
				boxIndex.put("bottom", bottom);
			}
			default : break;
		}
	}
	public static void valueMatch(int r, int c) {
		if(map[r][c] == 0) {
			map[r][c] = box[boxIndex.get("bottom")]; // �ֻ��� �ٴڸ鿡 �����ִ� �� ���� ĭ�� ����
		} else {
			box[boxIndex.get("bottom")] = map[r][c];
			map[r][c] = 0;
		}
	}
}
