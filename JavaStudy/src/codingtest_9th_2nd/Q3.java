// 크레인 인형뽑기 게임 (프로그래머스 LEVEL1)

// 알고리즘
// (1) moves 에서 주어진 요소 중에서 해당 board의 인덱스를 비교
// (2) (1)번의 결과, 0이 아닌 값이 있으면 그 숫자를 새로운 배열에 저장
// (3) (1)번의 결과, 0이면 continue
// (4) 해당 배열을 모두 저장해준 뒤 비교함수로 이동
// (5) 배열의 요소를 돌면서 자신의 앞의 숫자가 자신과 동일할 경우 삭제해주고 다시 비교함수로 재귀를 돌린다.
// (6) 모든 원소 각 부분 앞에 동일한게 없으면 해당 재귀함수를 return
// (7) 삭제해줄때는 본인과 자신의 앞을 카운트 해줘야 하기 때문에 answer +2 해준다.
// (8) 최종적으로 삭제된 값인 answer을 출력

package codingtest_9th_2nd;

import java.util.ArrayList;

public class Q3 {
	static public void main(String args[]) {
		Q3 s = new Q3();
		int[][] board = {{0,0,0,0,0},
						{0,0,1,0,3},
						{0,2,5,0,1},
						{4,2,4,4,2},
						{3,5,1,3,1}};
		int[] moves = {1,5,3,5,1,2,1,4};
		System.out.println(s.solution(board, moves));
		return;
	}
	
	public int solution(int[][] board, int[] moves) {
		ArrayList<Integer> arr = new ArrayList<Integer>();
        int answer = 0;
        
        for(int i=0; i<5; i++) {
        	System.out.println(board[i][0]);
        }
        for(int i=0; i<moves.length; i++) {
        	System.out.println("무브의 값 : "+moves[i]);
        	switch(moves[i]) {
	        	case 1: 
	        		for(int j=0; j< board.length; j++) {
	        			if(board[j][0] != 0) {
	        				System.out.println("저장되는 값 : "+ board[j][0]);
	        				arr.add(board[j][0]);
	        				board[j][0] = 0;
	        				break;
	        			}
	        			else continue;
	        		}
	        		break;
	        	case 2:
	        		for(int j=0; j< board.length; j++) {
	        			if(board[j][1] != 0) {
	        				System.out.println("저장되는 값 : "+ board[j][1]);
	        				arr.add(board[j][1]);
	        				board[j][1] = 0;
	        				break;
	        			}
	        			else continue;
	        		}
	        		break;
	        	case 3:
	        		for(int j=0; j< board.length; j++) {
	        			if(board[j][2] != 0) {
	        				System.out.println("저장되는 값 : "+ board[j][2]);
	        				arr.add(board[j][2]);
	        				board[j][2] = 0;
	        				break;
	        			}
	        			else continue;
	        		}
	        		break;
	        	case 4:
	        		for(int j=0; j< board.length; j++) {
	        			if(board[j][3] != 0) {
	        				System.out.println("저장되는 값 : "+ board[j][3]);
	        				arr.add(board[j][3]);
	        				board[j][3] = 0;
	        				break;
	        			}
	        			else continue;
	        		}
	        		break;
	        	case 5:
	        		for(int j=0; j< board.length; j++) {
	        			if(board[j][4] != 0) {
	        				System.out.println("저장되는 값 : "+ board[j][4]);
	        				arr.add(board[j][4]);
	        				board[j][4] = 0;
	        				break;
	        			}
	        			else continue;
	        		}
	        		break;
        	}
        	for(Integer item : arr) {
    			System.out.print(item);
    		}
        	System.out.print("\n");
        	comparison(19960522);
        }
        return answer;
    }
	public void comparison(int i) {
		System.out.println(i);
	}
}
