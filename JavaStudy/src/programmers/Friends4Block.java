// [1��] ������4��� (���α׷��ӽ� LEVEL2)

/** [ ���� ]
 * 
 * 1. map�� ����� ��ü�� �˻��Ѵ�.
 * 2. 1�� �������� 2x2�� �̷���� 4���� ������ ����� ã�´�.
 * 3. 2���� ������ŭ answer�� ������Ų��.
 * 4. �ش� ������ ���� ���� �Ѵ�. (�ٸ� ���ڿ��� ��ȯ)
 * 5. ������ ������ �������� �����ִ� ��ϵ��� ������ ����� ��ġ�� �Ʒ��������� ����߸���.
 */

package programmers;

public class Friends4Block {
	static char[][] map;
	static int answer = 0;
	static int[][] d = {{0, 0}, {0, 1}, {1, 0}, {1, 1}};
	public static void main(String args[]) {
		int m = 6; 
		int n = 6;
		String[] board = {
			"TTTANT", "RRFACC", "RRRFCC", "TRRRAA", "TTMMMF", "TMMTTJ"
		};
		System.out.println(solution(m, n, board));
	}
	public static int solution(int m, int n, String[] board) {
		map = new char[m][n];
		for(int i=0; i<m; i++) {
			map[i] = board[i].toCharArray();
		}
		
		// ���� ����
		while(true) {
			if(!checkBlock(m, n)) break; // �� �̻� �� ����� ������ break
			deleteBlock(m, n);
		}
		return answer;
	}
	public static boolean checkBlock(int m, int n) {
		boolean[][] isChecked = new boolean[m][n];
		int nr, nc, count=0;
		
		for(int i=0; i<m-1; i++) {
			for(int j=0; j<n-1; j++) {
				if(map[i][j] == '-') continue; // ������ ����� ��� continue
				if(!fourBlock(i, j)) continue;
				else {
					for(int k=0; k<4; k++) {
						nr = i + d[k][0];
						nc = j + d[k][1];
						if(nr >= m || nc >= n) continue;
						if(!isChecked[nr][nc]) {
							count += 1;
							isChecked[nr][nc] = true;
						}
					}
				}
			}
		}
		
		for(int i=0; i<m; i++) {
			for(int j=0; j<n; j++) {
				if(isChecked[i][j]) map[i][j] = '-';
			}
		}
		
		answer += count;
		return (count > 0) ? true : false;
	}
	public static boolean fourBlock(int r, int c) {
		int check = map[r][c];
		
		for(int i=r; i<r+2; i++) {
			for(int j=c; j<c+2; j++) {
				if(map[i][j] != check) return false;
			}
		}
		return true;
	}
	public static void deleteBlock(int m, int n) {
		for(int i=m-1; i>=0; i--) {   // ��
			for(int j=0; j<n; j++) {  // ��
				if(map[i][j] == '-') {
					for(int r = i - 1 ; r >= 0 ; r--) {
						if(map[r][j] != '-') {
							map[i][j] = map[r][j];
							map[r][j] = '-';
							break;
						}
					}
				}
			}
		}
	}
}
