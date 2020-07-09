package valentina.roulettetestapi.repository;

import java.util.Map;
import valentina.roulettetestapi.domain.Bet;
import valentina.roulettetestapi.domain.Roulette;

public interface RedisRepository {
	
	String createRoulette(Roulette roulette);
	Map<String,Roulette> findAllRoulettes();
	int openRoulette(String idRoulette);
	int closedRoulette(String idRoulette);
	
}