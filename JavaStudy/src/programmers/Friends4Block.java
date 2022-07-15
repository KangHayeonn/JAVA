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
	public static void main(String args[]) {
		int m = 4; 
		int n = 5;
		String[] board = {
			"CCBDE", "AAADE", "AAABF", "CCBBF"
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
			if(!checkBlock(m, n)) break;
			deleteBlock(m, n);
		}
		
		return answer;
	}
	public static boolean checkBlock(int m, int n) {
		int cnt = 0;
		boolean[][] isChecked = new boolean[m][n];
		
		for(int i=0; i<m-1; i++) {
			for(int j=0; j<n-1; j++) {
				if(map[i][j] == '-') continue;
				
				// 2x2 ������ ����� üũ
				FourBlock(isChecked, i, j);
			}
		}
		
		for(int i = 0 ; i < m ; i++) {
			for(int j = 0 ; j < n ; j++) {
				if(isChecked[i][j]) {
					cnt += 1;
					map[i][j] = '-'; // ó���� üũ�� ��� �ʱ�ȭ �Ƚ����༭ ���� �� (�̺κ� �߰�)
				}
			}
		}
		
		answer += cnt;
		return (cnt > 0) ? true : false;
	}
	public static void FourBlock(boolean[][] isChecked, int i, int j) {
		char block = map[i][j];
		
		for(int r = i ; r < i + 2 ; r++) {
			for(int c = j ; c < j + 2 ; c++) {
				if(map[r][c] != block) return;
			}
		}
		
		for(int r = i ; r < i + 2 ; r++) {
			for(int c = j ; c < j + 2 ; c++) {
				isChecked[r][c] = true;
			}
		}
	}
	public static void deleteBlock(int m, int n) {
		for(int i = 0 ; i < n ; i++) {
			for(int j = m - 1 ; j >= 0 ; j--) {
				if(map[j][i] == '-') {
					for(int r = j - 1 ; r >= 0 ; r--) {
						if(map[r][i] != '-') {
							map[j][i] = map[r][i];
							map[r][i] = '-';
							break;
						}
					}
				}
			}
		}
	}
}
