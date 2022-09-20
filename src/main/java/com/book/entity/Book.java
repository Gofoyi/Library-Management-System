package com.book.entity;

import lombok.Data;
@Data
public class Book {
    int Book_id;
    String Book_name;
    String price;
    String Desc;
    Borrow borrow;
    boolean status = true;//是否借出
}
