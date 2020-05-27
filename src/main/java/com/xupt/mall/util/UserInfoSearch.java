package com.xupt.mall.util;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;

/**
 * TODO
 *
 * @author pavi
 * @date 2020/5/26 10:55
 */
@Data
public class UserInfoSearch implements Serializable {


    private String loginName;

}
