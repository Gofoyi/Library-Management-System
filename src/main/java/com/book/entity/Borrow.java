package com.book.entity;

import lombok.Data;

import java.util.Date;
@Data
public class Borrow {
    int id;
    String Student_ID;
    String Student_Name;
    int Book_ID;
    Date time;
}
