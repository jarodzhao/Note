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

    public Note(){
        mId = UUID.randomUUID();
        mDate = new Date();
    }

    public UUID getId() {
        return mId;
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getmContent() {
        return mContent;
    }

    public void setmContent(String mContent) {
        this.mContent = mContent;
    }

    public Date getmDate() {
        return mDate;
    }

    public void setmDate(Date mDate) {
        this.mDate = mDate;
    }

    public String getmClass() {
        return mClass;
    }

    public void setmClass(String mClass) {
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
