package entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author:Gao
 * @Date:2020-03-20 11:04
 */
@Data
@NoArgsConstructor
public class PageResult <T>{
    private long total;
    private List<T> rows;

    public PageResult(long total, List<T> rows) {
        this.total = total;
        this.rows = rows;
    }
}
