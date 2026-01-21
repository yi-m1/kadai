package entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 結果履歴テーブルのエンティティクラス
 */
public class ResultHistory implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
    private Long historyId;
    
    private int userId;
    
    private Integer gameTypeId;
    
    private Date executeDatetime;
    
    private int opponent;
    
    private String result;
    
    private String userChoice;
    
    private Date createDatetime;
    
    private Date updateDatetime;
    
    private Integer version;

	/**
	 * @return historyId
	 */
	public Long getHistoryId() {
		return historyId;
	}

	/**
	 * @param historyId セットする historyId
	 */
	public void setHistoryId(Long historyId) {
		this.historyId = historyId;
	}

	/**
	 * @return userId
	 */
	public int getUserId() {
		return userId;
	}

	/**
	 * @param userId セットする userId
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}

	/**
	 * @return gameTypeId
	 */
	public Integer getGameTypeId() {
		return gameTypeId;
	}

	/**
	 * @param gameTypeId セットする gameTypeId
	 */
	public void setGameTypeId(Integer gameTypeId) {
		this.gameTypeId = gameTypeId;
	}

	/**
	 * @return executeDatetime
	 */
	public Date getExecuteDatetime() {
		return executeDatetime;
	}

	/**
	 * @param executeDatetime セットする executeDatetime
	 */
	public void setExecuteDatetime(Date executeDatetime) {
		this.executeDatetime = executeDatetime;
	}

	/**
	 * @return opponent
	 */
	public int getOpponent() {
		return opponent;
	}

	/**
	 * @param opponent セットする opponent
	 */
	public void setOpponent(int opponent) {
		this.opponent = opponent;
	}

	/**
	 * @return result
	 */
	public String getResult() {
		return result;
	}

	/**
	 * @param result セットする result
	 */
	public void setResult(String result) {
		this.result = result;
	}

	/**
	 * @return userChoice
	 */
	public String getUserChoice() {
		return userChoice;
	}

	/**
	 * @param userChoice セットする userChoice
	 */
	public void setUserChoice(String userChoice) {
		this.userChoice = userChoice;
	}

	/**
	 * @return createDatetime
	 */
	public Date getCreateDatetime() {
		return createDatetime;
	}

	/**
	 * @param createDatetime セットする createDatetime
	 */
	public void setCreateDatetime(Date createDatetime) {
		this.createDatetime = createDatetime;
	}

	/**
	 * @return updateDatetime
	 */
	public Date getUpdateDatetime() {
		return updateDatetime;
	}

	/**
	 * @param updateDatetime セットする updateDatetime
	 */
	public void setUpdateDatetime(Date updateDatetime) {
		this.updateDatetime = updateDatetime;
	}

	/**
	 * @return version
	 */
	public Integer getVersion() {
		return version;
	}

	/**
	 * @param version セットする version
	 */
	public void setVersion(Integer version) {
		this.version = version;
	}

	/**
	 * @return serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

    
    
}
