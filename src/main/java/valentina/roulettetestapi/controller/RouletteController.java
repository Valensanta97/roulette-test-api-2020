package valentina.roulettetestapi.controller;

import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
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

}
