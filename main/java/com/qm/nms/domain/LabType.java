package com.qm.nms.domain;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class LabType {
	private long id;
	private Long parentId;
	private String typeName;
	private String typeValue;
	private String typeDesc;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Basic
	@Column(name = "parentId")
	public Long getParentId() {
		return this.parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	@Basic
	@Column(name = "type_name")
	public String getTypeName() {
		return this.typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	@Basic
	@Column(name = "type_value")
	public String getTypeValue() {
		return this.typeValue;
	}

	public void setTypeValue(String typeValue) {
		this.typeValue = typeValue;
	}

	@Basic
	@Column(name = "type_desc")
	public String getTypeDesc() {
		return this.typeDesc;
	}

	public void setTypeDesc(String typeDesc) {
		this.typeDesc = typeDesc;
	}

	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if ((o == null) || (getClass() != o.getClass())) {
			return false;
		}
		LabType labType = (LabType) o;
		if (this.id != labType.id) {
			return false;
		}
		if (this.parentId != null ? !this.parentId.equals(labType.parentId) : labType.parentId != null) {
			return false;
		}
		if (this.typeDesc != null ? !this.typeDesc.equals(labType.typeDesc) : labType.typeDesc != null) {
			return false;
		}
		if (this.typeName != null ? !this.typeName.equals(labType.typeName) : labType.typeName != null) {
			return false;
		}
		if (this.typeValue != null ? !this.typeValue.equals(labType.typeValue) : labType.typeValue != null) {
			return false;
		}
		return true;
	}

	public int hashCode() {
		int result = (int) (this.id ^ this.id >>> 32);
		result = 31 * result + (this.parentId != null ? this.parentId.hashCode() : 0);
		result = 31 * result + (this.typeName != null ? this.typeName.hashCode() : 0);
		result = 31 * result + (this.typeValue != null ? this.typeValue.hashCode() : 0);
		result = 31 * result + (this.typeDesc != null ? this.typeDesc.hashCode() : 0);
		return result;
	}

}
