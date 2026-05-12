let winStreak = 0;

function handleSelection(hand) {
	const buttons = document.querySelectorAll('input[name="hand"]');
	buttons.forEach(button => button.classList.remove('selected'));
	document.getElementById(hand).classList.add('selected');
}

function playGame() {
	const selectedHand = document.querySelector('input[name="hand"]:checked');
	if (!selectedHand) {
		alert('手を選択してください');
		return;
	}

	const hand = selectedHand.value;
	const submitButton = document.getElementById('submit-button');
	submitButton.style.display = "none";

	// CSRF トークン取得
	const csrfToken = document.querySelector('meta[name="_csrf"]').content;
	const csrfHeader = document.querySelector('meta[name="_csrf_header"]').content;

	fetch("/game/play", {
		method: "POST",
		headers: {
			"Content-Type": "application/x-www-form-urlencoded",
			[csrfHeader]: csrfToken  // ← CSRF ヘッダを追加
		},
		body: "hand=" + hand
	})
		.then(response => response.json())
		.then(data => {
			if (data.status !== "OK") {
				alert("エラーが発生しました");
				submitButton.disabled = false;
				return;
			}

			const resultText = document.getElementById('result');
			const winStreakText = document.getElementById('win-streak');
			const cpuHandImg = document.getElementById('cpu-hand-img');
			const cpuHandContainer = document.getElementById('cpu-hand');
			
			

			// CPUの手画像を表示
			cpuHandImg.src = "/images/" + data.cpuHand + ".png";
			cpuHandContainer.style.display = "block";

			if (data.result == "WIN") {
				resultText.innerText = "あなたの勝ちです";
				winStreak++;
			} else if (data.result == "LOSE") {
				resultText.innerText = "あなたの負けです";
				winStreak = 0;
			} else if(data.result == "DRAW"){
				resultText.innerText = "引き分けです";
				winStreak = 0;
			}

			winStreakText.innerText = winStreak > 0 ? winStreak + "連勝中！" : "";

			document.getElementById('retry-button').style.display = 'inline-block';
			document.getElementById('exit-button').style.display = 'inline-block';
		})
		.catch(error => {
			console.error(error);
			alert("通信エラー");
			submitButton.disabled = false;
		});
}

function retryGame() {
	document.getElementById('submit-button').style.display = 'inline-block';
	document.getElementById('submit-button').disabled =false;
	document.getElementById('result').innerText = '';
	document.getElementById('win-streak').innerText = '';
	document.getElementById('retry-button').style.display = 'none';
	document.getElementById('cpu-hand').style.display = 'none';
	document.getElementById('exit-button').style.display = 'none';

	const buttons = document.querySelectorAll('input[name="hand"]');
	buttons.forEach(button => button.checked = false);
}