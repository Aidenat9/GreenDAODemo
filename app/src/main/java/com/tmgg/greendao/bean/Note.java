package com.tmgg.greendao.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

/**
 * @author sunwei
 *         邮箱：tianmu19@gmail.com
 *         时间：2018/7/19 10:07
 *         包名：com.tmgg.greendaogenerator.bean
 *         <p>description: 和用户唯一关联userId           </p>
 */

@Entity
public class Note {
    @Id(autoincrement = true)
    private Long id;

    private String content;
    private String createDate;
    private String editDate;
    @Property(nameInDb = "customId")
    private Long customId;
    @Generated(hash = 698381778)
    public Note(Long id, String content, String createDate, String editDate,
            Long customId) {
        this.id = id;
        this.content = content;
        this.createDate = createDate;
        this.editDate = editDate;
        this.customId = customId;
    }
    @Generated(hash = 1272611929)
    public Note() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getContent() {
        return this.content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public String getCreateDate() {
        return this.createDate;
    }
    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }
    public String getEditDate() {
        return this.editDate;
    }
    public void setEditDate(String editDate) {
        this.editDate = editDate;
    }
    public Long getCustomId() {
        return this.customId;
    }
    public void setCustomId(Long customId) {
        this.customId = customId;
    }

}
