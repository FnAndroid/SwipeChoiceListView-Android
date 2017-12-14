package divin.customview.swipechoicelist;

/**
 * Created by Divin on 2017/12/14.
 */

public class ItemDataBean {
    private String fileName;
    private String fileMessage;

    public ItemDataBean(String fileName, String fileMessage) {
        this.fileName = fileName;
        this.fileMessage = fileMessage;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileMessage() {
        return fileMessage;
    }

    public void setFileMessage(String fileMessage) {
        this.fileMessage = fileMessage;
    }

    @Override
    public String toString() {
        return "ItemDataBean{" +
                "fileName='" + fileName + '\'' +
                ", fileMessage='" + fileMessage + '\'' +
                '}';
    }
}
