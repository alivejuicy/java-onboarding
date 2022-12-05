package onboarding;

import java.util.*;

public class Problem7 {
    public static List<String> solution(String user, List<List<String>> friends, List<String> visitors) {
        //ist<String> answer = Collections.emptyList();
        //return answer;
        return visitors;
    }

    /**
     * @param friend
     * <p>친구 리스트 목록</p>
     * @param user
     * <p>추천받는 대상인 유저</p>
     * @return 친구 목록 리스트에서 user 유무에 따라 boolean 값을 리턴한다.
     */
    private static boolean isFriendsContainUser(List<String> friend, String user) {
        return friend.contains(user);
    }
}
