package com.neeraj.rest.webservices.restwebservices.filter;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@RestController
public class MyFilterController {
	
	@GetMapping("/path")
	public MappingJacksonValue show() {
		SomeBean someBean = new SomeBean("f1","f2","f3");
		MappingJacksonValue mapping=new MappingJacksonValue(someBean);
		SimpleBeanPropertyFilter filter=SimpleBeanPropertyFilter.filterOutAllExcept("field1","field2");
		FilterProvider filters=new SimpleFilterProvider().addFilter("someFilter", filter);
		mapping.setFilters(filters);
		return mapping;
	}
	@GetMapping("/path-list")
	public MappingJacksonValue showAll() {
		List<SomeBean> asList = Arrays.asList(new SomeBean("f1","f2","f3"),
				new SomeBean("f11","f21","f31"));
		SimpleBeanPropertyFilter sf=SimpleBeanPropertyFilter.filterOutAllExcept("field3","field1");
		FilterProvider filter=new SimpleFilterProvider().addFilter("someFilter", sf);
		MappingJacksonValue mp=new MappingJacksonValue(asList);
		mp.setFilters(filter);
		return mp;
	}
	

}
