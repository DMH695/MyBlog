package com.example.myblog.po;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//为了具备和数据库对应生成的能力
@Entity
//说明生成指定名字的表
@Table(name="t_blog")
//创建博客这个实体类
public class Blog {
    //id必须标识
    @Id
    //表示再mysql中自动生成
    @GeneratedValue
    private Long id;
    private String title;
    private String content;
    private String firstPicture;//首图地址
    private String flag;//标记
    private Integer views;//浏览次数
    private boolean appreciation;//是否开启赞赏功能
    private boolean shareStatement;//转载声明是否开启
    private boolean commentabled;//评论
    private boolean published;//是否发布
    private boolean recommend;//是否推荐
    @Temporal(TemporalType.TIMESTAMP)
    private Date creatTime;
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;
    //表示type为多的一端
    @ManyToOne
    //一个博客对应一个分类
    private Type type;

   //设置级联关系，如果新增一个博客，需要新增一个连同的标签，新增一个tag就会把tag保存到数据库里面
    @ManyToMany(cascade = {CascadeType.PERSIST})
    //博客与标签是多对多的关系
    private List<Tag> tags = new ArrayList<>();

    //一个博客对应一个用户
    @ManyToOne
    private User user;

    //该注解表示不入库
    @Transient
    private String tagIds;

    public void setRecommend(boolean recommend) {
        this.recommend = recommend;
    }

    public String getTagIds() {
        return tagIds;
    }

    public void setTagIds(String tagIds) {
        this.tagIds = tagIds;
    }


    @OneToMany(mappedBy = "blog")
    public List<Comment> getConmments() {
        return conmments;
    }

    public void setConmments(List<Comment> conmments) {
        this.conmments = conmments;
    }

    @OneToMany
    private List<Comment> conmments = new ArrayList<>();
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public Blog(){};

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getFirstPicture() {
        return firstPicture;
    }

    public void setFirstPicture(String firstPicture) {
        this.firstPicture = firstPicture;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    public boolean isAppreciation() {
        return appreciation;
    }

    public void setAppreciation(boolean appreciation) {
        this.appreciation = appreciation;
    }

    public boolean isShareStatement() {
        return shareStatement;
    }

    public void setShareStatement(boolean shareStatement) {
        this.shareStatement = shareStatement;
    }

    public boolean isCommentabled() {
        return commentabled;
    }

    public void setCommentabled(boolean commentabled) {
        this.commentabled = commentabled;
    }

    public boolean isPublished() {
        return published;
    }

    public void setPublished(boolean published) {
        this.published = published;
    }

    public boolean isRecommend() {
        return recommend;
    }

    public void setRecommened(boolean recommend) {
        this.recommend = recommend;
    }

    public Date getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(Date creatTime) {
        this.creatTime = creatTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    //由于tagIds这个属性不在数据库里面，为了使前端页面拿到tagIds，所以需要进行初始化
    public void init(){
        this.tagIds = tagsToIds(this.getTags());
    }
    private String tagsToIds(List<Tag> tags){
        if(!tags.isEmpty()){
            StringBuffer ids = new StringBuffer();
            boolean flag = false;
            for(Tag tag : tags){
                if(flag){
                    ids.append(",");
                }else {
                    flag = true;
                }
                ids.append(tag.getId());
            }
            return ids.toString();
        }else {
            return tagIds;
        }
    }


    @Override
    public String toString() {
        return "Blog{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", firstPicture='" + firstPicture + '\'' +
                ", flag='" + flag + '\'' +
                ", views=" + views +
                ", appreciation=" + appreciation +
                ", shareStatement=" + shareStatement +
                ", commentabled=" + commentabled +
                ", published=" + published +
                ", recommened=" + recommend +
                ", creatTime=" + creatTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
