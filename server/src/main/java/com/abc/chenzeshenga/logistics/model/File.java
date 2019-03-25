package com.abc.chenzeshenga.logistics.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Arrays;

/**
 * l_file
 *
 * @author
 */
@Data @AllArgsConstructor @NoArgsConstructor public class File implements Serializable {

    private String uuid;

    private byte[] file;

}