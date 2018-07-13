package com.audien.b2c.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class EhCacheServiceImpl implements EhCacheService {
	private static final Logger logger = LoggerFactory.getLogger(EhCacheServiceImpl.class);


	public Map<String, Object> getNormalDataNonCaching(String param) {
	    return genMap(param);
	}
	
/*	http://www.egovframe.go.kr/wiki/doku.php?id=egovframework:rte2:fdl:cache_abstraction
 * 자바메소드에 @Cacheable을 설정함으로써 Caching할 수 있다. 타겟메소드가 호출되었을 때, 캐시에 해당 메서드가 이미 동일한 인자로 있는지 확인하고, 만약 있다면 메소드를 호출하지 않고 캐시해둔 결과를 Proxy에서 반환하게 된다.

	Java Method에 적용가능한 Cache Annotation은 다음과 같다.

	@Cacheable : Cache에 메소드 데이터를 생성한다.
	@CacheEvict : Cache에 메소드 데이터를 삭제한다.
	
	@Cacheable 사용방법
		별다른 조건없이 호출되는 모든 인자를 caching하고자 할 때는 아래와 같이 cache명만 쓰면 된다. 이 코드는 Cache이름이 “books”인 cache저장소를 사용하였다. 
		메소드가 호출될 때 매번 “books”에 Cache데이터를 확인하고 이미 실행된 적이 있는지를 확인한다. 만약 “books”에 데이터가 있으면 그 값을 반환하게 된다.

	기본

	@Cacheable("books")
	public Book findBook(ISBN isbn, boolean checkWarehouse, boolean includeUsed)
	Cache되는 저장소를 여러개 정의할 수도 있다. 아래의 코드에서는 findBook메소드 호출 시 “books”와 “isbns” 두 군데에 캐시데이터가 저장된다.

	<여러 cache 저장소에 caching>

	@Cacheable({ "books", "isbns" })
	public Book findBook(ISBN isbn) {...}

	@CacheEvict는 @Cacheable과 반대로 cache저장소의 데이터를 제거함으로써 사용하지 않는 데이터를 정리하는데 유용하다. @CacheEvict는 캐시삭제를 수행할 메서드에 선언한다. 
	@CacheEvict도 여러개의 캐시를 명시할 수 있으며, key와 condition을 사용할 수 있다. 또한 allEntries속성은 키값으로 Cache Entrie하나만 비우는 것이 아니라 캐시영역의 모든 Entrie를 비우도록 한다. 이 경우에는 키를 명시하더라도 이를 무시하고 모든 Entrie를 비우게 된다.
	
	@CacheEvict(value = "books", allEntries=true)
	public void loadBooks(InputStream batch)
	
*/

	@Cacheable(value = "budgetCache", key = "#param")
	public Map<String, Object> getNormalDataCaching(String param) {
	    return genMap(param);

	}

	@CacheEvict(value = "budgetCache", key = "#param")
	public Map<String, Object> getNormalDataEraseCache(String param) {
	    return genMap(param);
	}
	
	@Cacheable(value="budgetCache")
	public String getDateToString() {
		return new Date().toString();
	}

	public Map genMap(String param) {
		Map<String, Object> map = new HashMap<>();
		Random random = new Random();
	    map.put("getRandom", random.nextLong());
	    
	    logger.info(">>>>> GEN-MAP PARAM : {}", param);
	    logger.info(">>>>> GEN-MAP RESULT : {}", map);

	    return map;
	}

}
