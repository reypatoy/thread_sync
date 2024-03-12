package com.threadsync.project.response;

import java.util.List;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ListResponse<E> {

    private long total;
    private int start;
    private List<? extends E> result;

    @SuppressWarnings("unchecked")
    public static <E> ListResponse<E> build(long total, int start, List<? extends E> result) {
        
        @SuppressWarnings("rawtypes")
        ListResponse listResponse = ListResponse.builder().total(total).start(start).result(result).build();
        return listResponse;
    }
}
