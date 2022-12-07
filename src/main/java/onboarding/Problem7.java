package onboarding;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Problem7 {
    private static final Map<String, Integer> userAndScore = new HashMap<>();
    private static final Set<String> friendsSet = new HashSet<>();

    public static List<String> solution(String user, List<List<String>> friends, List<String> visitors) {
        try {
            addFriendAndUser(friends, user);
            setFriendScore(friends, visitors);
            removeMyFriendsAndValueIsZero();

            List<String> answer = userAndScore.entrySet().stream()
                    .sorted(Map.Entry.comparingByKey())
                    .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                    .map(Map.Entry::getKey)
                    .limit(5)
                    .collect(Collectors.toList());

            return answer;
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("잘못된 값 입력");
        }
    }

    private static void addFriendAndUser(List<List<String>> friends, String user) {
        friends.stream().filter(friend -> isFriendsContainUser(friend, user)).forEach(friendsSet::addAll);
    }

    private static void setFriendScore(List<List<String>> friends, List<String> visitors) {
        friends.stream().filter(friend -> friendsSet.contains(friend.get(0)) || friendsSet.contains(friend.get(1)))
                .forEach(friend -> {
                    increaseFriendScore(friend.get(0), 10);
                    increaseFriendScore(friend.get(1), 10);
                });
        visitors.forEach(visitor -> increaseFriendScore(visitor, 1));
    }

    private static void increaseFriendScore(String user, int score) {
        userAndScore.put(user, userAndScore.getOrDefault(user, 0) + score);
    }

    /**
     * @param friend <p>친구 리스트 목록</p>
     * @param user   <p>추천받는 대상인 유저</p>
     * @return 친구 목록 리스트에서 user 유무에 따라 boolean 값을 리턴한다.
     */
    private static boolean isFriendsContainUser(List<String> friend, String user) {
        return friend.contains(user);
    }

    private static void removeMyFriendsAndValueIsZero() {
        for (String friend : friendsSet) {
            userAndScore.remove(friend);
        }
        for (String key : userAndScore.keySet()) {
            if (userAndScore.get(key) == 0) {
                userAndScore.remove(key);
            }
        }
    }
}
