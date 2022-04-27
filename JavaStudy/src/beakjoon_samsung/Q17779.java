// 게리맨더링 2

package beakjoon_samsung;

import java.io.*;
import java.util.*;

public class Q17779 {
	static int N;
	static int[][] map;
	static int[][] district;
	static int answer = Integer.MAX_VALUE;
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer("");
		
		map = new int[N][N];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 기준점 및 경계의 길이 정하기
		setNode();
		System.out.println(answer);
	}
	public static void setNode() {
		for(int x=0; x<N; x++) {
			for(int y=0; y<N; y++) {
				for(int d1=1; d1<N; d1++) {
					for(int d2=1; d2<N; d2++) {
						if (x + d1 + d2 >= N) continue;
                        if (y - d1 < 0 || y + d2 >= N) continue;
                        setBoundary(x, y, d1, d2); // 경계선 구하기
					}
				}
			}
		}
	}
	public static void setBoundary(int x, int y, int d1, int d2) {
		district = new int[N][N];
		
		// 1
		for(int i=0; i<=d1; i++) {
			district[x+i][y-i] = 5;
		}
		
		// 2
		for(int i=0; i<=d2; i++) {
			district[x+i][y+i] = 5;
		}
		
		// 3
		for(int i=0; i<=d2; i++) {
			district[x+d1+i][y-d1+i] = 5;
		}
		
		// 4
		for(int i=0; i<=d1; i++) {
			district[x+d2+i][y+d2-i] = 5;
		}
		
		setContain5(x, x+d1+d2, x, y, d1, d2); // 경계선과 경계선 안에 포함되어 있는 곳은 5번 선거구
				
	}
	public static void setContain5(int start, int end, int x, int y, int d1, int d2) {
		boolean check = false;
		for(int i=start+1; i<end; i++ ) {
			for(int j=0; j<N; j++) {
				if(district[i][j]==5 && !check) {
					check = true;
				} else if(district[i][j]==5 && check) {
					check = false;
				} else if(district[i][j]!=5 && check) {
					district[i][j] = 5;
				}
			}
		}
		
		setOthers(x, y, d1, d2); // 5번 선거구에 포함되지 않은 구역에 선거구 번호 표시하기
	}
	public static void setOthers(int x, int y, int d1, int d2) {
		// 1
		for(int r=0; r<x+d1; r++) {
			for(int c=0; c<=y; c++) {
				if(district[r][c] == 0) {
					district[r][c] = 1;
				}
			}
		}
		
		// 2
		for(int r=0; r<=x+d2; r++) {
			for(int c=y+1; c<N; c++) {
				if(district[r][c] == 0) {
					district[r][c] = 2;
				}
			}
		}
		
		// 3
		for(int r=x+d1; r<N; r++) {
			for(int c=0; c<y-d1+d2; c++) {
				if(district[r][c] == 0) {
					district[r][c] = 3;
				}
			}
		}
		
		// 4
		for(int r=x+d2+1; r<N; r++) {
			for(int c=y-d1+d2; c<N; c++) {
				if(district[r][c] == 0) {
					district[r][c] = 4;
				}
			}
		}
		calcPeople(district); // 선거구별 인구를 조사해 최대 값과 최소 값의 차를 저장
	}
	public static void calcPeople(int[][] district) {
		ArrayList<Integer> arr = new ArrayList<>();
		for(int i=1; i<6; i++) {
			int value = 0;
			for(int r=0; r<N; r++) {
				for(int c=0; c<N; c++) {
					if(district[r][c] == i) {
						value += map[r][c];
					}
				}
			}
			arr.add(value);
		}
		
		Collections.sort(arr);
		int sum = Math.abs(arr.get(0) - arr.get(arr.size()-1));
		answer = Math.min(answer, sum);
	}
}
