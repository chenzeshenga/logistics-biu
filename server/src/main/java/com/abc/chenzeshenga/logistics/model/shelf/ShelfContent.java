package com.abc.chenzeshenga.logistics.model.shelf;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author chenzeshenga
 * @since 2020-03-16 00:09
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ShelfContent {

  private String owner;
  private List<UpShelfProduct> content;
}
