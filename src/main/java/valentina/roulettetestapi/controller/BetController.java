package valentina.roulettetestapi.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import valentina.roulettetestapi.domain.Bet;
import valentina.roulettetestapi.repository.BetRepository;

@RestController
public class BetController {

	private BetRepository betRepository;

	public BetController(BetRepository betRepository) {
		this.betRepository = betRepository;
	}

	@PostMapping("/bet/{idRoulette}")
	public String doBet(@RequestBody Bet bet, @PathVariable String idRoulette, @RequestHeader("id-user") String idUser) {
		bet.setIdUser(idUser);
		bet.setIdRoulette(idRoulette);
		
		return betRepository.doBet(bet);
	}
}
