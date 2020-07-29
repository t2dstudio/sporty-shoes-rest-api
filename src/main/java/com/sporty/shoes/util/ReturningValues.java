package com.sporty.shoes.util;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReturningValues {
	public String message;
	public Object obj;
	public List<?> list;
	public Map<?,?> map;
	public Page<?> page;

}