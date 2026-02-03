// サイドメニュー表示をトグルで切り替えられるようにする
document.addEventListener("DOMContentLoaded", () => {
	const btn = document.getElementById("hamburgerBtn");

	const sidebar = document.querySelector(".sidebar");
	btn.addEventListener("click", () => {
		document.body.classList.toggle("sidebar-open");
	});

	// サイドバー内のリンクを押したら閉じる
	sidebar.addEventListener("click", (e) => {
		if (e.target.tagName === "A") {
			document.body.classList.remove("sidebar-open");
		}
	});
});

// 背景をクリックするとサブメニューが閉じる
const overlay = document.getElementById("overlay");

overlay.addEventListener("click", () => {
	document.body.classList.remove("sidebar-open");
});


