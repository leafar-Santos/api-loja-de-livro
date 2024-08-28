package com.santos.lojaLivros.Resources.exceptions;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class StandardError {

    private long timestamp;

    private Integer status;

    private String error;
}
