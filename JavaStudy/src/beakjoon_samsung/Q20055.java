// �����̾� ��Ʈ ���� �κ� (���� ���5)

package beakjoon_samsung;

import java.io.*;
import java.util.*;

public class Q20055 {
	static int N, K;
	static Map<Integer, Type> boxMap;  // �ڽ� ��ȣ : ������
	static Map<Integer, Integer> beltMap; // ��ġ(�ε���) : �ڽ���ȣ
	public static class Type {
		int val;
		boolean isRobot; // �κ��� ������ true, ������ false
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
			boxMap.put(i, new Type(Integer.parseInt(st.nextToken()), false)); // �ڽ����� ������ ����
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
			beltMap.put(i, beltMap.get(i-1)); // ��ĭ�� �̵�
			
			if(i==N) { // ������ ��ġ
				int tmpIdx = beltMap.get(i-1);
				boxMap.put(tmpIdx, new Type(boxMap.get(tmpIdx).val, false)); // (����ó��) : ��Ʈ�� �κ��� �Բ� ��ĭ�� ȸ���� ���� �κ��� ������ ��ġ�� ���� ���� �����������
			}
		}
		beltMap.put(1, boxNum);
	}
	public static void moveRobot() {
		for(int i=N-1; i>=1; i--) {
			int beforeIdx = beltMap.get(i); // �ڽ� �̵��� �ε���
			int afterIdx = beltMap.get(i+1); // �ڽ� �̵��� �ε���
			
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
		int idx = beltMap.get(1); // �ø��� ��ġ
		int value = boxMap.get(idx).val;
		
		if(value > 0) {
			boxMap.put(idx, new Type(value-1, true)); // �ø��� ��ġ�� �ִ� �ڽ��� �κ��� �ø�
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
