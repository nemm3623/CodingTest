import java.util.Arrays;

class Solution {
    public int[] solution(int m, int n, int h, int w, int[][] drops) {
        
        int[][] matrix = new int[m][n];
        int[] answer = null;

        for (int[] row : matrix) {
            Arrays.fill(row, drops.length);
        }

        for (int i = 0; i < drops.length; i++) {
            int row = drops[i][0];
            int col = drops[i][1];
            if( matrix[row][col] == drops.length )
                matrix[row][col] = i;
        }

        int left = 0;
        int right = drops.length;

        while ( left <= right ){

            int mid = (left + right) / 2;

            int[] ans = sum(m, n, h, w, mid, matrix);


            if( ans != null ){
                answer = ans;
                left = mid + 1;
            } else {
                right = mid - 1;
            }

        }

        return answer;
    }
    
    static int[] sum(int m, int n, int h, int w, int mid, int[][] matrix) {

        int[][] sum = new int[m + 1][n + 1];

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                int drop = matrix[i - 1][j - 1] < mid ? 1 : 0;
                sum[i][j] = drop + sum[i][j - 1] + sum[i - 1][j] - sum[i - 1][j - 1];
            }
        }

        for (int i = h; i <= m; i++) {
            for (int j = w; j <= n; j++) {
                int count = sum[i][j] - sum[i - h][j] - sum[i][j - w] + sum[i - h][j - w];

                if (count == 0) {
                    return new int[]{i - h, j - w};
                }
            }
        }

        return null;
    }
}