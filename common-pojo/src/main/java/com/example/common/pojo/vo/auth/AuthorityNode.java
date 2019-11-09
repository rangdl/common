package com.example.common.pojo.vo.auth;

import com.google.common.collect.Lists;
import lombok.Data;

import java.util.List;

/**
 * @ClassName AuthorityNode
 * @Description TODO
 * @Author rdl
 * @Date 2019/10/9 18:55
 * @Version 1.0
 **/
@Data
public class AuthorityNode {
    private Long id;
    private Long parentId;
    private String fullId;
    private String label;
    private String code;
    private Integer sort;
    private List<AuthorityNode> children;

    public AuthorityNode(){}

    public AuthorityNode(Long id, Long parentId, String label) {
        this.id = id;
        this.parentId = parentId;
        this.label = label;
    }

    public List<AuthorityNode> getChildren() {
        if (children == null) {
            children = Lists.newArrayList();
        }
        return children;
    }
}
