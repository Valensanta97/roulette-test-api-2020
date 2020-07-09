package valentina.roulettetestapi.repository;

import java.util.Map;
import java.util.UUID;
import javax.annotation.PostConstruct;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import valentina.roulettetestapi.domain.Roulette;

@Repository
public class RouletteRepository {

	private static final String KEY = "Roulette";
	private RedisTemplate<String, Roulette> redisTemplate;
	private HashOperations hashOperations;

	public RouletteRepository(RedisTemplate<String, Roulette> redisTemplate) {
		this.redisTemplate = redisTemplate;
	}

	@PostConstruct
	private void init() {
		hashOperations = redisTemplate.opsForHash();
	}

	public String createRoulette(Roulette roulette) {
		String id = UUID.randomUUID().toString();
		hashOperations.put(KEY, id, roulette);

		return id;
	}

	public Map<String, Roulette> findAllRoulettes() {
		return hashOperations.entries(KEY);
	}

	public int openRoulette(String idRoulette) {
		Roulette r = (Roulette) hashOperations.get(KEY, idRoulette);
		r.setState(Roulette.OPEN);
		hashOperations.put(KEY, idRoulette, r);

		return r.getState();
	}

	public int closedRoulette(String idRoulette) {
		Roulette r = (Roulette) hashOperations.get(KEY, idRoulette);
		r.setState(Roulette.CLOSED);
		hashOperations.put(KEY, idRoulette, r);

		return r.getState();
	}
}
