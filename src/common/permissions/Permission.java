package common.permissions;

public enum Permission {
	PATIENT("Patient"),
	ADMIN("Admin"),
	DOCTOR("Doctor"),
	OPERATOR("Operator"),
	RECEPTION("Reception"),
	LABORER("Laborer");

	private String typeName;

	Permission(String typeName) {
		this.typeName = typeName;
	}

	@Override
	public String toString() {
		return this.typeName;
	}
}