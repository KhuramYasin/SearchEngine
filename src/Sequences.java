//editDistance is provided by this class.
public class Sequences {

	public static int editDistance(String word1, String word2) {
		int length1 = word1.length();
		int length2 = word2.length();
	 
		// length1+1, length2+1, since dp[length1][length2] is eventually returned.
		int[][] dp = new int[length1 + 1][length2 + 1];
        int i = 0; 
		while (i <= length1)
        {
			dp[i][0] = i;
            i++;
		}
        i = 0;
		while (i <= length2) 
        {
			dp[0][i] = i;
            i++;
		}
	 
		//repeat and verify the last character.
        i = 0; 
		while (i < length1) {
			char c1 = word1.charAt(i);
            int j = 0;
			while ( j < length2) 
            {
				char c2 = word2.charAt(j);
	 
				//if the last two characters are the same
				if (c1 != c2) {
					int replace = dp[i][j] + 1;
					int insert = dp[i][j + 1] + 1;
					int delete = dp[i + 1][j] + 1;
	 
					int min = replace > insert ? insert : replace;
					min = delete > min ? min : delete;
					dp[i + 1][j + 1] = min;
					
					//dpvalue should be updated for +1 length
				} else {
					//update the value of dp for +1 length
					dp[i + 1][j + 1] = dp[i][j];
				}
                j++;
			}
            i++;
		}
	 
		return dp[length1][length2];
	}
}
