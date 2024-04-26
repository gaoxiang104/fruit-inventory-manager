package pres.xgo.fim.dto;

import lombok.Data;

/**
 * 分页请求 dto
 */
@Data
public class BasePageReqDto {
    private Integer pageSize = 24;
    private Integer currentPage = 1;
    private Integer offset;

    public Integer getOffset() {
        return (currentPage - 1) * pageSize;
    }
}
