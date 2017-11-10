package com.example.selectmenu2;


public class MyFriendsSDetails {
	
	 private String myfriendname=null;
	 private String myfriendnickname=null;
	 private int photo=0;
	 
	 public MyFriendsSDetails(String friendname, String friendnickname, int myphoto){
		 this.myfriendname=friendname;
		 this.myfriendnickname=friendnickname;
		 this.photo=myphoto;
	 }

	public String getMyfriendname() {
		return myfriendname;
	}

	public void setMyfriendname(String myfriendname) {
		this.myfriendname = myfriendname;
	}

	public String getMyfriendnickname() {
		return myfriendnickname;
	}

	public void setMyfriendnickname(String myfriendnickname) {
		this.myfriendnickname = myfriendnickname;
	}

	public int getPhoto() {
		return photo;
	}

	public void setPhoto(int photo) {
		this.photo = photo;
	}

	 
}
