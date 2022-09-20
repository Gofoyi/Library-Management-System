package com.book.service.Impl;


import com.book.entity.Book;
import com.book.entity.Borrow;
import com.book.mapper.BookMapper;
import com.book.service.BorrowListService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service
public class BorrowListServiceImpl implements BorrowListService {

    @Resource
    BookMapper mapper;

    @Override
    public List<Book> getBorrowListByRole(String role) {
        return null;
    }

    @Override
    public List<Book> getAllBookList() {

        //对数据库查询，得到Book类的一个List
        List<Book> list = mapper.getAllBookList();

        //对数据进行操作
        for (Book book : list) {
            if (book.getBorrow() == null) {
                book.setBorrow(new Borrow());//Borrow对象里面的数据是空的
                book.setStatus(false);//表示这本书没有借出
            }
        }
        return list;
    }

    @Override
    public List<Book> getAvailBookList() {
        return mapper.getAvailBookList();
    }

    @Override
    public List<Book> getUserBookList(String username) {
        List<Book> list = mapper.getAllBookList();
        list.removeIf(book -> book.getBorrow() == null || !book.getBorrow().getStudent_Name().equals(username));
        return list;
    }


}
