package valentina.roulettetestapi.repository;

import java.util.UUID;
import javax.annotation.PostConstruct;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import valentina.roulettetestapi.domain.Bet;
import valentina.roulettetestapi.domain.Roulette;

@Repository
public class BetRepository {

	public static final String KEY = "Bet";
	private RedisTemplate<String, Bet> betTemplate;
	private RedisTemplate<String, Roulette> rouletteTemplate;
	private HashOperations hashOperations;
	private HashOperations rouletteOperations;

	public BetRepository(RedisTemplate<String, Bet> betTemplate, RedisTemplate<String, Roulette> rouletteTemplate) {
		this.betTemplate = betTemplate;
		this.rouletteTemplate = rouletteTemplate;
	}

	@PostConstruct
	private void init() {
		hashOperations = betTemplate.opsForHash();
		rouletteOperations = this.rouletteTemplate.opsForHash();
	}

	public String doBet(Bet bet) {
		String id = UUID.randomUUID().toString();
		Roulette r = (Roulette) rouletteOperations.get(RouletteRepository.KEY, bet.getIdRoulette());

		if (this.validateBet(bet)) {
			if (r != null && r.getState() == Roulette.OPEN) {
				hashOperations.put(KEY, id, bet);
				return id;
			} else {
				return "Invalid Roulette";
			}
		} else {
			return "Invalid bet";
		}
	}

	public boolean validateBet(Bet bet) {

		if (bet.getNumber() != null && bet.getColor() != null) {
			return false;
		}
		if (bet.getColor() != "black" && bet.getColor() != "red"
				&& (bet.getNumber() != null && bet.getNumber() < 0 && bet.getNumber() > 36)) {
			return false;
		}
		if (bet.getAmount() <= 10000 && bet.getAmount() >= 1) {
			return true;
		}
		return false;
	}
}
