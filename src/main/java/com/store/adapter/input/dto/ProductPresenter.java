package com.store.adapter.input.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductPresenter {
    private String id;
    private String name;
    private String price;
}
