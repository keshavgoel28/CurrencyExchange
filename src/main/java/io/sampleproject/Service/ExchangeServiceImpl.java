package io.sampleproject.Service;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import io.sampleproject.Bean.CurrencyData;

@Service
public class ExchangeServiceImpl {
	 public double exchange(String fromCurr,String toCurr,Integer amount) {
		 Double exchangedValue;
		 RestTemplate restTemplate = new RestTemplate();
		 String url="https://free.currconv.com/api/v7/convert?q="+fromCurr+"_"+toCurr+"&compact=ultra&apiKey=7f274fab4b7af7dc0597";
		 Map exchange = restTemplate.getForObject(url,Map.class);
		 Double value=(Double)exchange.get(fromCurr+"_"+toCurr);
		 exchangedValue=amount*value;
		 return exchangedValue;
	 }
	 @Cacheable("list")
	 public CurrencyData getWrapper() {
		 RestTemplate restTemplate = new RestTemplate();
		 String url="https://free.currconv.com/api/v7/currencies?apiKey=7f274fab4b7af7dc0597";
		 Map exchange = restTemplate.getForObject(url,Map.class);
		 CurrencyData obj=new CurrencyData();
		 Map curr=(Map)exchange.get("results");
		 Set obj2=curr.keySet();
		 List<String> currencies=new ArrayList<>(obj2);
		 obj.setFromCurr(currencies);
		 obj.setToCurr(currencies);
		 return obj;
	 }
}
