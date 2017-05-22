public class Bowling {
	public int getBowlingScore(String bowlingCode) {
		if( bowlingCode.length == 0)
			return -1;
		
        	String[] temp = bowlingCode.split("\\|");

		int len=(temp.length==10?10:11);
		String[] cores = new String[len];
		
		if (temp.length == 12) {
			for (int i = 0; i < temp.length - 1; i++) {
				cores[i] = temp[i];
				if (temp[i].equals("")) {
					cores[i] = temp[i + 1];
				}
			}
		} else {
			for(int i=0; i<temp.length; i++){
				cores[i] = temp[i];
			}
		}
		return getScore(cores);
    	}
	public int getScore(String[] cores) {
		int sum = 0;
		int len = cores.length;
		int[][] scores = new int[len][2];

		for (int i = 0; i < len; i++) {
			for (int j = 0; j < 2; j++) {
				scores[i][j] = 0;
			}
		}

		for (int i = 0; i < len; i++) {
			if (cores[i].length() == 2) {
				scores[i][0] = turnChar(0, cores[i].charAt(0));
				scores[i][1] = turnChar(scores[i][0], cores[i].charAt(1));
			} else {
				scores[i][0] = turnChar(0, cores[i].charAt(0));
			}
		}
		
		int temp = (len == 10 ? len : len - 1);
		for (int i = 0; i < temp; i++) {
			if (cores[i].length() == 1) {
				if (scores[i + 1][0] == 10 && i + 2 < len)
					sum += 10 + scores[i + 1][0] + scores[i + 2][0];
				else if (i + 1 < len)
					sum += 10 + scores[i + 1][0] + scores[i + 1][1];
			} else { 
				if (scores[i][0] + scores[i][1] == 10 && i + 1 < len) {
					sum += 10 + scores[i + 1][0];
				} else {
					sum += scores[i][0] + scores[i][1];
				}
			}
		}
		return sum;
	}

	public int turnChar(int num, char ch) {
		int res = 0;
		switch (ch) {
		case 'X':
			res = 10;
			break;
		case '/':
			res = 10 - num;
			break;
		case '-':
			res = 0;
			break;
		default:
			res = Integer.parseInt(String.valueOf(ch));
		}
		return res;
	}
}
