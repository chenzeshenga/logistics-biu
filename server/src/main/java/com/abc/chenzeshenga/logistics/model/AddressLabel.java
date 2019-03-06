package com.abc.chenzeshenga.logistics.model;

import lombok.Data;

import java.util.List;

/**
 * @author chenzeshenga
 * @version 1.0
 */
@Data public class AddressLabel {

    private String label;

    private String value;

    private List<AddressLabel> children;

}
