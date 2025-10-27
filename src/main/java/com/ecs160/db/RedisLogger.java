package com.ecs160.db;

import redis.clients.jedis.Jedis;

public class RedisLogger {
    private Jedis jedis;

    public RedisLogger(String host, int port) {
        this.jedis = new Jedis(host, port);
    }

    public void log(String message) {
        jedis.lpush("logs", message);
    }

    public String getLatestLog() {
        return jedis.lpop("logs");
    }

    public void close() {
        jedis.close();
    }
}
