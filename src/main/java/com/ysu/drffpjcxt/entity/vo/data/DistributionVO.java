package com.ysu.drffpjcxt.entity.vo.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author sheng
 * @date 2025-06-24
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DistributionVO {
    private String name;
    private Long value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = value;
    }
}