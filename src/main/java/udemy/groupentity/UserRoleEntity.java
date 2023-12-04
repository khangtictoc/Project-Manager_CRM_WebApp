package udemy.groupentity;

import udemy.entity.UserEntity;
import udemy.entity.PrivilegeEntity;

public class UserRoleEntity {
	private UserEntity user;
	private PrivilegeEntity priv;
	
	public UserEntity getUser() {
		return user;
	}
	public void setUser(UserEntity user) {
		this.user = user;
	}
	public PrivilegeEntity getPriv() {
		return priv;
	}
	public void setPriv(PrivilegeEntity priv) {
		this.priv = priv;
	}
}
