package com.tmgg.greendao.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

/**
 * @author sunwei
 *         邮箱：tianmu19@gmail.com
 *         时间：2018/7/23 17:13
 *         包名：com.tmgg.greendao.bean
 *         <p>description:            </p>
 */
@Entity
public class Note {
    @Id(autoincrement = true)
    private Long id;
    @Property(nameInDb = "customId")
    private Long customId;
    private String content ;
    @Generated(hash = 363015704)
    public Note(Long id, Long customId, String content) {
        this.id = id;
        this.customId = customId;
        this.content = content;
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
    public Long getCustomId() {
        return this.customId;
    }
    public void setCustomId(Long customId) {
        this.customId = customId;
    }
    public String getContent() {
        return this.content;
    }
    public void setContent(String content) {
        this.content = content;
    }

}
