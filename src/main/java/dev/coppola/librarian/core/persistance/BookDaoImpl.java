package dev.coppola.librarian.core.persistance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import dev.coppola.librarian.core.entity.Book;

public class BookDaoImpl implements BasicDao<Book> {
	
	@Override
	public Book findById(long id) {
		return null;
	}

	@Override
	public List<Book> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(Book t) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Book t) {
		// TODO Auto-generated method stub

	}

}
