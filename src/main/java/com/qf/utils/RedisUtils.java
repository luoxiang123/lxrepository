package com.qf.utils;

import org.springframework.data.redis.core.RedisTemplate;


public class RedisUtils {
	private RedisTemplate<String, String> redisTemplate;

	public void setRedisTemplate(RedisTemplate<String, String> redisTemplate) {
		this.redisTemplate = redisTemplate;
	}

	public void ladd(String key, String value) {
		redisTemplate.opsForList().rightPush(key, value);
	}

	public String getLast(String key) {
		return redisTemplate.opsForList().index(key, redisTemplate.opsForList().size(key) - 1);
	}

	public boolean exists(String key) {
		return redisTemplate.hasKey(key);
	}
}
