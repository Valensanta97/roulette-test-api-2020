package valentina.roulettetestapi.controller;

import java.util.ArrayList;
import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import valentina.roulettetestapi.domain.Bet;
import valentina.roulettetestapi.domain.Roulette;
import valentina.roulettetestapi.repository.RouletteRepository;

@RestController
public class RouletteController {

	private RouletteRepository rouletteRepository;

	public RouletteController(RouletteRepository rouletteRepository) {
		this.rouletteRepository = rouletteRepository;
	}

	@PostMapping("/roulettes")
	public String createRoulette(@RequestBody Roulette roulette) {
		
		return rouletteRepository.createRoulette(roulette);
	}

	@GetMapping("/roulettes")
	public Map<String, Roulette> findAllRoulettes() {
		
		return rouletteRepository.findAllRoulettes();
	}

	@PostMapping("/roulettes/{idRoulette}/open")
	public int openRoulette(@PathVariable String idRoulette) {
		
		return rouletteRepository.openRoulette(idRoulette);
	}

	@PostMapping("/roulettes/{idRoulette}/closed")
	public ArrayList<Bet> closedRoulette(@PathVariable String idRoulette) {
		
		return rouletteRepository.closedRoulette(idRoulette);
	}
}
