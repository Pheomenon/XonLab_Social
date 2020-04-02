package com.tensquare.pojo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @Author:Gao
 * @Date:2020-03-26 19:44
 */
@Data
@Entity
@Table(name = "tb_friend")
/**
 * IdClass : Specifies a composite primary key class that is mapped to multiple fields or properties of the entity.
 * 联合主键
 */
@IdClass(Friend.class)
public class Friend implements Serializable {
    @Id
    private String userid;
    @Id
    private String friendid;

    private String islike;
}
