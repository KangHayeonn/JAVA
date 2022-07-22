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
		
		// 게임 진행
		while(true) {
			if(!checkBlock(m, n)) break; // 더 이상 깰 블록이 없으면 break
			deleteBlock(m, n);
		}
		return answer;
	}
	public static boolean checkBlock(int m, int n) {
		boolean[][] isChecked = new boolean[m][n];
		int nr, nc, count=0;
		
		for(int i=0; i<m-1; i++) {
			for(int j=0; j<n-1; j++) {
				if(map[i][j] == '-') continue; // 삭제된 블록일 경우 continue
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
		for(int i=m-1; i>=0; i--) {   // 행
			for(int j=0; j<n; j++) {  // 열
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
