package com.abc.chenzeshenga.logistics.model;

import jdk.nashorn.internal.objects.annotations.Constructor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.annotations.ConstructorArgs;

import java.io.Serializable;
import java.util.Arrays;

/**
 * l_img
 *
 * @author
 */
@Data @AllArgsConstructor @NoArgsConstructor public class Img implements Serializable {

    private String uuid;

    private byte[] img;

}