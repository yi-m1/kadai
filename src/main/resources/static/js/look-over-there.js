// 方向をキーボードの矢印キーで入力し、エンターキーで送信する
document.addEventListener("keydown", function(e) {

	// 繰り返し同じボタンが押されたときに値を返さないようにする
	if (e.repeat) return;

	// 矢印キーが入力されたときに、ブラウザのデフォルトの操作を無効にする
	if (["ArrowUp", "ArrowDown", "ArrowLeft", "ArrowRight"].includes(e.key)) {
		e.preventDefault();
	}

	switch (e.key) {

		case "ArrowUp":
			selectDirection("dir-up");
			break;

		case "ArrowDown":
			selectDirection("dir-down");
			break;

		case "ArrowLeft":
			selectDirection("dir-left");
			break;

		case "ArrowRight":
			selectDirection("dir-right");
			break;

		case "Enter":
			submitIfSelected();
			break;
	}
});

// 矢印キーの入力と方向IDを紐づける
function selectDirection(id) {

	const radio = document.getElementById(id);
	if (radio) {
		// ラジオボタンを選択状態にする
		radio.checked = true;
		radio.focus();
	}
}

// 方向が入力されている場合のみ、エンターキーで送信
function submitIfSelected() {
	const selected = document.querySelector(
		"input[name='direction']:checked"
	);

	// 方向が選択されているかチェック
	if (selected) {
		const form = selected.closest("form");
		form.requestSubmit();
	}
}

