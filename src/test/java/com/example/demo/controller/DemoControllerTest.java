package com.example.demo.controller;

import java.util.ArrayList;
import java.util.stream.Stream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class DemoControllerTest {

	@Test
	public void test() {
		Stream<Integer> stream = Stream.of(1,2,3,4,5,6,7,8,9);
        stream.forEach(p -> System.out.println(p));
        
        Stream.of(new ArrayList());
	}

}
