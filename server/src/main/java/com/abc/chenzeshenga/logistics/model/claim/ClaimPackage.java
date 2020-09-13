package com.abc.chenzeshenga.logistics.model.claim;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author chenzeshenga
 * @since 2020-09-13
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ClaimPackage {

    private String uuid;
    private String returnNo;
    private double length;
    private double width;
    private double height;
    private double weight;
    private String carrier;
    private String trackNo;

}