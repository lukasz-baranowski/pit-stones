package pl.lucek.pitstones.web;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pl.lucek.pitstones.game.Game;
import pl.lucek.pitstones.game.Game.Player;

@Controller
public class GameController {
	
	private Game game = new Game();
	
	@RequestMapping(value="/game", method=RequestMethod.GET)
	public String game(Model model) {
        fillInData(model);
        return "game";
	}
	
	@RequestMapping(value="/game", method=RequestMethod.POST, params="submit")
	public String playerSow(@ModelAttribute PlayerSow playerSow, Model model) {
		game.playerSow(game.getNextTurn(), playerSow.getPitNumber() - 1);
        fillInData(model);
        return "game";
	}
	
	@RequestMapping(value="/game", method=RequestMethod.POST, params="newGame")
	public String newGame(@ModelAttribute PlayerSow playerSow, Model model) {
		game = new Game();
        fillInData(model);
        return "game";
	}

	private void fillInData(Model model) {
		PitValue [] p1 = toPitValueArray(game.getFirstPlayerPits());
        ArrayUtils.reverse(p1);
        model.addAttribute("firstPlayerPits", p1);
        model.addAttribute("secondPlayerPits", toPitValueArray(game.getSecondPlayerPits()));
        model.addAttribute("nextTurn", playerLabel(game.getNextTurn()));
        model.addAttribute("playerSow", new PlayerSow());
	}

	private PitValue [] toPitValueArray(int [] pits) {
		int len = pits.length;
		PitValue [] pitValues = new PitValue[len];
		for (int i = 0; i < len; i++)  {
			pitValues[i] = new PitValue(i+1, pits[i]);
		}
		return pitValues;		
	}
	
	private String playerLabel(Player player) {
		if (Player.FIRST.equals(player)) {
			return "Player 1";
		} else {
			return "Player 2";
		}
	}

}
