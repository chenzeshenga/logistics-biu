package com.abc.chenzeshenga.logistics.model.common;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

/**
 * @author chenzeshenga
 * @since 2020-03-8 22:14
 */
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonIgnoreProperties(value = {"handler"})
public class PageData<T> extends Pagination {

    private List<T> data;
}
