package jp.devmen.spellchecker.model;

import java.util.List;

/**
 * 検査結果クラスです。
 *
 */
public class CheckResult implements ICheckResult {

	private boolean isValid;
	List<String> SuggestList;
	
	/**
	 * コンストラクタ
	 * 
	 * @param isValid
	 * @param SuggestList
	 */
	protected CheckResult(boolean isValid, List<String> SuggestList) {
		this.isValid = isValid;
		this.SuggestList = SuggestList;
	}

	@Override
	public boolean isValid() {
		return isValid;
	}

	@Override
	public List<String> getSuggestList() {
		if (!isValid) {
			return SuggestList;
		} else {
			return null;
		}
	}
}
