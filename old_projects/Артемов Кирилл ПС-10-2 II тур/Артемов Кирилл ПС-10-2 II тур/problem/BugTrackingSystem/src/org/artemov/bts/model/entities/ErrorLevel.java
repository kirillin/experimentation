package org.artemov.bts.model.entities;

public class ErrorLevel extends Entity {

	private static final long serialVersionUID = 1L;
	private String levelName;

	public ErrorLevel() {
	}

	public ErrorLevel(int id, String levelName) {
		super(id);
		this.levelName = levelName;
	}

	@Override
	public int getId() {
		return super.getId();
	}

	@Override
	public void setId(int id) {
		super.setId(id);
	}

	public String getLevelName() {
		return levelName;
	}

	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((levelName == null) ? 0 : levelName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		ErrorLevel other = (ErrorLevel) obj;
		if (levelName == null) {
			if (other.levelName != null)
				return false;
		} else if (!levelName.equals(other.levelName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ErrorLevel [levelName=" + levelName + "]";
	}

}
