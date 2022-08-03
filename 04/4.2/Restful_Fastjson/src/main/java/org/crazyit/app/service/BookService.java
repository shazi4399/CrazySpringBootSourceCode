package org.crazyit.app.service;

import org.crazyit.app.domain.Book;

import java.util.Collection;

public interface BookService
{
	Collection<Book> list();

	Book createOrUpdate(Book book);
}
