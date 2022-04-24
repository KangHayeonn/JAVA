// 주사위 굴리기 (백준 골드4)

package beakjoon_samsung;

import java.io.*;
import java.util.*;

public class Q14499 {
	static int[][] map; // 지도
	static HashMap<String, Integer> boxIndex; // 주사위 모양
	static int[] box = new int[7]; // 주사위에 입력되는 값
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 세로크기
		int M = Integer.parseInt(st.nextToken()); // 가로크기
		int r = Integer.parseInt(st.nextToken()); // r좌표
		int c = Integer.parseInt(st.nextToken()); // c좌표
		int K = Integer.parseInt(st.nextToken()); // 명령의 개수
		
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
		int[] dr = {0, 0, 0, -1, 1}; // 동서북남
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
			case 1 : { // 동쪽
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
			case 2 : { // 서쪽
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
			case 3 : { // 북쪽
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
			case 4 : { // 남쪽
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
			map[r][c] = box[boxIndex.get("bottom")]; // 주사위 바닥면에 쓰여있는 수 지도 칸에 복사
		} else {
			box[boxIndex.get("bottom")] = map[r][c];
			map[r][c] = 0;
		}
	}
}
