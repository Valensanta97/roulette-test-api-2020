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

	private static final String KEY = "Bet";
	private RedisTemplate<String, Bet> betTemplate;
	private HashOperations hashOperations;

	public BetRepository(RedisTemplate<String, Bet> betTemplate) {
		this.betTemplate = betTemplate;
	}

	@PostConstruct
	private void init() {
		hashOperations = betTemplate.opsForHash();
	}
	
	public String bet(Bet bet) {
		String id = UUID.randomUUID().toString();
		hashOperations.put(KEY, id, bet);
		
		return id;
	}
}
