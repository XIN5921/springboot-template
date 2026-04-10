package org.example.week06.entity;

/**
 * @author ZhangXin       // 作者：系统当前用户名
 * @date 2026/4/10 16:20 // 创建日期+时间
 * @description 类描述信息
 */
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;


@Data
@TableName("data")
public class Special {
    @Schema(description = "主键")
    @TableId("id")
    private String id;

    @Schema(description = "标题")
    private String title;

    @Schema(description = "封面")
    private String banner;

    @Schema(description = "描述")
    private String introduction;

    @Schema(description = "是否关注")
    private String isFollowing;

    @Schema(description = "关注数量")
    private Integer followersCount;

    @Schema(description = "浏览数量")
    private Integer viewCount;

    @Schema(description = "更新时间")
    private Long updated;
}
