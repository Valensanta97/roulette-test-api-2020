package valentina.roulettetestapi.repository;

import java.util.Map;
import java.util.UUID;
import javax.annotation.PostConstruct;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import valentina.roulettetestapi.domain.Bet;
import valentina.roulettetestapi.domain.Roulette;

@Repository
public class RouletteRepository implements RedisRepository {
    private static final String KEY = "Roulette";
	private RedisTemplate<String,Roulette> redisTemplate;
    private HashOperations hashOperations;
    
	public RouletteRepository(RedisTemplate<String, Roulette> redisTemplate) {
		this.redisTemplate = redisTemplate;
	}
	
	@PostConstruct
	private void init(){
		hashOperations = redisTemplate.opsForHash();		
	}

	@Override
	public String createRoulette(Roulette roulette) {
		String id =  UUID.randomUUID().toString();
		hashOperations.put(KEY, id, roulette);
		return id;
	}

}
