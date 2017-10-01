package zhao.jarod.note.domain;

import java.util.Date;
import java.util.UUID;

/**
 * Created by zht on 2017/8/6.
 */
public class Note {

    private UUID mId;

    private String mTitle;

    private String mContent;

    private Date mDate;

    private String mClass;

    private Boolean mDeleted;

    private Boolean mFavorited;

    //默认构造方法
    public Note(){
        this(UUID.randomUUID());
    }

    public Note(UUID id) {
        mId = id;
        mDate = new Date();
        mFavorited = false;
    }

    public UUID getId() {
        return mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getContent() {
        return mContent;
    }

    public void setContent(String mContent) {
        this.mContent = mContent;
    }

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date mDate) {
        this.mDate = mDate;
    }

    public String getmClass() {
        return mClass;
    }

    public void setClass(String mClass) {
        this.mClass = mClass;
    }

    public Boolean isDeleted() {
        return mDeleted;
    }

    public void setDeleted(Boolean mDeleted) {
        this.mDeleted = mDeleted;
    }

    public Boolean isFavorited() {
        return mFavorited;
    }

    public void setFavorited(Boolean mFavorited) {
        this.mFavorited = mFavorited;
    }

}
