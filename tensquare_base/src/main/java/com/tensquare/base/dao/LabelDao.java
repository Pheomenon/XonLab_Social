package com.tensquare.base.dao;

import com.tensquare.base.pojo.Label;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @Author:Gao
 * @Date:2020-03-20 12:26
 */

public interface LabelDao extends JpaRepository<Label,String>, JpaSpecificationExecutor<Label> {
}
