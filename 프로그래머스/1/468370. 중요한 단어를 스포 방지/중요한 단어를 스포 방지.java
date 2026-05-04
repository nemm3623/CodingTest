import java.util.*;

class Solution {
    public int solution(String message, int[][] spoiler_ranges) {
             int answer = 0;

        String[] tokens = message.split(" ");

        Map<String, List<int[]>> map = new HashMap<>();

        int start = 0;
        int end = 0;

        for (String token : tokens) {

            end = start + token.length() - 1;

            for(int[] range : spoiler_ranges) {
                int rangeStart = range[0];
                int rangeEnd = range[1];

                if(!(start > rangeEnd || end < rangeStart)) {
                    List<int[]> list = map.getOrDefault(token, new ArrayList<>());
                    list.add(new int[]{start, end});
                    map.putIfAbsent(token, list);
                    break;
                }
            }

            start += token.length() + 1;

        }

        start = 0;
        end = 0;
        for (String token : tokens) {

            end = start + token.length() - 1;

            List<int[]> list = map.getOrDefault(token, null);

            if(list == null) {
                start += token.length() + 1;
                continue;
            }

            boolean isMarking = false;

            for (int[] range : list) {
                int rangeStart = range[0];
                int rangeEnd = range[1];

                if(start == rangeStart && end == rangeEnd) {
                    isMarking = true;
                    break;
                }
            }
            if(!isMarking)
                map.remove(token);

            start += token.length() + 1;
        }

        answer = map.size();
        return answer;
    }
}
