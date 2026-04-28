class Solution {
    public int solution(int[][] signals) {
         int answer = 0;
        int lcm = 1;

        for (int[] signal : signals) {
            int cycle = signal[0] + signal[1] + signal[2];
            lcm = lcm(lcm, cycle);
        }

        for(int i = 1; i < lcm; i++) {

            boolean isAllYellow = true;

            for (int[] signal : signals) {

                int green = signal[0];
                int yellow = signal[1];
                int red = signal[2];
                int cycle = green + yellow + red;

                int time = (i - 1) % cycle + 1;

                if( !(green < time && green + yellow >= time) ){
                    isAllYellow = false;
                    break;
                }
            }

            if(isAllYellow){
                answer = i;
                return answer;
            }
        }
        return -1;
    }

    static private int gcd(int a, int b){
        while (b != 0) {
            int temp = a % b;
            a = b;
            b = temp;
        }
        return a;
    }

    static private int lcm(int a, int b){
        return a * b / gcd(a, b);
    }
}
