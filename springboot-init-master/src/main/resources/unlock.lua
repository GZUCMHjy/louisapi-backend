
-- 锁key(动态做为参数)
local key = KEYS[1];
-- 当前线程标识(动态做为参数)
local threadId = ARGV[1];

-- 获取锁中的线程标识
local id = redis.call('get',KEYS[1])
if(id == ARGV[1]) then
    redis.call("del", KEYS[1]);
end
return 0;