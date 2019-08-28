package com.xuyang.gdutmallserver.model;

public class UserInfo {
    private Integer id;
    private String userName;
    private String userPwd;
    private String userMobile;
    private String userIcon;
    private String userRealName;
    private String userIdentityCard;
    private String userNickName;
    private String userGender;
    private String userBirthday;
    private String userAddress;
    private String userSign;
    private String pushId;

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        /*  43 */
        this.userName = (userName == null ? null : userName.trim());
    }

    public String getUserPwd() {
        /*  47 */
        return this.userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = (userPwd == null ? null : userPwd.trim());
    }

    public String getUserMobile() {
        return this.userMobile;
    }

    public void setUserMobile(String userMobile) {
        this.userMobile = (userMobile == null ? null : userMobile.trim());
    }

    public String getUserIcon() {
        return this.userIcon;
    }

    public void setUserIcon(String userIcon) {
        this.userIcon = (userIcon == null ? null : userIcon.trim());
    }

    public String getUserRealName() {
        return this.userRealName;
    }

    public void setUserRealName(String userRealName) {
        this.userRealName = (userRealName == null ? null : userRealName.trim());
    }

    public String getUserIdentityCard() {
        return this.userIdentityCard;
    }

    public void setUserIdentityCard(String userIdentityCard) {
        this.userIdentityCard = (userIdentityCard == null ? null : userIdentityCard.trim());
    }

    public String getUserNickName() {
        return this.userNickName;
    }

    public void setUserNickName(String userNickName) {
        this.userNickName = (userNickName == null ? null : userNickName.trim());
    }

    public String getUserGender() {
        return this.userGender;
    }

    public void setUserGender(String userGender) {
        this.userGender = (userGender == null ? null : userGender.trim());
    }

    public String getUserBirthday() {
        /* 103 */
        return this.userBirthday;
    }

    public void setUserBirthday(String userBirthday) {
        this.userBirthday = (userBirthday == null ? null : userBirthday.trim());
    }

    public String getUserAddress() {
        return this.userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = (userAddress == null ? null : userAddress.trim());
    }

    public String getUserSign() {
        return this.userSign;
    }

    public void setUserSign(String userSign) {
        this.userSign = (userSign == null ? null : userSign.trim());
    }

    public String getPushId() {
        return this.pushId;
    }

    public void setPushId(String pushId) {
        this.pushId = pushId;
    }
}

