// í‚¤íŒ¨ë“œ ëˆ„ë¥´ê¸° (í”„ë¡œê·¸ë˜ë¨¸ìŠ¤ LEVEL1)

// ver1
<<<<<<< HEAD
// (1) ÀÌÂ÷¿ø ¹è¿­À» ÀÌ¿ëÇÑ keypad ÁÂÇ¥°ª ¼³Á¤
// (2) ÇöÀç ¿Ş¼Õ°ú ¿À¸¥¼ÕÀÇ À§Ä¡¸¦ ÁÂÇ¥°ªÀ¸·Î ¼³Á¤
// (3) ÀÌµ¿ÇÒ numbersÀÇ ÁÂÇ¥¶û ¿Ş¼Õ ¿À¸¥¼ÕÀÇ ÁÂÇ¥¸¦ ºñ±³ÇÏ¸ç °è»ê
// (4) ÀÌµ¿¿¡ µû¶ó L, R ºÎºĞÀ» answer¿¡ Ãß°¡ÇØÁÖ°í Ãâ·Â
=======
// (1) ì´ì°¨ì› ë°°ì—´ì„ ì´ìš©í•œ keypad ì¢Œí‘œê°’ ì„¤ì •
// (2) í˜„ì¬ ì™¼ì†ê³¼ ì˜¤ë¥¸ì†ì˜ ìœ„ì¹˜ë¥¼ ì¢Œí‘œê°’ìœ¼ë¡œ ì„¤ì •
// (3) ì´ë™í•  numbersì˜ ì¢Œí‘œë‘ ì™¼ì† ì˜¤ë¥¸ì†ì˜ ì¢Œí‘œë¥¼ ë¹„êµí•˜ë©° ê³„ì‚°
// (4) ì´ë™ì— ë”°ë¼ L, R ë¶€ë¶„ì„ answerì— ì¶”ê°€í•´ì£¼ê³  ì¶œë ¥
>>>>>>> 42686cde37ae4224d8b9d908f88102b22a76f042

package codingtest_9th_3rd;

public class Q3 {
	static public void main(String args[]) {
		int[] numbers = {1, 3, 4, 5, 8, 2, 1, 4, 5, 9, 5};
		Q3 s = new Q3();
		System.out.println(s.solution(numbers, "right"));
	}
	public String solution(int[] numbers, String hand) {
		String answer = "";
		int[][] keypad = {{1,0},{0,3},{1,3},{2,3},{0,2},{1,2},{2,2},{0,1},{1,1},{2,1}}; // 0 1 2 3 4 5 6 7 8 9
		int[] leftHand = {0,0}; // ì™¼ì†ì˜ ìœ„ì¹˜ (ì‹œì‘ì§€ì : *)
		int[] rightHand = {2,0};

		for(int i : numbers) {
			if(i==1 || i==4 || i==7) {
				answer += "L";
				leftHand[0] = keypad[i][0];
				leftHand[1] = keypad[i][1];
			}
			else if(i==3 || i==6 || i==9) {
				answer += "R";
				rightHand[0] = keypad[i][0];
				rightHand[1] = keypad[i][1];
			}
			else {
				int leftDistance = Math.abs(keypad[i][0]-leftHand[0]) + Math.abs(keypad[i][1]-leftHand[1]);
				int rightDistance = Math.abs(keypad[i][0]-rightHand[0]) + Math.abs(keypad[i][1]-rightHand[1]);
				
				if(leftDistance < rightDistance) {
					answer += "L";
					leftHand[0] = keypad[i][0];
					leftHand[1] = keypad[i][1];				
				}
				else if(leftDistance > rightDistance) {
					answer += "R";
					rightHand[0] = keypad[i][0];
					rightHand[1] = keypad[i][1];
				}
				else {
					if(hand.equals("left")) {
						answer += "L";
						leftHand[0] = keypad[i][0];
						leftHand[1] = keypad[i][1];
					}
					else {
						answer += "R";
						rightHand[0] = keypad[i][0];
						rightHand[1] = keypad[i][1];
					}
				}
			}
		}
		return answer;
	}
}
