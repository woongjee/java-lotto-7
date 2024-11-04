package lotto;

import java.util.Arrays;
import java.util.List;

public class Winning {
    private final int[] winningNumbers;
    private final int bonusNumber;

    public Winning(int[] winningNumbers, int bonusNumber) {
        validateWinningNumbers(winningNumbers);
        validateBonus(winningNumbers, bonusNumber);
        this.winningNumbers = winningNumbers;
        this.bonusNumber = bonusNumber;
    }

    private void validateWinningNumbers(int[] numbers) {
        if (numbers.length != 6) {
            throw new IllegalArgumentException("[ERROR] 당첨 번호는 6개여야 합니다.");
        }
        for(int number : numbers) {
            if(number<1||number>45) {
                throw new IllegalArgumentException("[ERROR] 당첨 번호는 1~45 사이의 숫자입니다.");
            }
        }
        long distinctCount = Arrays.stream(numbers).distinct().count();
        if(distinctCount!=numbers.length) {
            throw new IllegalArgumentException("[ERROR] 당첨 번호는 중복될 수 없습니다.");
        }
    }

    private void validateBonus(int[] winningNumbers, int bonusNumber){
        for(int i : winningNumbers) {
            if (i == bonusNumber) {
                throw new IllegalArgumentException("[ERROR] 당첨 번호는 6개여야 합니다.");
            }
        }
    }

    public int checkLotto(Lotto lotto) {
        int matchCount = countMatches(lotto.getNumbers(), winningNumbers);
        boolean hasBonus = lotto.getNumbers().contains(bonusNumber);
        return LottoRank.valueOf(matchCount, hasBonus).ordinal();
    }

    private int countMatches(List<Integer> userNumbers, int[] winningNumbers) {
        int count = 0;
        for(int number : userNumbers) {
            if(contains(winningNumbers, number)) {
                count++;
            }
        }
        return count;
    }

    private boolean contains(int[] numbers, int target) {
        for (int number : numbers) {
            if (number == target) return true;
        }
        return false;
    }
}
