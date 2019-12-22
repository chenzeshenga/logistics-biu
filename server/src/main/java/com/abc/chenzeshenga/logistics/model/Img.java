package com.abc.chenzeshenga.logistics.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * l_img
 *
 * @author
 */
@Data @AllArgsConstructor @NoArgsConstructor public class Img implements Serializable {

    private String uuid;

    private byte[] img;

}