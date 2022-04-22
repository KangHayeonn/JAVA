// ��ŸƮ�� ��ũ (���� �ǹ�2)

// �������� N���� N/2�� �̰� ���̽��� ���� �ΰ��� ���̸� ����
// �ΰ��� ���� �� ������ ���� ���� ���� ����

package beakjoon_samsung;

import java.io.*;
import java.util.*;

public class Q14889 {
	static int min = Integer.MAX_VALUE;
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[][] map = new int[N][N];
		StringTokenizer st = new StringTokenizer("");
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		boolean[] isVisited = new boolean[N];
		combi(map, isVisited, 0, N, N/2);
		
		System.out.println(min);
	}
	public static void combi(int[][] map, boolean[] isVisited, int start, int depth, int n) {
		System.out.println(Arrays.toString(isVisited));
		if(depth==n) {
			calc(map, isVisited);
			return;
		}
		for(int i=start; i<isVisited.length; i++) {
			if(!isVisited[i]) { // �̺κ� �ʿ���� (i+1�� �Ѱ��༭)
				isVisited[i] = true;
				combi(map, isVisited, i+1, depth-1, n);
				isVisited[i] = false;
			}
		}
	}
	public static void calc(int[][] map, boolean[] isVisited) {
		int start=0, link=0;
		
		for(int i=0; i<map.length-1; i++) {
			for(int j=i+1; j<map.length; j++) {
				if(isVisited[i]==true && isVisited[j]==true) {
					start += map[i][j];
					start += map[j][i];
				}
				else if(isVisited[i]==false && isVisited[j]==false) {
					link += map[i][j];
					link += map[j][i];
				}
			}
		}
		
		min = Math.min(min, Math.abs(start-link));
	}
}
