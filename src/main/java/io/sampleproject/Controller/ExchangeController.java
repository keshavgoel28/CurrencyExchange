package io.sampleproject.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.sampleproject.Bean.CurrencyData;
import io.sampleproject.Service.ExchangeServiceImpl;

@RestController
public class ExchangeController {
	@Autowired
	private ExchangeServiceImpl currencyService;
	@GetMapping(path="/home")
	public ResponseEntity<CurrencyData> currencyList(){
		CurrencyData dropDownList=currencyService.getWrapper();
		return new ResponseEntity<CurrencyData>(dropDownList, HttpStatus.OK);
	}
	@CrossOrigin
	@GetMapping(path ="/exchange/{from_currency}/{to_currency}")
	public ResponseEntity<Double> currencyExchange(@PathVariable("from_currency") String fromCurr,@PathVariable("to_currency") String toCurr, @RequestParam Integer amount){
		Double exchangedValue=currencyService.exchange(fromCurr,toCurr,amount);
		if (exchangedValue == null) {
			return new ResponseEntity<Double>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Double>(exchangedValue, HttpStatus.OK);
	}
}
