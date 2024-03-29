package com.nocaffeine.ssgclone.cart.dto.request;


import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartRemoveListRequest {
    private List<Long> cartIdlist;
}
