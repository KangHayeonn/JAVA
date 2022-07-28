// ��ô�ȸ (���α׷��ӽ� LEVEL2)
package programmers;

import java.util.*;

public class ArcheryCompetition {
	static int[] lionArr, ansArr;
	static int max = Integer.MIN_VALUE;
	public static void main(String args[]) {
		ArcheryCompetition a = new ArcheryCompetition();
		int n = 10;
		int[] info = {0,0,0,0,0,0,0,0,3,4,3};
		
		System.out.println(Arrays.toString(a.solution(n, info)));
	}
	public int[] solution(int n, int[] info) {
		lionArr = new int[11];
		ansArr = new int[11];
		dfs(1, n, info);
		
		if(!check(ansArr)) return new int[]{-1}; 
		return ansArr;
	}
	public void dfs(int count, int n, int[] info) {
		if(count == n+1) {
			int apeach=0, lion=0;
			for(int i=0; i<11; i++) {
				if(info[i] == 0 && lionArr[i] ==0) continue;
				if(info[i] >= lionArr[i]) apeach += 10-i;
				else lion += 10-i;
			}
			if(apeach < lion) {
				if(lion - apeach >= max) // ���� ���� �����ؾ� �� ���� ���� ��� ���ŵ�
                {
					max = lion - apeach;
					for(int j=0; j<11; j++) {
						ansArr[j] = lionArr[j];
					}
                }
			}
			return;
		}
		for(int i=0; i<11; i++) {
			if(lionArr[i] > info[i]) continue;
			lionArr[i]+=1;
			dfs(count+1, n, info);
			lionArr[i]-=1;
		}
	}
	public boolean check(int[] arr) {
		for(int i=0; i<arr.length; i++) {
			if(arr[i] != 0) return true;
		}
		return false;
	}
}
