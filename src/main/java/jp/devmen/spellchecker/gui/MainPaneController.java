package jp.devmen.spellchecker.gui;

import java.util.List;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import jp.devmen.spellchecker.model.ICheckResult;
import jp.devmen.spellchecker.model.ISpellChecker;
import jp.devmen.spellchecker.model.IWordDictionary;
import jp.devmen.spellchecker.model.SpellChecker;
import jp.devmen.spellchecker.model.WordDictionary;

import com.sun.javafx.collections.ObservableListWrapper;

public class MainPaneController {

	@FXML
	private ListView<String> listDictionary;

	@FXML
	private TextField txtFieldWord;

	@FXML
	private Label labelSuggested;

	private IWordDictionary wordDictionary;

	@FXML
	void initialize() {
		wordDictionary = WordDictionary.getInstance();

		List<String> allWord = wordDictionary.getAllWord();
		listDictionary.setItems(new ObservableListWrapper<String>(allWord));
	}

	@FXML
	void addButtonFired(ActionEvent event) {
		// 画面チームの実装箇所
		ISpellChecker spellChecker = SpellChecker.getInstance();
		if (spellChecker == null)
			return;

		ICheckResult result = spellChecker.check(txtFieldWord.toString());
		if (result == null)
			return;
		if (result.isValid()) {
			labelSuggested.setText("--");
			if (!wordDictionary.containts(txtFieldWord.toString())) {
				wordDictionary.addWord(txtFieldWord.toString());
				refreshAllwordList();
			}
		} else {
			List<String> suggestList = result.getSuggestList();
			labelSuggested.setText(String.join(",", suggestList));
		}
	}

	private void refreshAllwordList() {
		listDictionary.setItems(null);
		List<String> allWord = wordDictionary.getAllWord();
		listDictionary.setItems(new ObservableListWrapper<String>(allWord));
	}
}
