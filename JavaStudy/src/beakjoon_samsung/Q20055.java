// 컨베이어 벨트 위의 로봇 (백준 골드5)

package beakjoon_samsung;

import java.io.*;
import java.util.*;

public class Q20055 {
	static int N, K;
	static Map<Integer, Type> boxMap;  // 박스 번호 : 내구도
	static Map<Integer, Integer> beltMap; // 위치(인덱스) : 박스번호
	public static class Type {
		int val;
		boolean isRobot; // 로봇이 있으면 true, 없으면 false
		public Type(int val, boolean isRobot) {
			this.val = val;
			this.isRobot = isRobot;
		}
	}
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		boxMap = new HashMap<>();
		beltMap = new HashMap<>();
		
		st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N*2; i++) {
			boxMap.put(i, new Type(Integer.parseInt(st.nextToken()), false)); // 박스별로 내구도 저장
			beltMap.put(i, i);
		}
		
		int turn = 1;
		while(true) {
			moveBelt();
			moveRobot();
			upRobot();
			if(isEnd()) break;
			turn += 1;
		}
		System.out.println(turn);
	}
	public static void moveBelt() {
		int boxNum = beltMap.get(N*2);
		
		for(int i=N*2; i>1; i--) {
			beltMap.put(i, beltMap.get(i-1)); // 한칸씩 이동
			
			if(i==N) { // 내리는 위치
				int tmpIdx = beltMap.get(i-1);
				boxMap.put(tmpIdx, new Type(boxMap.get(tmpIdx).val, false)); // (에러처리) : 벨트가 로봇과 함께 한칸씩 회전할 때도 로봇이 내리는 위치에 있을 경우는 삭제해줘야함
			}
		}
		beltMap.put(1, boxNum);
	}
	public static void moveRobot() {
		for(int i=N-1; i>=1; i--) {
			int beforeIdx = beltMap.get(i); // 박스 이동전 인덱스
			int afterIdx = beltMap.get(i+1); // 박스 이동후 인덱스
			
			Type t = boxMap.get(afterIdx);
			Type t2 = boxMap.get(beforeIdx);
			
			if(!t2.isRobot) continue;
			
			if(t.val >=1 && !t.isRobot) {
				boxMap.put(beforeIdx, new Type(boxMap.get(beforeIdx).val, false));
				
				if(i+1 == N) {
					boxMap.put(afterIdx, new Type(boxMap.get(afterIdx).val-1, false));
				} else {
					boxMap.put(afterIdx, new Type(boxMap.get(afterIdx).val-1, true));
				}
			}
		}
	}
	public static void upRobot() {
		int idx = beltMap.get(1); // 올리는 위치
		int value = boxMap.get(idx).val;
		
		if(value > 0) {
			boxMap.put(idx, new Type(value-1, true)); // 올리는 위치에 있는 박스에 로봇을 올림
		}
	}
	public static boolean isEnd() {
		int cnt = 0;
		for(Integer key: boxMap.keySet()) {
			Type t = boxMap.get(key);
			
			if(t.val == 0) cnt += 1;
			if(cnt >= K) return true;
		}
		return false;
	}
}
