package com.threadsync.project.request;

import lombok.*;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor
@NoArgsConstructor
@Data
@SuperBuilder
public class BaseSelector {
    
    @Builder.Default
    private Integer page = 1;
    private Integer size = 2;

    private Integer offset;

    public Integer getOffset(){
        return (page - 1) * size;
    }
}
