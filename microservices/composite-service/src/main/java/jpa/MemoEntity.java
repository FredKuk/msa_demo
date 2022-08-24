package com.example.demo.jpa;
import javax.persistence.*;
import lombok.Data;
@Data
@Entity
@Table(name="tbl_memo")
public class MemoEntity{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(length=200,nullable=false)
    private String memoText;
}