package com.neeraj.rest.webservices.restwebservices.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersionController {

	
	@GetMapping(value="/",params="id=1")
	public Version1 showNameV1() {
		Name name=new Name("neeraj","chand");
		return new Version1(name);
	}
	
	@GetMapping(value="/",params="id=2")
	public Version2 showNameV2() {
		
		return new Version2("neeraj chand");
	}
	
	@GetMapping(value="/",headers="X_id=1")
	public Version1 showNameV1Header() {
		Name name=new Name("neeraj","chand");
		return new Version1(name);
	}
	
	@GetMapping(value="/",headers="X_id=2")
	public Version2 showNameV2Header() {
		
		return new Version2("neeraj chand");
	}
	
}
