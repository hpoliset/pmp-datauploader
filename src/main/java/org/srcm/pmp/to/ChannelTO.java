package org.srcm.pmp.to;

public class ChannelTO implements TransferObject {
  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
private String channelName;
  private Integer channerlId;
/**
 * @return the channelName
 */
public String getChannelName() {
	return channelName;
}
/**
 * @param channelName the channelName to set
 */
public void setChannelName(String channelName) {
	this.channelName = channelName;
}
/**
 * @return the channerlId
 */
public Integer getChannerlId() {
	return channerlId;
}
/**
 * @param channerlId the channerlId to set
 */
public void setChannerlId(Integer channerlId) {
	this.channerlId = channerlId;
}
/* (non-Javadoc)
 * @see java.lang.Object#toString()
 */
@Override
public String toString() {
	return "ChannelTO [channelName=" + channelName + ", channerlId="
			+ channerlId + "]";
}
}
