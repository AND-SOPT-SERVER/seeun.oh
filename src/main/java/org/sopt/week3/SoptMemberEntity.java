package org.sopt.week3;


import jakarta.persistence.*;

//create table sopt_member (
//	id bigint primary key auto_increment,
//    name varchar(16) not null,
//    age int not null
//);

@Entity
@Table(name="sopt_member")
public class SoptMemberEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "age", nullable = false)
    private int age;

    public SoptMemberEntity() {} //기본 생성자 필요
    public SoptMemberEntity(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
