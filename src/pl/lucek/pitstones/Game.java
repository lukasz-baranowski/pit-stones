package pl.lucek.pitstones;

public class Game {

	enum Player {
		FIRST, SECOND;
	}

	int[] firstPlayerPits = { 6, 6, 6, 6, 6, 6, 0 };
	int[] secondPlayerPits = { 6, 6, 6, 6, 6, 6, 0 };
	Player nextTurn = Player.FIRST;

	public void playerSow(Player player, int pitNumber) {
		int[] pits = getPits(player);
		int stones = pits[pitNumber];
		pits[pitNumber++] = 0;
		while (stones > 0) {
			pits[pitNumber] += 1;
			if (isLastStoneOnEmptyAndNotLastPit(pitNumber, pits, stones)) {
				pits[pitNumber] += captureStonesFromOpponentsPit(player, pitNumber);
			}
			stones--;
			pitNumber = getNextPitIndex(pitNumber);
		}
		nextTurn = nextTurn(player, pitNumber);
	}

	private int captureStonesFromOpponentsPit(Player player, int pitNumber) {
		int[] opponentPits = getOpponentPits(player);
		int oppositePitIndex = 5 - pitNumber;
		int stones = opponentPits[oppositePitIndex];
		opponentPits[oppositePitIndex] = 0;
		return stones;
	}

	private boolean isLastStoneOnEmptyAndNotLastPit(int pitNumber, int[] pits,
			int stones) {
		return stones == 1 && pits[pitNumber] == 1 && pitNumber != 6;
	}

	private int getNextPitIndex(int pitNumber) {
		if (pitNumber == 6) {
			return 0;
		} else {
			return pitNumber + 1;
		}
	}

	private Player nextTurn(Player current, int nextStonePitNo) {
		if (nextStonePitNo == 0) {
			return current;
		}
		if (Player.FIRST.equals(current)) {
			return Player.SECOND;
		} else {
			return Player.FIRST;
		}
	}

	private int[] getPits(Player player) {
		if (Player.FIRST.equals(player)) {
			return firstPlayerPits;
		} else {
			return secondPlayerPits;
		}
	}

	private int[] getOpponentPits(Player player) {
		if (Player.FIRST.equals(player)) {
			return secondPlayerPits;
		} else {
			return firstPlayerPits;
		}
	}

}
