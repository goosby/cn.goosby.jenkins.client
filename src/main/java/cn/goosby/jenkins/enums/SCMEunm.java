package cn.goosby.jenkins.enums;

public enum SCMEunm {
    SVN("svn",1),GIT("git",2),CVS("cvs",3);

    private String scmName;
    private int index;

    private SCMEunm(String scmName,int index){
        this.index = index;
        this.scmName = scmName;
    }

    public String getScmName(){
        return this.scmName;
    }

    public int getIndex(){
        return this.index;
    }


}
