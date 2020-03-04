package com.pzhu.myblog.project.system.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
/**
 * 轮播图表
 *
 * @Author Guo Huaijian
 * @Date 2020/2/24 22:09
 */
@Data
@TableName(value = "blog_carousel")
public class BlogCarousel implements Serializable {
    /**
     * 轮播图id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 轮播图url
     */
    @TableField(value = "img_url")
    private String imgUrl;

    /**
     * 轮播图标题
     */
    @TableField(value = "title")
    private String title;

    /**
     * 轮播图跳转地址
     */
    @TableField(value = "url")
    private String url;

    /**
     * 是否展示该轮播图，1展示，2不展示
     */
    @TableField(value = "display")
    private String display;

    /**
     * 创建者
     */
    @TableField(value = "creator")
    private String creator;

    /**
     * 创建时间
     */
    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss",
            timezone = "GMT+8"
    )
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(value = "create_time")
    private Date createTime;

    /**
     * 修改时间
     */
    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss",
            timezone = "GMT+8"
    )
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(value = "update_time")
    private Date updateTime;

    private static final long serialVersionUID = 1L;

    public static final String COL_ID = "id";

    public static final String COL_IMG_URL = "img_url";

    public static final String COL_TITLE = "title";

    public static final String COL_URL = "url";

    public static final String COL_DISPLAY = "display";

    public static final String COL_CREATOR = "creator";

    public static final String COL_CREATE_TIME = "create_time";

    public static final String COL_UPDATE_TIME = "update_time";
}
