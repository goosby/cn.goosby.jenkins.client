package cn.goosby.jenkins.enums;

/**
 * jenkins job type
 * @author : goosby
 */
public enum  JobTypeEnum {

    FREESTYLE("free_style",1),MAVENSTYPE("maven_stype",2),EXTERNALSTYLE("external_style",3),MATRIXSTYLE("matrix_style",4);

    private String styleName;

    private int index;

    private JobTypeEnum(String styleName,int index){
        this.index = index;
        this.styleName = styleName;
    }

    public String getStyleName(){
        return this.name();
    }

    public  int getIndex(){
        return this.index;
    }
}
