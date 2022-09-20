package com.book.mapper;

import com.book.entity.Book;
import com.book.entity.Borrow;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface BookMapper {

    //获取所有书籍列表
    @Results({
            @Result(column = "bid", property = "Book_id"),
            @Result(column = "title", property = "Book_name"),
            @Result(column = "price", property = "price"),
            @Result(column = "desc", property = "Desc"),
            @Result(column = "bid", property ="borrow", one = @One(select = "getBorrowInfoByBid"))
    })
    @Select("select * from book")
    List<Book> getAllBookList();

    //根据BookId查找借阅信息
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "bid", property = "Book_ID"),
            @Result(column = "sid", property = "Student_ID"),
            @Result(column = "sid", property = "Student_Name", one = @One(select = "getStudentNameBySid")),
            @Result(column = "date", property = "time")
    })
    @Select("select * from borrow where bid = #{bid}")
    Borrow getBorrowInfoByBid(int bid);

    //根据StudentId查找学生名字
    @Select("Select name from student where uid = #{sid}")
    String getStudentNameBySid(String sid);

    //获取可借阅的书籍列表
    @Results({
            @Result(column = "bid", property = "Book_id"),
            @Result(column = "title", property = "Book_name"),
            @Result(column = "price", property = "price")
    })
    @Select("select * from book where not EXISTS (SELECT * from borrow where book.bid = borrow.bid)")
    List<Book> getAvailBookList();

    //插入借阅信息
    @Insert("Insert into borrow(sid, bid, date) values(#{sid}, #{bid}, NOW())")
    int addBorrowInfo(@Param("bid") int bid, @Param("sid") String sid);

    //根据借阅id删除借阅信息
    @Delete("delete from borrow where id = #{id}")
    int deleteBorrowInfo(int sid);

    //插入一条新的书籍信息
    @Insert("insert into book(title, `desc`, price) values(#{BookName}, #{desc}, #{price})")
    int addBookInfo(@Param("BookName") String BookName, @Param("desc") String desc, @Param("price") Float price);

    //根据书籍id删除书籍信息
    @Delete("Delete from book where bid = #{bid}")
    int deleteBookInfo(int bid);

    //获得所有书的数量
    @Select("Select count(*) from book")
    int getBookCounts();

    //获得还未借出去书的数量
    @Select("select count(*) from book where not EXISTS (SELECT * from borrow where book.bid = borrow.bid)")
    int getAvailBookCounts();

    //获得学生自己借书的数量
    @Select("Select count(*) from borrow where sid = #{sid}")
    int getStudentBookCounts(String sid);


}
