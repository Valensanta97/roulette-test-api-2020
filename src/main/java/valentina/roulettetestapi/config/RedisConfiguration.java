package valentina.roulettetestapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import valentina.roulettetestapi.domain.Bet;
import valentina.roulettetestapi.domain.Roulette;

@Configuration
public class RedisConfiguration {

	@Bean
	JedisConnectionFactory jedisConnectionFactory() {
		return new JedisConnectionFactory();
	}

	@Bean
	RedisTemplate<String, Roulette> rouletteTemplate() {
		final RedisTemplate<String, Roulette> redisTemplate = new RedisTemplate<>();
		redisTemplate.setConnectionFactory(jedisConnectionFactory());
		return redisTemplate;
	}

	@Bean
	RedisTemplate<String, Bet> betTemplate() {
		final RedisTemplate<String, Bet> betTemplate = new RedisTemplate<>();
		betTemplate.setConnectionFactory(jedisConnectionFactory());
		return betTemplate;
	}
}
