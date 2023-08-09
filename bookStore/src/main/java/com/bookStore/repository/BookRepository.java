package com.bookStore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.bookStore.entity.Book;
import com.bookStore.entity.User;

@Repository
public interface BookRepository extends JpaRepository<Book,Integer>  {

    List<Book>  findByStatus(String status);
}
