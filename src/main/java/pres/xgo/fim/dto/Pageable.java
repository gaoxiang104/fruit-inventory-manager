package pres.xgo.fim.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

/**
 * 分页
 * @param <T>
 */
@Data
public class Pageable<T> {
    private Integer currentPage;
    // 每页记录数
    private Integer pageSize;
    private Integer countPage;
    // 所有数据记录数
    private Integer allDateCount;
    private List<T> data;

    public Pageable() {
    }

    public Pageable(Integer currentPage, Integer pageSize, Integer allDateCount, List<T> data) {
        this.currentPage = currentPage;
        this.pageSize = pageSize;
        this.allDateCount = allDateCount;
        this.data = data;
    }

    public Integer getCountPage() {

        int i = new BigDecimal(allDateCount)
                .divide(BigDecimal.valueOf(pageSize), 0, RoundingMode.UP).intValue();

        return i;
    }
}
