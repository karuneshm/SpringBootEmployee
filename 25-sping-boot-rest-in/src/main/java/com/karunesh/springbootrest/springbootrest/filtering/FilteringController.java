package com.karunesh.springbootrest.springbootrest.filtering;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;



@RestController
public class FilteringController {

	
	//JsonIgnore on property
	//JsonIgnoreProperties(value = {field1,field2})  -- on class level
	@GetMapping("/filtering")
	public SomeBean retriverSomeBeanStatic() {
		return new SomeBean("Value1", "Value2","Value2");
	}
	
	@GetMapping("/filtering-list")
	public List<SomeBean> retriverSomeBeanListStatic() {
		return Arrays.asList(
				new SomeBean("Value1", "Value2","Value2"),
				new SomeBean("Value4", "Value5","Value6"));
	}
	
	
	@GetMapping("/dynamic-filtering")
	public MappingJacksonValue retriverSomeBeanDynamic() {
		 SomeBean someBean = new SomeBean("Value1", "Value2","Value2");
		 SimpleBeanPropertyFilter filters = SimpleBeanPropertyFilter.filterOutAllExcept("value1");
		 
		 FilterProvider filterProvider = new SimpleFilterProvider().addFilter("SomeBeanFiler", filters);
				 
		 
		 MappingJacksonValue mapping = new MappingJacksonValue(someBean);
		 mapping.setFilters(filterProvider);
		 
		 
		 return mapping;
	}
}
