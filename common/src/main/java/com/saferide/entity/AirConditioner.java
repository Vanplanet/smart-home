package com.saferide.entity;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * <p>
 *
 * </p>
 *
 * @author saferide
 * @since 2021-04-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class AirConditioner implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private Integer temperature;

    private String type;

    private Integer speed;
}
