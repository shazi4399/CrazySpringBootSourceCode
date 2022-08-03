package org.crazyit.app.controller;

import org.crazyit.app.dao.UserDao;
import org.crazyit.app.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Description:<br>
 * 网站: <a href="http://www.crazyit.org">疯狂Java联盟</a><br>
 * Copyright (C), 2001-2022, Yeeku.H.Lee<br>
 * This program is protected by copyright laws.<br>
 * Program Name:<br>
 * Date:<br>
 *
 * @author Yeeku.H.Lee kongyeeku@163.com 公众号: fkbooks<br>
 * @version 1.0
 */
@RestController
@RequestMapping("/user")
public class UserController
{
	@Autowired
	private UserDao userDao;

	@PostMapping
	public Mono<User> testSave(@RequestBody User user)
	{
		return userDao.save(user);
	}

	@GetMapping("/name={name}")
	public Flux<User> testFindByName(@PathVariable String name)
	{
		return userDao.findByName(name);
	}

	@GetMapping("/passPattern={passPattern}")
	public Flux<User> testFindByPasswordLike(@PathVariable String passPattern)
	{
		return userDao.findByPasswordLike(passPattern);
	}

	@GetMapping("/start={start}/end={end}")
	public Flux<User> testFindByAgeBetween(@PathVariable int start,
			@PathVariable int end)
	{
		return userDao.findByAgeBetween(start, end);
	}

	@GetMapping("/subName={subName}/passPrefix={passPrefix}")
	public Flux<User> testFindByNameContainsAndPasswordStartsWith(
			@PathVariable String subName, @PathVariable String passPrefix)
	{
		return userDao.findByNameContainsAndPasswordStartsWith(
				subName, passPrefix);
	}

	@DeleteMapping("/namePattern={namePattern}")
	public Mono<Integer> testDeleteByNameLike(@PathVariable String namePattern)
	{
		return userDao.deleteByNameLike(namePattern);
	}

	@GetMapping("/namePattern={namePattern}/minAge={minAge}")
	public Flux<User> testFindBySql(@PathVariable String namePattern,
			@PathVariable int minAge)
	{
		return userDao.findBySql(namePattern, minAge);
	}

	@PutMapping("/{id}/name={name}")
	@Transactional
	public Mono<Integer> testUpdateNameById(@PathVariable Integer id,
			@PathVariable String name)
	{
		return userDao.updateNameById(name, id);
	}

	@GetMapping("/startAge={startAge}/endAge={endAge}")
	public Flux<User> testCustomQuery1(@PathVariable int startAge,
			@PathVariable int endAge)
	{
		return userDao.customQuery1(startAge, endAge);
	}

	@GetMapping("/startAge={startAge}/passPattern={passPattern}")
	public Flux<User> testCustomQuery2(@PathVariable int startAge,
			@PathVariable String passPattern)
	{
		return userDao.customQuery2(startAge, passPattern);
	}
}
