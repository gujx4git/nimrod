package com.gioov.gujx;
import java.io.Serializable;

public class CurrencyEntity  implements Serializable {
    private Long id;

    private String currname;

    private String currcode;

   

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCurrname() {
		return currname;
	}

	public void setCurrname(String currname) {
		this.currname = currname;
	}

	public String getCurrcode() {
		return currcode;
	}

	public void setCurrcode(String currcode) {
		this.currcode = currcode;
	}

	private static final long serialVersionUID = 1L;
}


