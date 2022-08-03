package org.crazyit.app.dao;

import org.crazyit.app.domain.Author;
import org.springframework.data.neo4j.repository.query.ExistsQuery;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
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
public interface AuthorDao extends ReactiveCrudRepository<Author, Long>
{
	Mono<Author> findByName(String name);
}
