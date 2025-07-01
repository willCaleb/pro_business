package com.will.caleb.business.pattern;

import com.will.caleb.business.model.records.responses.AbstractResponseDTO;
import lombok.Getter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Getter
public class PageableBean<R extends AbstractResponseDTO> {

    private Pageable pageable;

    private List<R> content;

    private Long total;

    public PageableBean<R> withContent(List<R> respons) {
        this.content = respons;
        return this;
    }

    public PageableBean<R> withTotal(Long total) {
        this.total = total;
        return this;
    }

    public PageableBean<R> withPageable(Pageable pageable) {
        this.pageable = pageable;
        return this;
    }

    public Page<R> getPaged() {
        return new PageImpl<>(
                this.content,
                this.pageable,
                this.total
        );
    }
}
