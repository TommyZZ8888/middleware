redis.call('ZADD',KEYS[1],ARGV[1],ARGV[2])

local res = redis.call('ZCARD',KEYS[1])

if res==nil or (res < tonumber(ARGV[3])) then
    redis.call('ZADD',KEYS[1],ARGV[1],ARGV[4])
end

redis.call('ZADD',KEYS[1],ARGV[1],ARGV[4])

redis.call('EXPIRE',KEYS[1],ARGV[5])

return 1