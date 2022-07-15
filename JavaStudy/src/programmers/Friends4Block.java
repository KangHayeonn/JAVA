// [1차] 프렌즈4블록 (프로그래머스 LEVEL2)

/** [ 로직 ]
 * 
 * 1. map을 만들어 전체를 검사한다.
 * 2. 1번 과정에서 2x2로 이루어진 4개가 동일한 블록을 찾는다.
 * 3. 2번의 갯수만큼 answer을 증가시킨다.
 * 4. 해당 영역을 가상 삭제 한다. (다른 문자열로 변환)
 * 5. 삭제된 영역을 기준으로 위에있는 블록들을 삭제된 블록의 위치로 아래방향으로 떨어뜨린다.
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
		
		// 게임 진행
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
				
				// 2x2 형태의 블록을 체크
				FourBlock(isChecked, i, j);
			}
		}
		
		for(int i = 0 ; i < m ; i++) {
			for(int j = 0 ; j < n ; j++) {
				if(isChecked[i][j]) {
					cnt += 1;
					map[i][j] = '-'; // 처음에 체크한 블록 초기화 안시켜줘서 오류 뜸 (이부분 추가)
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
