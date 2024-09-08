package com.um.service.portal.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class DashboardResponse {
    private Integer id;
    private String quote;
    private String author;
}
