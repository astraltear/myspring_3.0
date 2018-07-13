package com.audien.b2c.service;

import java.util.Map;

public interface EhCacheService {
	public Map getNormalDataNonCaching(String param);
	public Map getNormalDataCaching(String param);
	public Map getNormalDataEraseCache(String param); 
	public String getDateToString(); 
}
