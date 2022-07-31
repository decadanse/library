package model;

public class Info extends LibraryModel {
	
    private String info;

    public Info() {
		// TODO Auto-generated constructor stub
	}

    public Info(String info) {
        this.info = info;
    }

	public String getInfo() {
        return info;
    }
   
    public void setInfo(String info) {
        this.info = info;
    }
    

    public String toStringForSearch() {
        return "" + id + " " + info;
    }

    @Override
    public String toStringForLog() {
        return id + " " + info;
    }
}