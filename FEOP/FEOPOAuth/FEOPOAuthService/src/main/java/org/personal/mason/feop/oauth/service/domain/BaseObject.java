package org.personal.mason.feop.oauth.service.domain;

import java.io.Serializable;

public abstract class BaseObject implements Serializable {

	private static final long serialVersionUID = 1L;

	public abstract boolean equals(Object o);

	public abstract int hashCode();
}
