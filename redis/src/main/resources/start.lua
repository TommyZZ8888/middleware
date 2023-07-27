--"return {KEYS[1],KEYS[2],ARGV[1],ARGV[2]}" 2 key1 key2 first second
--keys数组有两个元素key1和key2，arg数组元素中有两个元素first和second
--其实{KEYS[1],KEYS[2],ARGV[1],ARGV[2]}表示的是Lua语法中“使用默认索引”的table表[数组]，
--相当于java中的map中存放四条数据。Key分别为：1、2、3、4，而对应的value才是：KEYS[1]、KEYS[2]、ARGV[1]、ARGV[2]

redis.call('SET',KEYS[1],ARGV[1])
redis.call('EXPIRE',KEYS[1],ARGV[2])
return 1

--redis.call('DEL',KEYS[1])
--return 1
