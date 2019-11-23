package com.ck.ck181228.init;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ck.ck181228.user_management.model.UserModel;

import net.sf.json.JSONObject;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.exceptions.JedisConnectionException;

public class CacheKit {

	private static Logger logger = LoggerFactory.getLogger(CacheKit.class);  
	
	private List<?> resultList;  
	
	private static JedisPool pool;
	
	public static void initializePool() {
		JedisPoolConfig config = new JedisPoolConfig();
		//设置最大空闲数
		config.setMaxIdle(10);
		//设置最大连接数
		config.setMaxTotal(100);
		//获取jedis连接的最大等待时间
		config.setMaxWaitMillis(50 * 1000);
		//获取jedis链接时，自动检验连接是否可用
		config.setTestOnBorrow(true);
		//在将连接放回池中前，自动检验连接是否有效
		config.setTestOnReturn(true);
		//自动测试池中的空闲连接是否都是可用连接
		config.setTestWhileIdle(true);
		//创建连接池
		pool = new JedisPool(config,"127.0.0.1",6379);
	}
	
	private static synchronized void poolInit() {
		if(pool == null) {
			initializePool();
		}
	}
	
	private static Jedis getJedis() {
		if(pool == null) {
			poolInit();
		}
		
		int timeoutCount = 0;
		while(true) {
			try {
				if(pool != null) {
					return pool.getResource();
				}
			} catch (Exception e) {
				// TODO: handle exception
				if(e instanceof JedisConnectionException) {
					timeoutCount++;
					logger.warn("getJedis timeoutCount={}",timeoutCount);
					if(timeoutCount > 3) {
						break;
					}
				}else {
					/* logger.warn("jedisInfo ... NumActive=" + pool.getResource().get("")
                    + ", NumIdle=" + pool.getNumIdle()  
                    + ", NumWaiters=" + pool.getNumWaiters()  
                    + ", isClosed=" + pool.isClosed());  */
					logger.warn(pool.getResource()+"//"+pool);
					logger.error("GetJedis error,",e);
					break;
				}
			}
			break;
		}
		return null;
	}
	
	private static void returnResource(Jedis jedis) {
		if(jedis != null) {
			pool.returnResourceObject(jedis);
		}
	}
	
	private String safeGet(String key) {
		String value = util().get(key);
		returnResource(util());
		return value;
	}
	
	private void safeSet(String key,int time,String value) {
		util().setex(key, time, value);
		returnResource(util());
	}
	
	private void safeDel(String key) {
		util().del(key);
		returnResource(util());
	}
	
	private static void clearCache() {
		CacheKit kit = new CacheKit();
		Jedis jedis = getJedis();
		while(true) {
			if(jedis != null) {
				break;
			}else {
				jedis = getJedis();
			}
		}
		Iterator it = jedis.keys("redis").iterator();
		while(it.hasNext()) {
			String key = (String)it.next();
//			kit.del
		}
				
	}
	
	public String getByCache(HttpServletRequest request) {
        String result = safeGet(request.getRemoteAddr());  
        if (result != null) {  
            return result;  
        }  
        return null;  
    }
	
	public UserModel getByCacheModel(HttpServletRequest request) {
        String result = safeGet(request.getRemoteAddr());  
        if (result != null) {  
            return (UserModel)JSONObject.toBean(JSONObject.fromObject(result),UserModel.class);  
        }  
        return null;  
    }
  
    public String getByCacheToString(String key) {
        String result = safeGet(key);  
        if (result != null) {  
            return result;  
        }  
        return null;  
  
    }  
  
//    public List<?> getArrayByCache(String key) {  
//        String result = safeGet(key);  
//        if (result != null) {  
//            resultList = net.sf.json.JSONArray.;  
//            return resultList;  
//        }  
//        return null;  
//    }  
  
    public Object getJSONArrayByCache(String key) {  
        String result = safeGet(key);  
        if (result != null) {  
            return (Object)result;  
        }  
        return null;  
    }  
  
    public void setByCache(HttpServletRequest request, UserModel usermodel) {  
        safeSet(request.getRemoteAddr(), 86400, JSONObject.fromObject(usermodel).toString()); 
    }  
  
    public void setByCacheOneHour(String key, String s) {  
        safeSet(key, 3600, s);  
    }  
  
    public void setByCacheOneHour(String key, List<?> json) {  
        safeSet(key, 86400, new JSONArray(json).toString());  
        resultList = json;  
    }  
  
    public void setByCache(String key, JSONObject json) {  
        safeSet(key, 86400, json.toString());  
    }  
  
    public void setByCache(String key, List<?> list) {  
        safeSet(key, 86400, new JSONArray(list).toString());  
        resultList = list;  
    }  
  
    public void setByCache(String key, JSONArray array) {  
        safeSet(key, 86400, array.toString());  
    }  
  
    public void setByCacheCusTime(String key, String s, int time) {  
        safeSet(key, time, s);  
    }  
  
    public void delByCache(String key) {
    	//该方法删除指定的key
        if (null != safeGet(key)) {  
            safeDel(key);  
        }  
    }
    
    //该方法用来清除所有相关redis的key
    public void delRedisRelevantKey(){
    	clearCache();
    }
  
    public JSONObject toJSON(JSONObject db) {
        return db;  
    }  
  
    public List<JSONObject> toJSON(List<JSONObject> list) {
        List<JSONObject> json = new ArrayList<>();  
        for (JSONObject aList : list) {  
            json.add(aList);  
        }
        return json;  
    }  
  
    public boolean notNull() {
        return resultList != null && resultList.size() > 0;  
    }  
  
    public List<?> getResult() {
        return resultList;
    }
//    
//    public static void main(String[] args) {
//    	//clearCache();  到这里自己去测试一下是否可以
//	}
	
	public Jedis util() {
		Jedis jedis = getJedis();
		while(true) {
			if(jedis != null) {
				break;
			}else {
				jedis = getJedis();
			}
		}
		return jedis;
	}
}
