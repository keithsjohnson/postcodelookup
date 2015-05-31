package uk.co.cdl.keithj.postcodelookup.api.model;

public class PostcodeLocation {
	private final String postcode;
	private final String latitude;
	private final String longitude;

	public PostcodeLocation(String postcode, String latitude, String longitude) {
		super();
		this.postcode = postcode;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public String getPostcode() {
		return postcode;
	}

	public String getLatitude() {
		return latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	@Override
	public String toString() {
		return "PostdcodeLocation [postcode=" + postcode + ", latitude=" + latitude + ", longitude=" + longitude + "]";
	}

}
