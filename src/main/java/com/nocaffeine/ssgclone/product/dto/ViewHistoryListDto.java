package com.nocaffeine.ssgclone.product.dto;

import com.nocaffeine.ssgclone.product.vo.request.ViewHistoryDeleteRequestVo;
import com.nocaffeine.ssgclone.product.vo.request.ViewHistoryRequestVo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder // 빌더는 객체를 생성할 때 사용하는데, 생성자를 사용하지 않고 객체를 생성할 수 있다.
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ViewHistoryListDto {

    private List<Long> productIds;


    public static ViewHistoryListDto viewHistoryVoToDto(ViewHistoryDeleteRequestVo viewHistoryDeleteRequestVo) {
        return ViewHistoryListDto.builder()
                .productIds(viewHistoryDeleteRequestVo.getProductIds())
                .build();

    }
}